package org.neighbor21.slakslramsapi.service;

import org.neighbor21.slakslramsapi.dto.TL_RIS_ROADWIDTHDTO;
import org.neighbor21.slakslramsapi.dto.TL_RIS_ROUGH_DISTRIDTO;
import org.neighbor21.slakslramsapi.dto.TL_RIS_SURFACEDTO;
import org.neighbor21.slakslramsapi.dto.TL_TIS_AADTDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SlamsApiService {
    private static final Random random = new Random();

    private static final int sampleDataNumber = 1;

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