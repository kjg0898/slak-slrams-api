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
 * description    : Common utility class containing shared processing logic, reusable across multiple services.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 6. 12.        kjg08           Initial creation
 */
@Component
public class CommonUtility {
    private static final Logger logger = LoggerFactory.getLogger(CommonUtility.class);

    @Autowired
    private CollectionDateTimeService collectionDateTimeService;

    /**
     * Common insert processing method.
     *
     * @param dtos            List of DTOs
     * @param converter       Entity conversion function
     * @param maxSqnoProvider Function to provide the maximum sequence number
     * @param batchInserter   Batch insertion function
     * @param processName     Process name
     * @param timestampKey    Timestamp key
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
                            logger.warn("Entity conversion returned null for DTO: {}", dto);
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

            logger.info("{} process completed successfully", processName);
        } catch (Exception e) {
            logger.error("Error occurred during the insertion of entities in {}: {}", processName, e.getMessage(), e);
            throw new RuntimeException("Transaction rolled back due to insertion failure", e);
        }
    }

    /**
     * Method to extract the survey year from a DTO.
     *
     * @param dto DTO object
     * @return Survey year
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
     * Method to retrieve the map of maximum sequence numbers.
     *
     * @param results Query result list
     * @return Map of maximum sequence numbers
     */
    private Map<String, Integer> getMaxSqnoMap(List<Object[]> results) {
        return results.stream()
                .collect(Collectors.toMap(
                        result -> (String) result[0],
                        result -> (Integer) result[1]
                ));
    }
}
