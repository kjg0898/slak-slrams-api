package org.neighbor21.slakslramsapi.service;

import org.neighbor21.slakslramsapi.dto.TL_RIS_ROADWIDTHDTO;
import org.neighbor21.slakslramsapi.dto.TL_RIS_ROUGH_DISTRIDTO;
import org.neighbor21.slakslramsapi.dto.TL_RIS_SURFACEDTO;
import org.neighbor21.slakslramsapi.dto.TL_TIS_AADTDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SlamsApiService {
    private static final Random random = new Random();

    private static final int sampleDataNumber = 100000;

    /**
     * 도로 너비 데이터를 생성하는 메서드
     *
     * @return 도로 너비 DTO 리스트
     */
    public List<TL_RIS_ROADWIDTHDTO> risRoadWidths() {
        return generateTestRoadWidthData();
    }

    /**
     * 조잡한 분포 데이터를 생성하는 메서드
     *
     * @return 조잡한 분포 DTO 리스트
     */
    public List<TL_RIS_ROUGH_DISTRIDTO> risRoughDistris() {
        return generateTestRoughDistriData();
    }

    /**
     * 표면 데이터를 생성하는 메서드
     *
     * @return 표면 DTO 리스트
     */
    public List<TL_RIS_SURFACEDTO> risSurfaces() {
        return generateTestSurfaceData();
    }

    /**
     * AADT 데이터를 생성하는 메서드
     *
     * @return AADT DTO 리스트
     */
    public List<TL_TIS_AADTDTO> tisAadts() {
        return generateTestAadtData();
    }

    // 임의의 데이터를 생성하는 메서드들
    private List<TL_RIS_ROADWIDTHDTO> generateTestRoadWidthData() {
        List<TL_RIS_ROADWIDTHDTO> dataList = new ArrayList<>();
        for (int i = 0; i < sampleDataNumber; i++) {
            dataList.add(new TL_RIS_ROADWIDTHDTO(
                    generateRandomLinkCode(),
                    generateRandomRoadType(),
                    generateRandomWidthCategory(),
                    BigDecimal.valueOf(random.nextDouble() * 10),
                    generateRandomSurveyYear()
            ));
        }
        return dataList;
    }

    private List<TL_RIS_ROUGH_DISTRIDTO> generateTestRoughDistriData() {
        List<TL_RIS_ROUGH_DISTRIDTO> dataList = new ArrayList<>();
        for (int i = 0; i < sampleDataNumber; i++) {
            dataList.add(new TL_RIS_ROUGH_DISTRIDTO(
                    generateRandomLinkCode(),
                    generateRandomRoughnessCategory(),
                    BigDecimal.valueOf(random.nextDouble() * 10),
                    generateRandomSurveyYear()
            ));
        }
        return dataList;
    }

    private List<TL_RIS_SURFACEDTO> generateTestSurfaceData() {
        List<TL_RIS_SURFACEDTO> dataList = new ArrayList<>();
        for (int i = 0; i < sampleDataNumber; i++) {
            dataList.add(new TL_RIS_SURFACEDTO(
                    generateRandomLinkCode(),
                    generateRandomSurfaceType(),
                    generateRandomSurfaceDescription(),
                    BigDecimal.valueOf(random.nextDouble() * 10),
                    generateRandomSurveyYear()
            ));
        }
        return dataList;
    }

    private List<TL_TIS_AADTDTO> generateTestAadtData() {
        List<TL_TIS_AADTDTO> dataList = new ArrayList<>();
        for (int i = 0; i < sampleDataNumber; i++) {
            dataList.add(new TL_TIS_AADTDTO(
                    generateRandomLinkCode(),
                    BigInteger.valueOf(random.nextInt(1000000)),
                    generateRandomTrafficCategory(),
                    BigDecimal.valueOf(random.nextDouble() * 10),
                    generateRandomSurveyYear()
            ));
        }
        return dataList;
    }

    // 임의의 데이터 생성을 위한 헬퍼 메서드들
    private String generateRandomLinkCode() {
        String[] linkCodes = {"LI:A0004_9:061.075", "LI:A0005_8:075.740", "LI:E0001:053.500", "LI:A0004_1:000.000"};
        return linkCodes[random.nextInt(linkCodes.length)];
    }

    private String generateRandomRoadType() {
        double[] roadTypes = {2.0, 1.5, 1.0, 4.0, 3.0, 6.0};
        return String.valueOf(roadTypes[random.nextInt(roadTypes.length)]);
    }

    private String generateRandomWidthCategory() {
        String[] widthCategories = {"이중 차선", "중간 차선", "단일 차선", "4차로", "3차로", "6차선"};
        return widthCategories[random.nextInt(widthCategories.length)];
    }

    private String generateRandomRoughnessCategory() {
        String[] roughnessCategories = {"Excellent", "Good", "Bad", "Poor", "Fair"};
        return roughnessCategories[random.nextInt(roughnessCategories.length)];
    }

    private String generateRandomSurfaceType() {
        String[] surfaceTypes = {"AC", "PM", "SD", "C", "G"};
        return surfaceTypes[random.nextInt(surfaceTypes.length)];
    }

    private String generateRandomSurfaceDescription() {
        String[] surfaceDescriptions = {"Asphalt Concrete", "Penetration Macadam", "Surface Dressing", "Concrete", "Gravel"};
        return surfaceDescriptions[random.nextInt(surfaceDescriptions.length)];
    }

    private String generateRandomTrafficCategory() {
        String[] trafficCategories = {"Very Low (0 - 750)", "Low (750 - 2500)", "Medium (2500 - 10000)", "High (10000 - 20000)", "Very High (20000 - 1000000)"};
        return trafficCategories[random.nextInt(trafficCategories.length)];
    }

    private String generateRandomSurveyYear() {
        int year = random.nextInt(2024 - 2011 + 1) + 2011;
        return String.valueOf(year);
    }
}


//package org.neighbor21.slakslramsapi.service;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import kong.unirest.HttpResponse;
//import kong.unirest.Unirest;
//import kong.unirest.UnirestException;
//import org.neighbor21.slakslramsapi.dto.TL_RIS_ROADWIDTHDTO;
//import org.neighbor21.slakslramsapi.dto.TL_RIS_ROUGH_DISTRIDTO;
//import org.neighbor21.slakslramsapi.dto.TL_RIS_SURFACEDTO;
//import org.neighbor21.slakslramsapi.dto.TL_TIS_AADTDTO;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.sql.Timestamp;
//import java.text.SimpleDateFormat;
//import java.util.Collections;
//import java.util.List;
//
//
///**
// * packageName    : org.neighbor21.slakslramsapi
// * fileName       : SlamsApiService.java
// * author         : kjg08
// * date           : 24. 5. 3.
// * description    : slams api 들을 호출하여 데이터를 가져오고 dto 에 저장 한다.
// * ===========================================================
// * DATE              AUTHOR             NOTE
// * -----------------------------------------------------------
// * 24. 5. 3.        kjg08           최초 생성
// */
//
//
//@Service
//public class SlamsApiService {
//    private static final Logger logger = LoggerFactory.getLogger(SlamsApiService.class);
//    private static final String TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
//
//    @Autowired
//    private CollectionDateTimeService collectionDateTimeService;
//
//    // api 호출 url 을 설정파일에 넣을거면 사용
//    @Value("${api.url.roadwidth}")
//    private String roadWidthApiUrl;
//
//    @Value("${api.url.roughdistri}")
//    private String roughDistriApiUrl;
//
//    @Value("${api.url.surface}")
//    private String surfaceApiUrl;
//
//    @Value("${api.url.aadt}")
//    private String aadtApiUrl;
//
//    @Value("${api.key}")
//    private String apiKey;
//
//
//    // 공통 API 호출 메소드
//    private <T> List<T> fetchApiData(String url, String timestampKey, TypeReference<List<T>> typeReference) throws UnirestException {
//        try {
//            //가장 최신의 수집일시를 디비에서 조회하여 값을 가져오기 위해 (배치처리 or jpa 에서 saveall 할때 셀렉트 하는걸 무시할것이기 때문에 중복값이 들어갈수 있으므로 이전에 이미 조회한것을 조회하지 않도록하기 위해서)
//            Timestamp latestTimestamp = collectionDateTimeService.getLatestCollectionDateTime(timestampKey);
//            String formattedTimestamp = new SimpleDateFormat(TIMESTAMP_FORMAT).format(latestTimestamp);//시간값 포맷팅
//
//            HttpResponse<String> response = Unirest.post(url)
//                    .header("APIKEY", apiKey)
//                    .queryString("since", formattedTimestamp) // 수집일시 이후의 데이터만 요청
//                    .asString();
//
//            // 응답 데이터 body 반환
//            if (response.getStatus() == 200) {
//                return new ObjectMapper().readValue(response.getBody(), typeReference);
//            } else {
//                logger.warn("Failed to fetch data from {}: HTTP {} - {}", url, response.getStatus(), response.getStatusText());
//                return Collections.emptyList();
//            }
//        } catch (JsonProcessingException e) {
//            logger.error("Error parsing response from {}: {}", url, e.getMessage(), e);
//            throw new RuntimeException("JSON parsing error", e);
//        } catch (UnirestException e) {
//            if (e.getCause() instanceof java.net.UnknownHostException) {
//                logger.error("Unknown host: {} - Please check the URL or network connection", url, e);
//            } else {
//                logger.error("Error fetching data from {}: {}", url, e.getMessage(), e);
//            }
//            throw e;
//        }
//    }
//
//
//    //*
//    //url,시간 키값 전달, TypeReference : 각 타입으로 json 데이터를 파싱
//    //*
//    // RIS 도로 너비 정보 데이터 API 호출
//    public List<TL_RIS_ROADWIDTHDTO> risRoadWidths() throws UnirestException {
//        return fetchApiData(roadWidthApiUrl, "roadwidth", new TypeReference<>() {
//        });
//    }
//
//    // RIS 거칠기 분포 정보 데이터 API 호출
//    public List<TL_RIS_ROUGH_DISTRIDTO> risRoughDistris() throws UnirestException {
//        return fetchApiData(roughDistriApiUrl, "roughDistri", new TypeReference<>() {
//        });
//    }
//
//    // RIS 표면 유형 정보 데이터 API 호출
//    public List<TL_RIS_SURFACEDTO> risSurfaces() throws UnirestException {
//        return fetchApiData(surfaceApiUrl, "surface", new TypeReference<>() {
//        });
//    }
//
//    // TIS 교통분산 정보 데이터 API 호출
//    public List<TL_TIS_AADTDTO> tisAadts() throws UnirestException {
//        return fetchApiData(aadtApiUrl, "aadt", new TypeReference<>() {
//        });
//    }
//}
