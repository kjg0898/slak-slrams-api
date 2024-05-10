package org.neighbor21.slakslramsapi.hendler;

import jakarta.annotation.PostConstruct;
import org.neighbor21.slakslramsapi.service.SaveAfterDtoToEntity;
import org.neighbor21.slakslramsapi.service.SlamsApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * packageName    : org.neighbor21.slakslramsapi.hendler
 * fileName       : ScheduledTaskHendler.java
 * author         : kjg08
 * date           : 24. 5. 7.
 * description    : slams data api 를 호출하여 db 에 적재하는 로직을 실행하는 스케쥴러
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 7.        kjg08           최초 생성
 */
@Component
public class ScheduledTasksHendler {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasksHendler.class);

    @Autowired
    private SlamsApiService slamsApiService;
    @Autowired
    private SaveAfterDtoToEntity saveAfterDtoToEntity;

    @PostConstruct
    public void init() {
        logger.info("ScheduledTasksHendler initialized");
    }

    private <T> void processApiCall(String taskName, Supplier<List<T>> apiCall, Consumer<List<T>> saveMethod) {
        logger.info("Starting {}", taskName);
        try {
            //각 api 호출
            List<T> dataList = apiCall.get();
            if (!dataList.isEmpty()) {
                saveMethod.accept(dataList); //각 엔티티 변환 및 배치 처리
            }
            logger.info("Completed {} successfully", taskName);
        } catch (Exception e) {
            logger.warn("Failed to execute {}: {}", taskName, e.getMessage(), e);
        }
    }

    @Scheduled(cron = "${scheduler.cron.roadwidth}") //TL_RIS_ROADWIDTH api 호출
    public void roadWidthDataCallApi() {
        processApiCall("roadWidthDataCallApi", slamsApiService::risRoadWidths, saveAfterDtoToEntity::insertTL_RIS_ROADWIDTH);
    }

    @Scheduled(cron = "${scheduler.cron.roughdistri}") //TL_RIS_ROUGH_DISTRI api 호출
    public void roughDataCallApi() {
        processApiCall("roughDataCallApi", slamsApiService::risRoughDistris, saveAfterDtoToEntity::insertTL_RIS_ROUGH_DISTRI);
    }

    @Scheduled(cron = "${scheduler.cron.surface}") //TL_RIS_SURFACE api 호출
    public void surfaceDataCallApi() {
        processApiCall("surfaceDataCallApi", slamsApiService::risSurfaces, saveAfterDtoToEntity::insertTL_RIS_SURFACE);
    }

    @Scheduled(cron = "${scheduler.cron.aadt}") //TL_TIS_AADT api 호출
    public void tisDataCallApi() {
        processApiCall("tisDataCallApi", slamsApiService::tisAadts, saveAfterDtoToEntity::insertTL_TIS_AADT);
    }




   /*
      @Scheduled(cron = "${scheduler.cron.roadwidth}")
    public void roadWidthDataCallApi() {
        logger.info("Starting roadWidthDataCallApi");
        try {
            //TL_RIS_ROADWIDTH api 호출
            List<TL_RIS_ROADWIDTHDTO> risRoadWidths = slamsApiService.risRoadWidths();
            if (!risRoadWidths.isEmpty()) {
                saveAfterDtoToEntity.insertTL_RIS_ROADWIDTH(risRoadWidths); // 엔티티 변환 및 배치 처리
            }
        } catch (Exception e) {
            logger.error("Failed to fetch and store RoadWidth data : {}", e.getMessage());
        }
    }
   @Scheduled(cron = "${scheduler.cron.roughdistri}")
    public void roughDataCallApi() {
        logger.info("Starting roughDataCallApi");
        try {
            //TL_RIS_ROUGH_DISTRI api 호출
            List<TL_RIS_ROUGH_DISTRIDTO> risRoughDistris = slamsApiService.risRoughDistris();
            if (!risRoughDistris.isEmpty()) {
                saveAfterDtoToEntity.insertTL_RIS_ROUGH_DISTRI(risRoughDistris); // 엔티티 변환 및 배치 처리
            }
        } catch (Exception e) {
            logger.error("Failed to fetch and store RoughDistri data : {}", e.getMessage());
        }
    }

    @Scheduled(cron = "${scheduler.cron.surface}")
    public void surfaceDataCallApi() {
        logger.info("Starting surfaceDataCallApi");
        try {
            //TL_RIS_SURFACE api 호출
            List<TL_RIS_SURFACEDTO> risSurfaces = slamsApiService.risSurfaces();
            if (!risSurfaces.isEmpty()) {
                saveAfterDtoToEntity.insertTL_RIS_SURFACE(risSurfaces); // 엔티티 변환 및 배치 처리
            }
        } catch (Exception e) {
            logger.error("Failed to fetch and store surface data : {}", e.getMessage());
        }
    }

    @Scheduled(cron = "${scheduler.cron.aadt}")
    public void tisDataCallApi() {
        logger.info("Starting tisDataCallApi");
        try {
            //TL_TIS_AADT api 호출
            List<TL_TIS_AADTDTO> tisAadts = slamsApiService.tisAadts();
            if (!tisAadts.isEmpty()) {
                saveAfterDtoToEntity.insertTL_TIS_AADT(tisAadts); // 엔티티 변환 및 배치 처리
            }
        } catch (Exception e) {
            logger.error("Failed to fetch and store TIS data : {}", e.getMessage());
        }
    }*/
}
