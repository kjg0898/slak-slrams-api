package org.neighbor21.slakslramsapi.util;

import org.neighbor21.slakslramsapi.dto.TL_RIS_ROADWIDTHDTO;
import org.neighbor21.slakslramsapi.dto.TL_RIS_ROUGH_DISTRIDTO;
import org.neighbor21.slakslramsapi.dto.TL_RIS_SURFACEDTO;
import org.neighbor21.slakslramsapi.dto.TL_TIS_AADTDTO;
import org.neighbor21.slakslramsapi.service.BatchInserter;
import org.neighbor21.slakslramsapi.service.CollectionDateTimeService;
import org.neighbor21.slakslramsapi.service.EntityConverter;
import org.neighbor21.slakslramsapi.service.MaxSqnoProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * packageName    : org.neighbor21.slakslramsapi.util
 * fileName       : CommonUtility.java
 * author         : kjg08
 * date           : 24. 6. 12.
 * description    : 공통 유틸리티 클래스 공통 처리 로직을 포함하고 있으며, 여러 서비스에서 재사용됩니다.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 6. 12.        kjg08           최초 생성
 */
@Component
public class CommonUtility {
    private static final Logger logger = LoggerFactory.getLogger(CommonUtility.class);

    @Autowired
    private CollectionDateTimeService collectionDateTimeService;

    /**
     * 공통 삽입 처리 메서드
     *
     * @param dtos            DTO 리스트
     * @param converter       엔티티 변환 함수
     * @param maxSqnoProvider 최대 순번 제공 함수
     * @param batchInserter   배치 삽입 함수
     * @param processName     프로세스 이름
     * @param timestampKey    타임스탬프 키
     */
    @Transactional
    public <T, E> void processInsert(List<T> dtos, EntityConverter<T, E> converter, MaxSqnoProvider maxSqnoProvider, BatchInserter<E> batchInserter, String processName, String timestampKey) {
        try {
            Map<String, Integer> maxSqnoMap = getMaxSqnoMap(maxSqnoProvider.getMaxSqnoBySurveyYear());

            if (maxSqnoMap == null) {
                throw new RuntimeException("maxSqnoMap is null");
            }

            List<E> entities = dtos.stream()
                    .sorted(Comparator.comparing(this::getSurveyYear))
                    .map(dto -> {
                        E entity = converter.convert(dto, maxSqnoMap);
                        if (entity == null) {
                            logger.warn("Entity conversion resulted in null for DTO: {}", dto);
                        }
                        return entity;
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            if (entities.isEmpty()) {
                throw new RuntimeException("No entities to insert after conversion");
            }

            logger.info("Starting batch insert for {} with {} entities", processName, entities.size());
            batchInserter.batchInsert(entities);

            Timestamp latestTimestamp = new Timestamp(System.currentTimeMillis());
            collectionDateTimeService.updateTimestampIfNeeded(timestampKey, latestTimestamp);

            logger.info("{} process completed", processName);
        } catch (Exception e) {
            logger.error("Failed to insert entities in {}: {}", processName, e.getMessage(), e);
            throw new RuntimeException("Transaction rolled back due to insertion failure", e);
        }
    }

    /**
     * DTO에서 설문 연도를 추출하는 메서드
     *
     * @param dto DTO 객체
     * @return 설문 연도
     */
    private <T> String getSurveyYear(T dto) {
        if (dto instanceof TL_RIS_ROADWIDTHDTO) {
            return ((TL_RIS_ROADWIDTHDTO) dto).getSurveyYear();
        } else if (dto instanceof TL_RIS_ROUGH_DISTRIDTO) {
            return ((TL_RIS_ROUGH_DISTRIDTO) dto).getSurveyYear();
        } else if (dto instanceof TL_RIS_SURFACEDTO) {
            return ((TL_RIS_SURFACEDTO) dto).getSurveyYear();
        } else if (dto instanceof TL_TIS_AADTDTO) {
            return ((TL_TIS_AADTDTO) dto).getSurveyYear();
        }
        return null;
    }

    /**
     * 최대 순번 맵을 가져오는 메서드
     *
     * @param results 쿼리 결과 리스트
     * @return 최대 순번 맵
     */
    private Map<String, Integer> getMaxSqnoMap(List<Object[]> results) {
        return results.stream()
                .collect(Collectors.toMap(
                        result -> (String) result[0],
                        result -> (Integer) result[1]
                ));
    }
}
