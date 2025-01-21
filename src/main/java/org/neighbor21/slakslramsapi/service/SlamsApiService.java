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
 * description    : Service class for generating random test data
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 2.        kjg08           Initial creation
 */
@Service
public class SlamsApiService {
    private static final Random random = new Random();
    private static final int sampleDataNumber = 100000;

    /**
     * Generates road width data
     *
     * @return List of road width DTOs
     */
    public List<TL_RIS_ROADWIDTHDTO> risRoadWidths() {
        return generateTestRoadWidthData();
    }

    /**
     * Generates roughness distribution data
     *
     * @return List of roughness distribution DTOs
     */
    public List<TL_RIS_ROUGH_DISTRIDTO> risRoughDistris() {
        return generateTestRoughDistriData();
    }

    /**
     * Generates surface data
     *
     * @return List of surface DTOs
     */
    public List<TL_RIS_SURFACEDTO> risSurfaces() {
        return generateTestSurfaceData();
    }

    /**
     * Generates AADT data
     *
     * @return List of AADT DTOs
     */
    public List<TL_TIS_AADTDTO> tisAadts() {
        return generateTestAadtData();
    }

    // Methods for generating random data

    /**
     * Generates test data for road width
     *
     * @return List of road width DTOs
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
     * Generates test data for roughness distribution
     *
     * @return List of roughness distribution DTOs
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
     * Generates test data for surface
     *
     * @return List of surface DTOs
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
     * Generates test data for AADT
     *
     * @return List of AADT DTOs
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


    /**
     * Generate a random link code
     *
     * @return Link code string
     */
    private String generateRandomLinkCode() {
        String[] linkCodes = {
                "LI:A0004_9:061.075", "LI:A0005_8:075.740", "LI:E0001:053.500",
        };
        return linkCodes[random.nextInt(linkCodes.length)];
    }

    /**
     * Generate a random road type
     *
     * @return Road type string
     */
    private String generateRandomRoadType() {
        double[] roadTypes = {2.0, 1.5, 1.0, 4.0, 3.0, 6.0};
        return String.valueOf(roadTypes[random.nextInt(roadTypes.length)]);
    }

    /**
     * Generate a random width category
     *
     * @return Width category string
     */
    private String generateRandomWidthCategory() {
        String[] widthCategories = {"이중 차선", "중간 차선", "단일 차선", "4차로", "3차로", "6차선"};
        return widthCategories[random.nextInt(widthCategories.length)];
    }

    /**
     * Generate a random roughness category
     *
     * @return Roughness category string
     */
    private String generateRandomRoughnessCategory() {
        String[] roughnessCategories = {"Excellent", "Good", "Bad", "Poor", "Fair"};
        return roughnessCategories[random.nextInt(roughnessCategories.length)];
    }

    /**
     * Generate a random surface type
     *
     * @return Surface type string
     */
    private String generateRandomSurfaceType() {
        String[] surfaceTypes = {"AC", "PM", "SD", "C", "G"};
        return surfaceTypes[random.nextInt(surfaceTypes.length)];
    }

    /**
     * Generate a random surface description
     *
     * @return Surface description string
     */
    private String generateRandomSurfaceDescription() {
        String[] surfaceDescriptions = {"Asphalt Concrete", "Penetration Macadam", "Surface Dressing", "Concrete", "Gravel"};
        return surfaceDescriptions[random.nextInt(surfaceDescriptions.length)];
    }

    /**
     * Generate a random traffic category
     *
     * @return Traffic category string
     */
    private String generateRandomTrafficCategory() {
        String[] trafficCategories = {"Very Low (0 - 750)", "Low (750 - 2500)", "Medium (2500 - 10000)", "High (10000 - 20000)", "Very High (20000 - 1000000)"};
        return trafficCategories[random.nextInt(trafficCategories.length)];
    }

    /**
     * Generate a random survey year
     *
     * @return Survey year string
     */
    private String generateRandomSurveyYear() {
        int year = random.nextInt(2024 - 2011 + 1) + 2011;
        return String.valueOf(year);
    }
}

