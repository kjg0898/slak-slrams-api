package org.neighbor21.slakslramsapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.neighbor21.slakslramsapi.dto.TL_RIS_ROADWIDTHDTO;
import org.neighbor21.slakslramsapi.dto.TL_RIS_ROUGH_DISTRIDTO;
import org.neighbor21.slakslramsapi.dto.TL_RIS_SURFACEDTO;
import org.neighbor21.slakslramsapi.dto.TL_TIS_AADTDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * packageName    : org.neighbor21.slakslramsapi
 * fileName       : SlamsApiService.java
 * author         : kjg08
 * date           : 24. 5. 3.
 * description    : slams api 들을 호출하여 데이터를 가져오고 dto 에 저장 한다.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 3.        kjg08           최초 생성
 */
@Service
public class SlamsApiService {
    private static final Logger logger = LoggerFactory.getLogger(SlamsApiService.class);

    public SlamsApiService() {
        //서비스 인스턴스화 시 Unirest 구성 재설정
        resetUnirestConfig();
    }

    private void resetUnirestConfig() {
        Unirest.config().reset(); // Unirest 설정을 초기화
        Unirest.config().socketTimeout(5000).connectTimeout(5000); // 타임아웃 설정: 연결, 읽기 타임아웃을 5초로 설정
    }


    // RIS 도로 너비 정보 데이터 API 호출
    public List<TL_RIS_ROADWIDTHDTO> risRoadWidths() throws UnirestException {
        resetUnirestConfig();
        try {
            HttpResponse<String> response = Unirest.post("--------------------------------------------------------------------------------------------------")
                    .header("APIKEY", "Your API KEY")
                    .body("")
                    .asString();
            logger.debug(String.valueOf(response.getBody()));
            logger.debug(String.valueOf(response.getHeaders()));
            // 응답 데이터 body 반환
            if (response.getStatus() == 200) {
                List<TL_RIS_ROADWIDTHDTO> roadwidths = new ObjectMapper().readValue(response.getBody(), new TypeReference<List<TL_RIS_ROADWIDTHDTO>>() {
                });
                logger.info("response RIS_Road_width Data: {} ", roadwidths);
                return roadwidths;
            } else {
                logger.warn("Failed to fetch RIS_Road_width : HTTP {}", response.getStatus());
                return Collections.emptyList();
            }
        } catch (JsonProcessingException e) {
            logger.error("Error parsing the RIS_Road_width response", e);
            throw new RuntimeException("JSON parsing error", e);
        } catch (UnirestException e) {
            logger.error("Error fetching RIS_Road_width data", e);
            throw e;
        }
    }

    // RIS 거칠기 분포 정보 데이터 API 호출
    public List<TL_RIS_ROUGH_DISTRIDTO> risRoughDistris() throws UnirestException {
        resetUnirestConfig();
        try {
            HttpResponse<String> response = Unirest.post("--------------------------------------------------------------------------------------------------")
                    .header("APIKEY", "Your API KEY")
                    .body("")
                    .asString();
            logger.debug(String.valueOf(response.getBody()));
            logger.debug(String.valueOf(response.getHeaders()));
            // 응답 데이터 body 반환
            if (response.getStatus() == 200) {
                List<TL_RIS_ROUGH_DISTRIDTO> roughDistris = new ObjectMapper().readValue(response.getBody(), new TypeReference<List<TL_RIS_ROUGH_DISTRIDTO>>() {
                });
                logger.info("response RIS_Rough_Distri Data: {} ", roughDistris);
                return roughDistris;
            } else {
                logger.warn("Failed to fetch RIS_Rough_Distri : HTTP {}", response.getStatus());
                return Collections.emptyList();
            }
        } catch (JsonProcessingException e) {
            logger.error("Error parsing the RIS_Rough_Distri response", e);
            throw new RuntimeException("JSON parsing error", e);
        } catch (UnirestException e) {
            logger.error("Error fetching RIS_Rough_Distri data", e);
            throw e;
        }
    }

    // RIS 표면 유형 정보 데이터 API 호출
    public List<TL_RIS_SURFACEDTO> risSurfaces() throws UnirestException {
        resetUnirestConfig();
        try {
            HttpResponse<String> response = Unirest.post("--------------------------------------------------------------------------------------------------")
                    .header("APIKEY", "Your API KEY")
                    .body("")
                    .asString();
            logger.debug(String.valueOf(response.getBody()));
            logger.debug(String.valueOf(response.getHeaders()));
            // 응답 데이터 body 반환
            if (response.getStatus() == 200) {
                List<TL_RIS_SURFACEDTO> surfaces = new ObjectMapper().readValue(response.getBody(), new TypeReference<List<TL_RIS_SURFACEDTO>>() {
                });
                logger.info("response RIS_Surface Data: {} ", surfaces);
                return surfaces;
            } else {
                logger.warn("Failed to fetch RIS_Surface : HTTP {}", response.getStatus());
                return Collections.emptyList();
            }
        } catch (JsonProcessingException e) {
            logger.error("Error parsing the RIS_Surface response", e);
            throw new RuntimeException("JSON parsing error", e);
        } catch (UnirestException e) {
            logger.error("Error fetching RIS_Surface data", e);
            throw e;
        }
    }

    // TIS 교통분산 정보 데이터 API 호출
    public List<TL_TIS_AADTDTO> tisAadts() throws UnirestException {
        resetUnirestConfig();
        try {
            HttpResponse<String> response = Unirest.post("--------------------------------------------------------------------------------------------------")
                    .header("APIKEY", "Your API KEY")
                    .body("")
                    .asString();
            logger.debug(String.valueOf(response.getBody()));
            logger.debug(String.valueOf(response.getHeaders()));
            // 응답 데이터 body 반환
            if (response.getStatus() == 200) {
                List<TL_TIS_AADTDTO> aadts = new ObjectMapper().readValue(response.getBody(), new TypeReference<List<TL_TIS_AADTDTO>>() {
                });
                logger.info("response TIS_AADT Data: {} ", aadts);
                return aadts;
            } else {
                logger.warn("Failed to fetch TIS_AADT : HTTP {}", response.getStatus());
                return Collections.emptyList();
            }
        } catch (JsonProcessingException e) {
            logger.error("Error parsing the TIS_AADT response", e);
            throw new RuntimeException("JSON parsing error", e);
        } catch (UnirestException e) {
            logger.error("Error fetching TIS_AADT data", e);
            throw e;
        }
    }
}