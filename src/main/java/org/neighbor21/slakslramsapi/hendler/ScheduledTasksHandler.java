package org.neighbor21.slakslramsapi.hendler;

import jakarta.annotation.PostConstruct;
import kong.unirest.UnirestException;
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
 * fileName       : ScheduledTasksHandler.java
 * author         : kjg08
 * date           : 24. 5. 2.
 * description    : 스케줄러를 사용하여 정기적으로 API를 호출하고 데이터를 처리하는 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 2.        kjg08           최초 생성
 */
@Component
public class ScheduledTasksHandler {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasksHandler.class);

    @Autowired
    private SlamsApiService slamsApiService;
    @Autowired
    private SaveAfterDtoToEntity saveAfterDtoToEntity;

    @PostConstruct
    public void init() {
        logger.info("ScheduledTasksHandler initialized");
    }

    /**
     * API 호출을 처리하고 데이터를 저장하는 메서드
     *
     * @param taskName   작업 이름
     * @param apiCall    API 호출 함수
     * @param saveMethod 데이터 저장 함수
     * @param <T>        데이터 타입
     */
    private <T> void processApiCall(String taskName, Supplier<List<T>> apiCall, Consumer<List<T>> saveMethod) {
        logger.info("--------------------------------------------------------------------------------------------");
        logger.info("Starting {}", taskName);
        long startTime = System.currentTimeMillis();
        try {
            List<T> dataList = apiCall.get();
            logger.info("{} API call returned {} records", taskName, dataList.size());
            if (!dataList.isEmpty()) {
                saveMethod.accept(dataList);
                logger.info("{} records saved to database", dataList.size());
            } else {
                logger.info("No data to save for {}", taskName);
            }
            long endTime = System.currentTimeMillis();
            logger.info("--------------------------------------------------------------------------------------------");
            logger.info("Completed {} successfully in {} ms", taskName, (endTime - startTime));
        } catch (UnirestException e) {
            logger.error("Failed to fetch data from API for {}: {}", taskName, e.getMessage(), e);
            handleApiException(taskName, e);
        } catch (Exception e) {
            logger.error("Failed to execute {}: {}", taskName, e.getMessage(), e);
            handleGenericException(taskName, e);
        }
    }

    /**
     * API 호출 중 발생한 예외를 처리하는 메서드
     *
     * @param taskName 작업 이름
     * @param e        발생한 예외
     */
    private void handleApiException(String taskName, UnirestException e) {
        if (e.getCause() instanceof java.net.UnknownHostException) {
            logger.error("Unknown host exception for {}: Please check the URL or network connection", taskName);
        } else {
            logger.error("Generic API exception for {}: {}", taskName, e.getMessage());
        }
    }

    /**
     * 일반 예외를 처리하는 메서드
     *
     * @param taskName 작업 이름
     * @param e        발생한 예외
     */
    private void handleGenericException(String taskName, Exception e) {
        logger.error("Generic exception for {}: {}", taskName, e.getMessage());
        // 추가적인 예외 처리 로직을 여기에 추가할 수 있습니다.
    }

    @Scheduled(cron = "${scheduler.cron.roadwidth}")
    public void roadWidthDataCallApi() {
        processApiCall("roadWidthDataCallApi", slamsApiService::risRoadWidths, saveAfterDtoToEntity::insertTL_RIS_ROADWIDTH);
    }

    @Scheduled(cron = "${scheduler.cron.roughdistri}")
    public void roughDataCallApi() {
        processApiCall("roughDataCallApi", slamsApiService::risRoughDistris, saveAfterDtoToEntity::insertTL_RIS_ROUGH_DISTRI);
    }

    @Scheduled(cron = "${scheduler.cron.surface}")
    public void surfaceDataCallApi() {
        processApiCall("surfaceDataCallApi", slamsApiService::risSurfaces, saveAfterDtoToEntity::insertTL_RIS_SURFACE);
    }

    @Scheduled(cron = "${scheduler.cron.aadt}")
    public void tisDataCallApi() {
        processApiCall("tisDataCallApi", slamsApiService::tisAadts, saveAfterDtoToEntity::insertTL_TIS_AADT);
    }
}
