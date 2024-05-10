package org.neighbor21.slakslramsapi.service;

import jakarta.transaction.Transactional;
import org.neighbor21.slakslramsapi.dto.TL_RIS_ROADWIDTHDTO;
import org.neighbor21.slakslramsapi.dto.TL_RIS_ROUGH_DISTRIDTO;
import org.neighbor21.slakslramsapi.dto.TL_RIS_SURFACEDTO;
import org.neighbor21.slakslramsapi.dto.TL_TIS_AADTDTO;
import org.neighbor21.slakslramsapi.entity.TL_RIS_ROADWIDTHEntity;
import org.neighbor21.slakslramsapi.entity.TL_RIS_ROUGH_DISTRIEntity;
import org.neighbor21.slakslramsapi.entity.TL_RIS_SURFACEEntity;
import org.neighbor21.slakslramsapi.entity.TL_TIS_AADTEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName    : org.neighbor21.slakslramsapi.service
 * fileName       : SaveAfterDtoToEntity.java
 * author         : kjg08
 * date           : 24. 5. 7.
 * description    : dto 를 조합하여 엔티티를 만들어 db 에 save 하는 로직
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 7.        kjg08           최초 생성
 */
@Service
public class SaveAfterDtoToEntity {
    private static final Logger logger = LoggerFactory.getLogger(SaveAfterDtoToEntity.class);

    @Autowired
    private BatchService batchService;

    //ris 도로너비 정보
    @Transactional
    public void insertTL_RIS_ROADWIDTH(List<TL_RIS_ROADWIDTHDTO> roadWidths) {
        try {
            List<TL_RIS_ROADWIDTHEntity> roadwidthEntities = roadWidths.stream()
                    .map(this::convertToRoadwidthEntity)
                    .collect(Collectors.toList());

            // 배치 삽입 시도
            batchService.batchInsertWithRetry(roadwidthEntities);
        } catch (Exception e) {
            //에러 로그 기록 및 트랜잭션 롤백
            logger.error("Failed to insert TL_RIS_ROADWIDTH entities: {}", e.getMessage(), e);
            throw new RuntimeException("Transaction rolled back due to insertion failure", e);
        }
    }

    //ris 거칠기 분포 정보
    @Transactional
    public void insertTL_RIS_ROUGH_DISTRI(List<TL_RIS_ROUGH_DISTRIDTO> roughDistris) {
        try {
            List<TL_RIS_ROUGH_DISTRIEntity> roughDistriEntities = roughDistris.stream()
                    .map(this::convertToRoughDistriEntity)
                    .collect(Collectors.toList());

            // 배치 삽입 시도
            batchService.batchInsertWithRetry(roughDistriEntities);
        } catch (Exception e) {
            //에러 로그 기록 및 트랜잭션 롤백
            logger.error("Failed to insert TL_RIS_ROUGH_DISTRI entities: {}", e.getMessage(), e);
            throw new RuntimeException("Transaction rolled back due to insertion failure", e);
        }
    }

    //ris 표면 유형 정보
    @Transactional
    public void insertTL_RIS_SURFACE(List<TL_RIS_SURFACEDTO> surfaces) {
        try {
            List<TL_RIS_SURFACEEntity> surfaceEntities = surfaces.stream()
                    .map(this::convertToSurfaceEntity)
                    .collect(Collectors.toList());

            // 배치 삽입 시도
            batchService.batchInsertWithRetry(surfaceEntities);
        } catch (Exception e) {
            //에러 로그 기록 및 트랜잭션 롤백
            logger.error("Failed to insert TL_RIS_SURFACE entities: {}", e.getMessage(), e);
            throw new RuntimeException("Transaction rolled back due to insertion failure", e);
        }
    }

    //tis 교통분산 정보
    @Transactional
    public void insertTL_TIS_AADT(List<TL_TIS_AADTDTO> aadts) {
        try {
            List<TL_TIS_AADTEntity> aadtEntities = aadts.stream()
                    .map(this::convertToAadtEntity)
                    .collect(Collectors.toList());

            // 배치 삽입 시도
            batchService.batchInsertWithRetry(aadtEntities);
        } catch (Exception e) {
            //에러 로그 기록 및 트랜잭션 롤백
            logger.error("Failed to insert TL_TIS_AADT entities: {}", e.getMessage(), e);
            throw new RuntimeException("Transaction rolled back due to insertion failure", e);
        }
    }

    private TL_RIS_ROADWIDTHEntity convertToRoadwidthEntity(TL_RIS_ROADWIDTHDTO dto) {
        TL_RIS_ROADWIDTHEntity entity = new TL_RIS_ROADWIDTHEntity();
        //링크 코드
        entity.setLinkCode(dto.getLinkCode());
        //도로 타입
        entity.setRoadType(dto.getRoadType());
        //너비 분류
        entity.setWidthCategory(dto.getWidthCategory());
        //길이
        entity.setLength(dto.getLength());
        //설문 년
        entity.setSurveyYear(dto.getSurveyYear());
        //수집 일시
        entity.setCollectionDateTime(new Timestamp(System.currentTimeMillis()));
        return entity;
    }

    private TL_RIS_ROUGH_DISTRIEntity convertToRoughDistriEntity(TL_RIS_ROUGH_DISTRIDTO dto) {
        TL_RIS_ROUGH_DISTRIEntity entity = new TL_RIS_ROUGH_DISTRIEntity();
        //링크 코드
        entity.setLinkCode(dto.getLinkCode());
        //분류
        entity.setCategory(dto.getCategory());
        //길이
        entity.setLength(dto.getLength());
        //설문 년
        entity.setSurveyYear(dto.getSurveyYear());
        //수집 일시
        entity.setCollectionDateTime(new Timestamp(System.currentTimeMillis()));
        return entity;
    }

    private TL_RIS_SURFACEEntity convertToSurfaceEntity(TL_RIS_SURFACEDTO dto) {
        TL_RIS_SURFACEEntity entity = new TL_RIS_SURFACEEntity();
        //링크 코드
        entity.setLinkCode(dto.getLinkCode());
        //표면 분류
        entity.setSurfaceCategory(dto.getSurfaceCategory());
        //표면 설명
        entity.setSurfaceDescription(dto.getSurfaceDescription());
        //길이
        entity.setLength(dto.getLength());
        //설문 년
        entity.setSurveyYear(dto.getSurveyYear());
        //수집 일시
        entity.setCollectionDateTime(new Timestamp(System.currentTimeMillis()));
        return entity;
    }

    private TL_TIS_AADTEntity convertToAadtEntity(TL_TIS_AADTDTO dto) {
        TL_TIS_AADTEntity entity = new TL_TIS_AADTEntity();
        //링크 코드
        entity.setLinkCode(dto.getLinkCode());
        //연평균일 교통량
        entity.setAverageDailyTraffic(dto.getAverageDailyTraffic());
        //카테고리
        entity.setCategory(dto.getCategory());
        //길이
        entity.setLength(dto.getLength());
        //설문 년
        entity.setSurveyYear(dto.getSurveyYear());
        //수집일시
        entity.setCollectionDateTime(new Timestamp(System.currentTimeMillis()));
        return entity;
    }




    /*//ris 도로너비 정보
    @Transactional
    public void insertTL_RIS_ROADWIDTH(List<TL_RIS_ROADWIDTHDTO> roadWidths) {
        try {
            List<TL_RIS_ROADWIDTHEntity> roadwidthEntitys = roadWidths.stream().map(roadWidth -> {
                // 데이터 유효성 검사
                if (roadWidth.getLinkCode() == null || roadWidth.getRoadType() == null) {
                    throw new IllegalArgumentException("Link Code and Road Type must not be null");
                }
                TL_RIS_ROADWIDTHEntity roadwidthEntity = new TL_RIS_ROADWIDTHEntity();
                //링크 코드
                roadwidthEntity.setLinkCode(roadWidth.getLinkCode());
                //도로 타입
                roadwidthEntity.setRoadType(roadWidth.getRoadType());
                //너비 분류
                roadwidthEntity.setWidthCategory(roadWidth.getWidthCategory());
                //길이
                roadwidthEntity.setLength(roadWidth.getLength());
                //설문 년
                roadwidthEntity.setSurveyYear(roadWidth.getSurveyYear());
                //수집 일시
                roadwidthEntity.setCollectionDateTime(new Timestamp(System.currentTimeMillis()));

                return roadwidthEntity;
            }).collect(Collectors.toList());

            // 배치 삽입 시도
            batchService.batchInsertWithRetry(roadwidthEntitys);
        } catch (Exception e) {
            // 로그 기록 및 트랜잭션 롤백
            logger.error("Failed to insert TL_RIS_ROADWIDTH entities :" + e.getMessage(), e);
            throw new RuntimeException("Transaction rolled back due to insertion failure", e);
        }
    }


    //ris 거칠기 분포 정보
    @Transactional
    public void insertTL_RIS_ROUGH_DISTRI(List<TL_RIS_ROUGH_DISTRIDTO> roughDistris) {
        try {
            List<TL_RIS_ROUGH_DISTRIEntity> roughDistriEntitys = roughDistris.stream().map(roughDistri -> {
                // 데이터 유효성 검사
                if (roughDistri.getLinkCode() == null || roughDistri.getCategory() == null) {
                    throw new IllegalArgumentException("Link Code and Category must not be null");
                }
                TL_RIS_ROUGH_DISTRIEntity roughDistriEntity = new TL_RIS_ROUGH_DISTRIEntity();

                //링크 코드
                roughDistriEntity.setLinkCode(roughDistri.getLinkCode());
                //분류
                roughDistriEntity.setCategory(roughDistri.getCategory());
                //길이
                roughDistriEntity.setLength(roughDistri.getLength());
                //설문 년
                roughDistriEntity.setSurveyYear(roughDistri.getSurveyYear());
                //수집 일시
                roughDistriEntity.setCollectionDateTime(new Timestamp(System.currentTimeMillis()));

                return roughDistriEntity;
            }).collect(Collectors.toList());

            // 배치 삽입 시도
            batchService.batchInsertWithRetry(roughDistriEntitys);
        } catch (Exception e) {
            // 로그 기록 및 트랜잭션 롤백
            logger.error("Failed to insert TL_RIS_ROUGH_DISTRI entities :" + e.getMessage(), e);
            throw new RuntimeException("Transaction rolled back due to insertion failure", e);
        }
    }

    //ris 표면 유형 정보
    @Transactional
    public void insertTL_RIS_SURFACE(List<TL_RIS_SURFACEDTO> surfaces) {
        try {
            List<TL_RIS_SURFACEEntity> surfaceEntitys = surfaces.stream().map(surface -> {
                // 데이터 유효성 검사
                if (surface.getLinkCode() == null || surface.getSurfaceCategory() == null) {
                    throw new IllegalArgumentException("Link Code and Surface Category must not be null");
                }
                TL_RIS_SURFACEEntity surfaceEntity = new TL_RIS_SURFACEEntity();

                //링크 코드
                surfaceEntity.setLinkCode(surface.getLinkCode());
                //표면 분류
                surfaceEntity.setSurfaceCategory(surface.getSurfaceCategory());
                //표면 설명
                surfaceEntity.setSurfaceDescription(surface.getSurfaceDescription());
                //길이
                surfaceEntity.setLength(surface.getLength());
                //설문 년
                surfaceEntity.setSurveyYear(surface.getSurveyYear());
                //수집 일시
                surfaceEntity.setCollectionDateTime(new Timestamp(System.currentTimeMillis()));

                return surfaceEntity;
            }).collect(Collectors.toList());

            // 배치 삽입 시도
            batchService.batchInsertWithRetry(surfaceEntitys);
        } catch (Exception e) {
            // 로그 기록 및 트랜잭션 롤백
            logger.error("Failed to insert TL_RIS_SURFACE entities :" + e.getMessage(), e);
            throw new RuntimeException("Transaction rolled back due to insertion failure", e);
        }
    }


    //tis 교통분산 정보
    @Transactional
    public void insertTL_TIS_AADT(List<TL_TIS_AADTDTO> aadts) {
        try {
            List<TL_TIS_AADTEntity> aadtEntitys = aadts.stream().map(aadt -> {
                // 데이터 유효성 검사
                if (aadt.getLinkCode() == null || aadt.getCategory() == null) {
                    throw new IllegalArgumentException("Link Code and Category must not be null");
                }
                TL_TIS_AADTEntity aadtEntity = new TL_TIS_AADTEntity();

                //링크 코드
                aadtEntity.setLinkCode(aadt.getLinkCode());
                //연평균일 교통량
                aadtEntity.setAverageDailyTraffic(aadt.getAverageDailyTraffic());
                //카테고리
                aadtEntity.setCategory(aadt.getCategory());
                //길이
                aadtEntity.setLength(aadt.getLength());
                //설문 년
                aadtEntity.setSurveyYear(aadt.getSurveyYear());
                //수집일시
                aadtEntity.setCollectionDateTime(new Timestamp(System.currentTimeMillis()));

                return aadtEntity;
            }).collect(Collectors.toList());

            // 배치 삽입 시도
            batchService.batchInsertWithRetry(aadtEntitys);
        } catch (Exception e) {
            // 로그 기록 및 트랜잭션 롤백
            logger.error("Failed to insert TL_TIS_AADT entities :" + e.getMessage(), e);
            throw new RuntimeException("Transaction rolled back due to insertion failure", e);
        }
    }*/
}
