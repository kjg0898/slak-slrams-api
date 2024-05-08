package org.neighbor21.slakslramsapi.service;

import jakarta.transaction.Transactional;
import org.neighbor21.slakslramsapi.dto.TL_RIS_ROADWIDTHDTO;
import org.neighbor21.slakslramsapi.dto.TL_RIS_ROUGH_DISTRIDTO;
import org.neighbor21.slakslramsapi.dto.TL_RIS_SURFACEDTO;
import org.neighbor21.slakslramsapi.entity.TL_RIS_ROADWIDTHEntity;
import org.neighbor21.slakslramsapi.entity.TL_RIS_ROUGH_DISTRIEntity;
import org.neighbor21.slakslramsapi.entity.TL_RIS_SURFACEEntity;
import org.neighbor21.slakslramsapi.jpRepository.TL_RIS_ROADWIDTHReposit;
import org.neighbor21.slakslramsapi.jpRepository.TL_RIS_ROUGH_DISTRIReposit;
import org.neighbor21.slakslramsapi.jpRepository.TL_RIS_SURFACEReposit;
import org.neighbor21.slakslramsapi.jpRepository.TL_TIS_AADTReposit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

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
    private TL_RIS_ROADWIDTHReposit tlRisRoadwidthReposit;
    @Autowired
    private TL_RIS_ROUGH_DISTRIReposit tlRisRoughDistriReposit;
    @Autowired
    private TL_RIS_SURFACEReposit tlRisSurfaceReposit;
    @Autowired
    private TL_TIS_AADTReposit tlTisAadtReposit;


    @Transactional
    public void insertTL_RIS_ROADWIDTH(List<TL_RIS_ROADWIDTHDTO> roadWidths){
        for(TL_RIS_ROADWIDTHDTO roadWidth : roadWidths){
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            try {
                TL_RIS_ROADWIDTHEntity roadwidthEntity = new TL_RIS_ROADWIDTHEntity();

                //링크 코드
                String linkCode = roadWidth.getLinkCode();
                roadwidthEntity.setLinkCode(linkCode);
                //도로 유형
                roadwidthEntity.setRoadType(roadWidth.getRoadType());
                //너비 분류
                roadwidthEntity.setWidthCategory(roadWidth.getWidthCategory());
                //길이
                roadwidthEntity.setLength(roadWidth.getLength());
                //설문 년
                roadwidthEntity.setSurveyYear(roadWidth.getSurveyYear());
                //수집 일시
                roadwidthEntity.setCollectionDateTime(roadWidth.getCollectionDateTime());

                ////*
                // todo for 문 밖에서 saveAll 사용하여 한번에 배치 처리
                ////*
                tlRisRoadwidthReposit.save(roadwidthEntity);
                logger.info("Successfully inserted TL_RIS_ROADWIDTH for LinkCode {}: {}", linkCode, roadwidthEntity);

            } catch (DataAccessException e) {
                logger.error("Database access error during insert of TL_RIS_ROADWIDTH", e);
            } catch (Exception e) {
                logger.error("Unexpected error during insert of TL_RIS_ROADWIDTH", e);
            }
        }
    }

    @Transactional
    public void insertTL_RIS_ROUGH_DISTRI(List<TL_RIS_ROUGH_DISTRIDTO> roughDistris){
        for(TL_RIS_ROUGH_DISTRIDTO roughDistri : roughDistris){
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            try {
                TL_RIS_ROUGH_DISTRIEntity roughDistriEntity = new TL_RIS_ROUGH_DISTRIEntity();

                //링크 코드
                String linkCode = roughDistri.getLinkCode();
                roughDistriEntity.setLinkCode(linkCode);
                //분류
                roughDistriEntity.setCategory(roughDistri.getCategory());
                //길이
                roughDistriEntity.setLength(roughDistri.getLength());
                //설문 년
                roughDistriEntity.setSurveyYear(roughDistri.getSurveyYear());
                //수집 일시
                roughDistriEntity.setCollectionDateTime(roughDistri.getCollectionDateTime());

                ////*
                // todo for 문 밖에서 saveAll 사용하여 한번에 배치 처리
                ////*
                tlRisRoughDistriReposit.save(roughDistriEntity);
                logger.info("Successfully inserted TL_RIS_ROUGH_DISTRI for LinkCode {}: {}", linkCode, roughDistriEntity);

            } catch (DataAccessException e) {
                logger.error("Database access error during insert of TL_RIS_ROUGH_DISTRI", e);
            } catch (Exception e) {
                logger.error("Unexpected error during insert of TL_RIS_ROUGH_DISTRI", e);
            }
        }
    }

    @Transactional
    public void insertTL_RIS_SURFACE(List<TL_RIS_SURFACEDTO> surfaces){
        for(TL_RIS_SURFACEDTO surface : surfaces){
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            try {
                TL_RIS_SURFACEEntity surfaceEntity = new TL_RIS_SURFACEEntity();

                //링크 코드
                String linkCode = surface.getLinkCode();
                surfaceEntity.setLinkCode(linkCode);

                //표면 분류









                //도로 유형
                roadwidthEntity.setRoadType(roadwidth.getRoadType());
                //너비 분류
                roadwidthEntity.setWidthCategory(roadwidth.getWidthCategory());
                //길이
                roadwidthEntity.setLength(roadwidth.getLength());
                //설문 년
                roadwidthEntity.setSurveyYear(roadwidth.getSurveyYear());
                //수집 일시
                roadwidthEntity.setCollectionDateTime(roadwidth.getCollectionDateTime());

                ////*
                // todo for 문 밖에서 saveAll 사용하여 한번에 배치 처리
                ////*
                tlRisRoadwidthReposit.save(roadwidthEntity);
                logger.info("Successfully inserted TL_RIS_ROADWIDTH for LinkCode {}: {}", linkCode, roadwidthEntity);

            } catch (DataAccessException e) {
                logger.error("Database access error during insert of TL_RIS_ROADWIDTH", e);
            } catch (Exception e) {
                logger.error("Unexpected error during insert of TL_RIS_ROADWIDTH", e);
            }
        }
    }

    @Transactional
    public void insertTL_RIS_ROADWIDTH(List<TL_RIS_ROADWIDTHDTO> roadwidths){
        for(TL_RIS_ROADWIDTHDTO roadwidth : roadwidths){
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            try {
                TL_RIS_ROADWIDTHEntity roadwidthEntity = new TL_RIS_ROADWIDTHEntity();

                //링크 코드
                String linkCode = roadwidth.getLinkCode();
                roadwidthEntity.setLinkCode(linkCode);
                //도로 유형
                roadwidthEntity.setRoadType(roadwidth.getRoadType());
                //너비 분류
                roadwidthEntity.setWidthCategory(roadwidth.getWidthCategory());
                //길이
                roadwidthEntity.setLength(roadwidth.getLength());
                //설문 년
                roadwidthEntity.setSurveyYear(roadwidth.getSurveyYear());
                //수집 일시
                roadwidthEntity.setCollectionDateTime(roadwidth.getCollectionDateTime());

                ////*
                // todo for 문 밖에서 saveAll 사용하여 한번에 배치 처리
                ////*
                tlRisRoadwidthReposit.save(roadwidthEntity);
                logger.info("Successfully inserted TL_RIS_ROADWIDTH for LinkCode {}: {}", linkCode, roadwidthEntity);

            } catch (DataAccessException e) {
                logger.error("Database access error during insert of TL_RIS_ROADWIDTH", e);
            } catch (Exception e) {
                logger.error("Unexpected error during insert of TL_RIS_ROADWIDTH", e);
            }
        }
    }

    @Transactional
    public void insertTL_RIS_ROADWIDTH(List<TL_RIS_ROADWIDTHDTO> roadwidths){
        for(TL_RIS_ROADWIDTHDTO roadwidth : roadwidths){
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            try {
                TL_RIS_ROADWIDTHEntity roadwidthEntity = new TL_RIS_ROADWIDTHEntity();

                //링크 코드
                String linkCode = roadwidth.getLinkCode();
                roadwidthEntity.setLinkCode(linkCode);
                //도로 유형
                roadwidthEntity.setRoadType(roadwidth.getRoadType());
                //너비 분류
                roadwidthEntity.setWidthCategory(roadwidth.getWidthCategory());
                //길이
                roadwidthEntity.setLength(roadwidth.getLength());
                //설문 년
                roadwidthEntity.setSurveyYear(roadwidth.getSurveyYear());
                //수집 일시
                roadwidthEntity.setCollectionDateTime(roadwidth.getCollectionDateTime());

                ////*
                // todo for 문 밖에서 saveAll 사용하여 한번에 배치 처리
                ////*
                tlRisRoadwidthReposit.save(roadwidthEntity);
                logger.info("Successfully inserted TL_RIS_ROADWIDTH for LinkCode {}: {}", linkCode, roadwidthEntity);

            } catch (DataAccessException e) {
                logger.error("Database access error during insert of TL_RIS_ROADWIDTH", e);
            } catch (Exception e) {
                logger.error("Unexpected error during insert of TL_RIS_ROADWIDTH", e);
            }
        }
    }
}
