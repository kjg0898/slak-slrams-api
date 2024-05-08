package org.neighbor21.slakslramsapi.hendler;

import jakarta.annotation.PostConstruct;
import org.neighbor21.slakslramsapi.dto.TL_RIS_ROADWIDTHDTO;
import org.neighbor21.slakslramsapi.dto.TL_RIS_ROUGH_DISTRIDTO;
import org.neighbor21.slakslramsapi.dto.TL_RIS_SURFACEDTO;
import org.neighbor21.slakslramsapi.dto.TL_TIS_AADTDTO;
import org.neighbor21.slakslramsapi.service.SlamsApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

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


    @PostConstruct
    public void init() {
        // Initialization if needed
    }


    @Scheduled(cron = "*/5 * * * * * ")
    public void risDataCallApi() {

        try {
            //TL_RIS_ROADWIDTH api 호출
            List<TL_RIS_ROADWIDTHDTO> risRoadWidths = slamsApiService.risRoadWidths();
            ////*
            // insertTL_MVMNEQ_PASS 에서 한번에 배치 처리
            ////*


            //TL_RIS_ROUGH_DISTRI api 호출
            List<TL_RIS_ROUGH_DISTRIDTO> risRoughDistris = slamsApiService.risRoughDistris();
            ////*
            // insertTL_MVMNEQ_PASS 에서 한번에 배치 처리
            ////*


            //TL_RIS_SURFACE api 호출
            List<TL_RIS_SURFACEDTO> risSurfaces = slamsApiService.risSurfaces();
            ////*
            // insertTL_MVMNEQ_PASS 에서 한번에 배치 처리
            ////*


        } catch (Exception e) {
            logger.error("Failed to fetch and store RIS data : {}",e.getMessage());
        }
    }

    @Scheduled(cron = "*/5 * * * * * ")
    public void tisDataCallApi() {

        try {
            //TL_RIS_ROADWIDTH api 호출
            List<TL_TIS_AADTDTO> tisAadts = slamsApiService.tisAadts();
            ////*
            // insertTL_MVMNEQ_PASS 에서 한번에 배치 처리
            ////*


        } catch (Exception e) {
            logger.error("Failed to fetch and store TIS data : {}",e.getMessage());
        }
    }
}
