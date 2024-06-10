package org.neighbor21.slakslramsapi.service;

import org.neighbor21.slakslramsapi.dto.TL_RIS_ROADWIDTHDTO;
import org.neighbor21.slakslramsapi.dto.TL_RIS_ROUGH_DISTRIDTO;
import org.neighbor21.slakslramsapi.dto.TL_RIS_SURFACEDTO;
import org.neighbor21.slakslramsapi.dto.TL_TIS_AADTDTO;
import org.neighbor21.slakslramsapi.entity.TL_RIS_ROADWIDTHEntity;
import org.neighbor21.slakslramsapi.entity.TL_RIS_ROUGH_DISTRIEntity;
import org.neighbor21.slakslramsapi.entity.TL_RIS_SURFACEEntity;
import org.neighbor21.slakslramsapi.entity.TL_TIS_AADTEntity;
import org.neighbor21.slakslramsapi.jpRepository.TL_RIS_ROADWIDTHReposit;
import org.neighbor21.slakslramsapi.jpRepository.TL_RIS_ROUGH_DISTRIReposit;
import org.neighbor21.slakslramsapi.jpRepository.TL_RIS_SURFACEReposit;
import org.neighbor21.slakslramsapi.jpRepository.TL_TIS_AADTReposit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SaveAfterDtoToEntity {
    private static final Logger logger = LoggerFactory.getLogger(SaveAfterDtoToEntity.class);

    @Autowired
    private TL_RIS_ROADWIDTHReposit roadwidthReposit;
    @Autowired
    private TL_RIS_ROUGH_DISTRIReposit roughDistriReposit;
    @Autowired
    private TL_RIS_SURFACEReposit surfaceReposit;
    @Autowired
    private TL_TIS_AADTReposit aadtReposit;

    @Autowired
    private BatchService batchService;

    @Autowired
    private CollectionDateTimeService collectionDateTimeService;

    @Transactional
    public void insertTL_RIS_ROADWIDTH(List<TL_RIS_ROADWIDTHDTO> roadWidths) {
        processInsert(roadWidths, this::convertToRoadwidthEntity, roadwidthReposit::findMaxSqnoBySurveyYear, this::roadwidthBatchInsert, "insertTL_RIS_ROADWIDTH", "roadwidth");
    }

    @Transactional
    public void insertTL_RIS_ROUGH_DISTRI(List<TL_RIS_ROUGH_DISTRIDTO> roughDistris) {
        processInsert(roughDistris, this::convertToRoughDistriEntity, roughDistriReposit::findMaxSqnoBySurveyYear, this::roughDistriBatchInsert, "insertTL_RIS_ROUGH_DISTRI", "roughDistri");
    }

    @Transactional
    public void insertTL_RIS_SURFACE(List<TL_RIS_SURFACEDTO> surfaces) {
        processInsert(surfaces, this::convertToSurfaceEntity, surfaceReposit::findMaxSqnoBySurveyYear, this::surfaceBatchInsert, "insertTL_RIS_SURFACE", "surface");
    }

    @Transactional
    public void insertTL_TIS_AADT(List<TL_TIS_AADTDTO> aadts) {
        processInsert(aadts, this::convertToAadtEntity, aadtReposit::findMaxSqnoBySurveyYear, this::aadtBatchInsert, "insertTL_TIS_AADT", "aadt");
    }

    private <T, E> void processInsert(List<T> dtos, EntityConverter<T, E> converter, MaxSqnoProvider maxSqnoProvider, BatchInserter<E> batchInserter, String processName, String timestampKey) {
        long startTime = System.currentTimeMillis();
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
                    .filter(Objects::nonNull) // Null 값을 필터링하여 제외
                    .collect(Collectors.toList());

            if (entities.isEmpty()) {
                throw new RuntimeException("No entities to insert after conversion");
            }

            logger.info("Starting batch insert for {} with {} entities", processName, entities.size());
            batchInserter.batchInsert(entities);

            // 인서트 성공 시 최신 타임스탬프 업데이트
            Timestamp latestTimestamp = new Timestamp(System.currentTimeMillis());
            collectionDateTimeService.updateTimestampIfNeeded(timestampKey, latestTimestamp);

            long endTime = System.currentTimeMillis();
            logger.info("{} process completed in {} ms", processName, (endTime - startTime));
        } catch (Exception e) {
            logger.error("Failed to insert entities in {}: {}", processName, e.getMessage(), e);
            throw new RuntimeException("Transaction rolled back due to insertion failure", e);
        }
    }

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

    private TL_RIS_ROADWIDTHEntity convertToRoadwidthEntity(TL_RIS_ROADWIDTHDTO dto, Map<String, Integer> maxSqnoMap) {
        TL_RIS_ROADWIDTHEntity entity = new TL_RIS_ROADWIDTHEntity();
        entity.setLinkCode(dto.getLinkCode());
        entity.setRoadType(dto.getRoadType());
        entity.setWidthCategory(dto.getWidthCategory());
        entity.setLength(dto.getLength());
        entity.setSurveyYear(dto.getSurveyYear());
        entity.setCollectionDateTime(new Timestamp(System.currentTimeMillis()));
        entity.setSqno(maxSqnoMap.compute(dto.getSurveyYear(), (k, v) -> (v == null) ? 1 : v + 1));
        return entity;
    }

    private TL_RIS_ROUGH_DISTRIEntity convertToRoughDistriEntity(TL_RIS_ROUGH_DISTRIDTO dto, Map<String, Integer> maxSqnoMap) {
        TL_RIS_ROUGH_DISTRIEntity entity = new TL_RIS_ROUGH_DISTRIEntity();
        entity.setLinkCode(dto.getLinkCode());
        entity.setCategory(dto.getCategory());
        entity.setLength(dto.getLength());
        entity.setSurveyYear(dto.getSurveyYear());
        entity.setCollectionDateTime(new Timestamp(System.currentTimeMillis()));
        entity.setSqno(maxSqnoMap.compute(dto.getSurveyYear(), (k, v) -> (v == null) ? 1 : v + 1));
        return entity;
    }

    private TL_RIS_SURFACEEntity convertToSurfaceEntity(TL_RIS_SURFACEDTO dto, Map<String, Integer> maxSqnoMap) {
        TL_RIS_SURFACEEntity entity = new TL_RIS_SURFACEEntity();
        entity.setLinkCode(dto.getLinkCode());
        entity.setSurfaceCategory(dto.getSurfaceCategory());
        entity.setSurfaceDescription(dto.getSurfaceDescription());
        entity.setLength(dto.getLength());
        entity.setSurveyYear(dto.getSurveyYear());
        entity.setCollectionDateTime(new Timestamp(System.currentTimeMillis()));
        entity.setSqno(maxSqnoMap.compute(dto.getSurveyYear(), (k, v) -> (v == null) ? 1 : v + 1));
        return entity;
    }

    private TL_TIS_AADTEntity convertToAadtEntity(TL_TIS_AADTDTO dto, Map<String, Integer> maxSqnoMap) {
        TL_TIS_AADTEntity entity = new TL_TIS_AADTEntity();
        entity.setLinkCode(dto.getLinkCode());
        entity.setAverageDailyTraffic(dto.getAverageDailyTraffic());
        entity.setCategory(dto.getCategory());
        entity.setLength(dto.getLength());
        entity.setSurveyYear(dto.getSurveyYear());
        entity.setCollectionDateTime(new Timestamp(System.currentTimeMillis()));
        entity.setSqno(maxSqnoMap.compute(dto.getSurveyYear(), (k, v) -> (v == null) ? 1 : v + 1));
        return entity;
    }

    private Map<String, Integer> getMaxSqnoMap(List<Object[]> results) {
        return results.stream()
                .collect(Collectors.toMap(
                        result -> (String) result[0],
                        result -> (Integer) result[1]
                ));
    }

    private void roadwidthBatchInsert(List<TL_RIS_ROADWIDTHEntity> entities) {
        String sql = "INSERT INTO srlk.TL_RIS_ROADWIDTH (LINK_CD, ROAD_TYPE, WIDTH_CLSF, LEN, SRVY_YY, CLCT_DT, SQNO) VALUES (?, ?, ?, ?, ?, ?, ?)";
        batchService.batchInsertWithRetry(entities, sql, (ps, entity) -> {
            ps.setString(1, entity.getLinkCode());
            ps.setString(2, entity.getRoadType());
            ps.setString(3, entity.getWidthCategory());
            ps.setBigDecimal(4, entity.getLength());
            ps.setString(5, entity.getSurveyYear());
            ps.setTimestamp(6, entity.getCollectionDateTime());
            ps.setInt(7, entity.getSqno());
        });
    }

    private void roughDistriBatchInsert(List<TL_RIS_ROUGH_DISTRIEntity> entities) {
        String sql = "INSERT INTO srlk.TL_RIS_ROUGH_DISTRI (LINK_CD, CLSF, LEN, SRVY_YY, CLCT_DT, SQNO) VALUES (?, ?, ?, ?, ?, ?)";
        batchService.batchInsertWithRetry(entities, sql, (ps, entity) -> {
            ps.setString(1, entity.getLinkCode());
            ps.setString(2, entity.getCategory());
            ps.setBigDecimal(3, entity.getLength());
            ps.setString(4, entity.getSurveyYear());
            ps.setTimestamp(5, entity.getCollectionDateTime());
            ps.setInt(6, entity.getSqno());
        });
    }

    private void surfaceBatchInsert(List<TL_RIS_SURFACEEntity> entities) {
        String sql = "INSERT INTO srlk.TL_RIS_SURFACE (LINK_CD, SURF_CLSF, SURF_DESCR, LEN, SRVY_YY, CLCT_DT, SQNO) VALUES (?, ?, ?, ?, ?, ?, ?)";
        batchService.batchInsertWithRetry(entities, sql, (ps, entity) -> {
            ps.setString(1, entity.getLinkCode());
            ps.setString(2, entity.getSurfaceCategory());
            ps.setString(3, entity.getSurfaceDescription());
            ps.setBigDecimal(4, entity.getLength());
            ps.setString(5, entity.getSurveyYear());
            ps.setTimestamp(6, entity.getCollectionDateTime());
            ps.setInt(7, entity.getSqno());
        });
    }

    private void aadtBatchInsert(List<TL_TIS_AADTEntity> entities) {
        String sql = "INSERT INTO srlk.TL_TIS_AADT (LINK_CD, AAD_TRFVLM, CATEG, LEN, SRVY_YY, CLCT_DT, SQNO) VALUES (?, ?, ?, ?, ?, ?, ?)";
        batchService.batchInsertWithRetry(entities, sql, (ps, entity) -> {
            ps.setString(1, entity.getLinkCode());
            ps.setBigDecimal(2, new BigDecimal(entity.getAverageDailyTraffic())); // BigInteger to BigDecimal
            ps.setString(3, entity.getCategory());
            ps.setBigDecimal(4, entity.getLength());
            ps.setString(5, entity.getSurveyYear());
            ps.setTimestamp(6, entity.getCollectionDateTime());
            ps.setInt(7, entity.getSqno());
        });
    }

    @FunctionalInterface
    public interface EntityConverter<T, E> {
        E convert(T dto, Map<String, Integer> maxSqnoMap);
    }

    @FunctionalInterface
    public interface MaxSqnoProvider {
        List<Object[]> getMaxSqnoBySurveyYear();
    }

    @FunctionalInterface
    public interface BatchInserter<E> {
        void batchInsert(List<E> entities);
    }
}
