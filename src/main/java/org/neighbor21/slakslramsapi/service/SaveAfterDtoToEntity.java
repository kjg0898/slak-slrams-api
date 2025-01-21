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
import org.neighbor21.slakslramsapi.util.CommonUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * packageName    : org.neighbor21.slakslramsapi.service
 * fileName       : SaveAfterDtoToEntity.java
 * author         : kjg08
 * date           : 24. 5. 2.
 * description    : DTO를 엔티티로 변환하여 데이터베이스에 저장하는 서비스 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 2.        kjg08           Initial creation
 */
@Service
public class SaveAfterDtoToEntity {

    @Autowired
    private TL_RIS_ROADWIDTHReposit roadwidthReposit;
    @Autowired
    private TL_RIS_ROUGH_DISTRIReposit roughDistriReposit;
    @Autowired
    private TL_RIS_SURFACEReposit surfaceReposit;
    @Autowired
    private TL_TIS_AADTReposit aadtReposit;

    @Autowired
    private CommonUtility commonUtility;

    @Autowired
    private BatchService batchService;

    /**
     * Method to save road width data.
     *
     * @param roadWidths List of Road Width DTOs
     */
    @Transactional
    public void insertTL_RIS_ROADWIDTH(List<TL_RIS_ROADWIDTHDTO> roadWidths) {
        commonUtility.processInsert(roadWidths, this::convertToRoadwidthEntity, roadwidthReposit::findMaxSqnoBySurveyYear, this::roadwidthBatchInsert, "insertTL_RIS_ROADWIDTH", "roadwidth");
    }

    /**
     * Method to save roughness distribution data.
     *
     * @param roughDistris List of Roughness Distribution DTOs
     */
    @Transactional
    public void insertTL_RIS_ROUGH_DISTRI(List<TL_RIS_ROUGH_DISTRIDTO> roughDistris) {
        commonUtility.processInsert(roughDistris, this::convertToRoughDistriEntity, roughDistriReposit::findMaxSqnoBySurveyYear, this::roughDistriBatchInsert, "insertTL_RIS_ROUGH_DISTRI", "roughDistri");
    }

    /**
     * Method to save surface data.
     *
     * @param surfaces List of Surface DTOs
     */
    @Transactional
    public void insertTL_RIS_SURFACE(List<TL_RIS_SURFACEDTO> surfaces) {
        commonUtility.processInsert(surfaces, this::convertToSurfaceEntity, surfaceReposit::findMaxSqnoBySurveyYear, this::surfaceBatchInsert, "insertTL_RIS_SURFACE", "surface");
    }

    /**
     * Method to save traffic distribution data.
     *
     * @param aadts List of Traffic Distribution DTOs
     */
    @Transactional
    public void insertTL_TIS_AADT(List<TL_TIS_AADTDTO> aadts) {
        commonUtility.processInsert(aadts, this::convertToAadtEntity, aadtReposit::findMaxSqnoBySurveyYear, this::aadtBatchInsert, "insertTL_TIS_AADT", "aadt");
    }

    /**
     * Method to convert a Road Width DTO into an entity.
     *
     * @param dto        Road Width DTO
     * @param maxSqnoMap Map of maximum sequence numbers
     * @return Road Width Entity
     */
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

    /**
     * Method to convert a Roughness Distribution DTO into an entity.
     *
     * @param dto        Roughness Distribution DTO
     * @param maxSqnoMap Map of maximum sequence numbers
     * @return Roughness Distribution Entity
     */
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

    /**
     * Method to convert a Surface DTO into an entity.
     *
     * @param dto        Surface DTO
     * @param maxSqnoMap Map of maximum sequence numbers
     * @return Surface Entity
     */
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

    /**
     * Method to convert a Traffic Distribution DTO into an entity.
     *
     * @param dto        Traffic Distribution DTO
     * @param maxSqnoMap Map of maximum sequence numbers
     * @return Traffic Distribution Entity
     */
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

    /**
     * Method to batch insert Road Width data.
     *
     * @param entities List of Road Width Entities
     */
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

    /**
     * Method to batch insert roughness distribution data.
     *
     * @param entities List of Roughness Distribution Entities
     */
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

    /**
     * Method to batch insert Surface data.
     *
     * @param entities List of Surface Entities
     */
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

    /**
     * Method to batch insert Traffic Distribution data.
     *
     * @param entities List of Traffic Distribution Entities
     */
    private void aadtBatchInsert(List<TL_TIS_AADTEntity> entities) {
        String sql = "INSERT INTO srlk.TL_TIS_AADT (LINK_CD, AAD_TRFVLM, CATEG, LEN, SRVY_YY, CLCT_DT, SQNO) VALUES (?, ?, ?, ?, ?, ?, ?)";
        batchService.batchInsertWithRetry(entities, sql, (ps, entity) -> {
            ps.setString(1, entity.getLinkCode());
            ps.setBigDecimal(2, new BigDecimal(entity.getAverageDailyTraffic()));
            ps.setString(3, entity.getCategory());
            ps.setBigDecimal(4, entity.getLength());
            ps.setString(5, entity.getSurveyYear());
            ps.setTimestamp(6, entity.getCollectionDateTime());
            ps.setInt(7, entity.getSqno());
        });
    }
}
