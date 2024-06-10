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

/**
 * packageName    : org.neighbor21.slakslramsapi.service
 * fileName       : SlamsApiService.java
 * author         : kjg08
 * date           : 24. 5. 2.
 * description    : 임의의 테스트 데이터를 생성하는 서비스 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 2.        kjg08           최초 생성
 */
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
     * 거칠기 분포 데이터를 생성하는 메서드
     *
     * @return 거칠기 분포 DTO 리스트
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

    /**
     * 도로 너비 테스트 데이터를 생성하는 메서드
     *
     * @return 도로 너비 DTO 리스트
     */
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

    /**
     * 거칠기 분포 테스트 데이터를 생성하는 메서드
     *
     * @return 거칠기 분포 DTO 리스트
     */
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

    /**
     * 표면 테스트 데이터를 생성하는 메서드
     *
     * @return 표면 DTO 리스트
     */
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

    /**
     * AADT 테스트 데이터를 생성하는 메서드
     *
     * @return AADT DTO 리스트
     */
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

    /**
     * 임의의 링크 코드를 생성하는 메서드
     *
     * @return 링크 코드 문자열
     */
    private String generateRandomLinkCode() {
        String[] linkCodes = {
                // 여기에 모든 링크 코드 값을 넣으세요.
                "LI:A0004_9:061.075", "LI:A0005_8:075.740", "LI:E0001:053.500", //...
        };
        return linkCodes[random.nextInt(linkCodes.length)];
    }

    /**
     * 임의의 도로 유형을 생성하는 메서드
     *
     * @return 도로 유형 문자열
     */
    private String generateRandomRoadType() {
        double[] roadTypes = {2.0, 1.5, 1.0, 4.0, 3.0, 6.0};
        return String.valueOf(roadTypes[random.nextInt(roadTypes.length)]);
    }

    /**
     * 임의의 너비 카테고리를 생성하는 메서드
     *
     * @return 너비 카테고리 문자열
     */
    private String generateRandomWidthCategory() {
        String[] widthCategories = {"이중 차선", "중간 차선", "단일 차선", "4차로", "3차로", "6차선"};
        return widthCategories[random.nextInt(widthCategories.length)];
    }

    /**
     * 임의의 거칠기 카테고리를 생성하는 메서드
     *
     * @return 거칠기 카테고리 문자열
     */
    private String generateRandomRoughnessCategory() {
        String[] roughnessCategories = {"Excellent", "Good", "Bad", "Poor", "Fair"};
        return roughnessCategories[random.nextInt(roughnessCategories.length)];
    }

    /**
     * 임의의 표면 유형을 생성하는 메서드
     *
     * @return 표면 유형 문자열
     */
    private String generateRandomSurfaceType() {
        String[] surfaceTypes = {"AC", "PM", "SD", "C", "G"};
        return surfaceTypes[random.nextInt(surfaceTypes.length)];
    }

    /**
     * 임의의 표면 설명을 생성하는 메서드
     *
     * @return 표면 설명 문자열
     */
    private String generateRandomSurfaceDescription() {
        String[] surfaceDescriptions = {"Asphalt Concrete", "Penetration Macadam", "Surface Dressing", "Concrete", "Gravel"};
        return surfaceDescriptions[random.nextInt(surfaceDescriptions.length)];
    }

    /**
     * 임의의 교통 카테고리를 생성하는 메서드
     *
     * @return 교통 카테고리 문자열
     */
    private String generateRandomTrafficCategory() {
        String[] trafficCategories = {"Very Low (0 - 750)", "Low (750 - 2500)", "Medium (2500 - 10000)", "High (10000 - 20000)", "Very High (20000 - 1000000)"};
        return trafficCategories[random.nextInt(trafficCategories.length)];
    }

    /**
     * 임의의 조사 연도를 생성하는 메서드
     *
     * @return 조사 연도 문자열
     */
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
///**
// * packageName    : org.neighbor21.slakslramsapi.service
// * fileName       : SlamsApiService.java
// * author         : kjg08
// * date           : 24. 5. 2.
// * description    : SLAMS API 호출 서비스 클래스
// * ===========================================================
// * DATE              AUTHOR             NOTE
// * -----------------------------------------------------------
// * 24. 5. 2.        kjg08           최초 생성
// */
//@Service
//public class SlamsApiService {
//    private static final Logger logger = LoggerFactory.getLogger(SlamsApiService.class);
//    private static final String TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
//
//    @Autowired
//    private CollectionDateTimeService collectionDateTimeService;
//
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
//    /**
//     * 공통 API 호출 메서드
//     *
//     * @param url            API URL
//     * @param timestampKey   타임스탬프 키
//     * @param typeReference  응답 타입 참조
//     * @param <T>            응답 타입
//     * @return 응답 데이터 리스트
//     * @throws UnirestException 예외 발생 시 던져짐
//     */
//    private <T> List<T> fetchApiData(String url, String timestampKey, TypeReference<List<T>> typeReference) throws UnirestException {
//        try {
//            // 가장 최신의 수집 일시를 파일에서 조회
//            Timestamp latestTimestamp = collectionDateTimeService.getLatestCollectionDateTime(timestampKey);
//            String formattedTimestamp = new SimpleDateFormat(TIMESTAMP_FORMAT).format(latestTimestamp);
//
//            // API 호출
//            HttpResponse<String> response = Unirest.post(url)
//                    .header("APIKEY", apiKey)
//                    .queryString("since", formattedTimestamp)
//                    .asString();
//
//            // 응답 데이터 처리
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
//    /**
//     * 도로 너비 데이터를 API에서 가져오는 메서드
//     *
//     * @return 도로 너비 DTO 리스트
//     * @throws UnirestException 예외 발생 시 던져짐
//     */
//    public List<TL_RIS_ROADWIDTHDTO> risRoadWidths() throws UnirestException {
//        return fetchApiData(roadWidthApiUrl, "roadwidth", new TypeReference<>() {});
//    }
//
//    /**
//     * 거칠기 분포 데이터를 API에서 가져오는 메서드
//     *
//     * @return 거칠기 분포 DTO 리스트
//     * @throws UnirestException 예외 발생 시 던져짐
//     */
//    public List<TL_RIS_ROUGH_DISTRIDTO> risRoughDistris() throws UnirestException {
//        return fetchApiData(roughDistriApiUrl, "roughDistri", new TypeReference<>() {});
//    }
//
//    /**
//     * 표면 데이터를 API에서 가져오는 메서드
//     *
//     * @return 표면 DTO 리스트
//     * @throws UnirestException 예외 발생 시 던져짐
//     */
//    public List<TL_RIS_SURFACEDTO> risSurfaces() throws UnirestException {
//        return fetchApiData(surfaceApiUrl, "surface", new TypeReference<>() {});
//    }
//
//    /**
//     * AADT 데이터를 API에서 가져오는 메서드
//     *
//     * @return AADT DTO 리스트
//     * @throws UnirestException 예외 발생 시 던져짐
//     */
//    public List<TL_TIS_AADTDTO> tisAadts() throws UnirestException {
//        return fetchApiData(aadtApiUrl, "aadt", new TypeReference<>() {});
//    }
//}
