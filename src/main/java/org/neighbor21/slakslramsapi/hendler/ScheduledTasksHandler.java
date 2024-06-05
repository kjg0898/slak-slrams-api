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

    private <T> void processApiCall(String taskName, Supplier<List<T>> apiCall, Consumer<List<T>> saveMethod) {
        logger.info("Starting {}", taskName);
        long startTime = System.currentTimeMillis();
        try {
            List<T> dataList = apiCall.get();
            logger.info("{} API call returned {} records", taskName, dataList.size());
            if (!dataList.isEmpty()) {
                saveMethod.accept(dataList);
                logger.info("{} records saved to database", dataList.size());
            }
            long endTime = System.currentTimeMillis();
            logger.info("Completed {} successfully in {} ms", taskName, (endTime - startTime));
        } catch (Exception e) {
            logger.error("Failed to execute {}: {}", taskName, e.getMessage(), e);
        }
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
