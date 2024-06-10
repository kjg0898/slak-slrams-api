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
        String[] linkCodes = {
                "LI:A0004_9:061.075","LI:A0005_8:075.740","LI:E0001:053.500","LI:A0004_1:000.000","LI:A0000_1:000.000","LI:A0001_1:000.000","LI:A0001_9:055.900","LI:A0001_2:098.710","LI:A0002_1:000.000","LI:A0002_3:061.080","LI:A0002_8:281.940","LI:A0003_1:000.000","LI:A0003_6:037.900","LI:A0004_8:167.800","LI:A0004_10:293.020","LI:A0004_5:391.430","LI:A0005_2:000.000","LI:A0006_9:000.000","LI:A0006_6:007.070","LI:A0006_2:068.920","LI:A0006_7:107.740","LI:A0006_5:147.190","LI:A0007_1:000.000","LI:A0007_9:000.650","LI:A0007_2:041.130","LI:A0008_1:000.000","LI:A0008_9:036.370","LI:A0009_2:000.000","LI:A0009_7:076.540","LI:A0009_4:168.170","LI:A0010_2:000.000","LI:A0010_6:016.140","LI:A0011_7:000.000","LI:A0011_5:108.820","LI:A0012_6:000.000","LI:A0012_7:037.270","LI:A0012_5:142.815","LI:A0013_7:000.000","LI:A0014_7:000.000","LI:A0014_4:014.500","LI:A0015_5:000.000","LI:A0016_8:000.000","LI:A0017_3:000.000","LI:A0017_9:086.490","LI:A0018_9:000.000","LI:A0018_3:068.060","LI:A0019_6:000.000","LI:A0019_9:002.925","LI:A0020_7:000.000","LI:A0021_9:000.000","LI:A0022_8:000.000","LI:A0023_8:000.000","LI:A0024_3:000.000","LI:A0025_8:000.000","LI:A0025_10:024.070","LI:A0026_2:000.000","LI:A0026_8:071.180","LI:A0026_10:097.780","LI:A0027_10:000.000","LI:A0028_6:000.000","LI:A0028_7:054.135","LI:A0029_4:000.000","LI:A0029_7:009.645","LI:A0030_4:000.000","LI:A0031_10:000.000","LI:A0032_4:000.000","LI:A0033_1:000.000","LI:A0034_4:000.000","LI:A0035_4:000.000","LI:B0001_10:000.000","LI:B0002_10:000.000","LI:B0003_3:000.000","LI:B0004_3:000.000","LI:B0005_2:000.000","LI:B0006_2:000.000","LI:B0007_2:000.000","LI:B0008_6:000.000","LI:B0009_6:000.000","LI:B0010_5:000.000","LI:B0011_1:000.000","LI:B0012_1:000.000","LI:B0013_4:000.000","LI:B0014_3:000.000","LI:B0015_2:000.000","LI:B0016_10:000.000","LI:B0018_5:000.000","LI:B0018_10:014.865","LI:B0019_6:000.000","LI:B0021_3:000.000","LI:B0024_1:000.000","LI:B0025_2:000.000","LI:B0028_7:000.000","LI:B0031_10:000.000","LI:B0032_1:000.000","LI:B0033_4:000.000","LI:B0034_9:000.000","LI:B0035_8:000.000","LI:B0036_8:000.000","LI:B0037_3:000.000","LI:B0038_9:000.000","LI:B0039_9:000.000","LI:B0040_3:000.000","LI:B0041_3:000.000","LI:B0042_8:000.000","LI:B0043_8:000.000","LI:B0044_8:000.000","LI:B0045_6:000.000","LI:B0046_5:000.000","LI:B0047_1:000.000","LI:B0048_7:000.000","LI:B0049_3:000.000","LI:B0050_1:000.000","LI:B0051_6:000.000","LI:B0052_4:000.000","LI:B0053_4:000.000","LI:B0054_3:000.000","LI:B0055_3:000.000","LI:B0056_8:000.000","LI:B0057_8:000.000","LI:B0058_1:000.000","LI:B0059_6:000.000","LI:B0060_7:000.000","LI:B0060_5:012.100","LI:B0061_3:000.000","LI:B0062_1:000.000","LI:B0063_1:000.000","LI:B0064_2:000.000","LI:B0065_6:000.000","LI:B0066_2:000.000","LI:B0067_9:000.000","LI:B0068_1:000.000","LI:B0069_2:000.000","LI:B0070_2:000.000","LI:B0071_2:000.000","LI:B0072_1:000.000","LI:B0073_10:000.000","LI:B0074_4:000.000","LI:B0075_4:000.000","LI:B0076_4:000.000","LI:B0077_10:000.000","LI:B0078_1:000.000","LI:B0079_6:000.000","LI:B0080_4:000.000","LI:B0081_4:000.000","LI:B0082_6:000.000","LI:B0083_3:000.000","LI:B0084_1:000.000","LI:B0085_7:000.000","LI:B0086_8:000.000","LI:B0087_6:000.000","LI:B0088_6:000.000","LI:B0089_6:000.000","LI:B0090_1:000.000","LI:B0091_2:000.000","LI:B0092_10:000.000","LI:B0093_9:000.000","LI:B0094_1:000.000","LI:B0095_1:000.000","LI:B0096_1:000.000","LI:B0097_8:000.000","LI:B0098_3:000.000","LI:B0099_3:000.000","LI:B0101_3:000.000","LI:B0102_8:000.000","LI:B0103_8:000.000","LI:B0104_8:000.000","LI:B0105_8:000.000","LI:B0106_3:000.000","LI:B0107_3:000.000","LI:B0108_1:000.000","LI:B0109_3:000.000","LI:B0110_9:000.000","LI:B0111_1:000.000","LI:B0112_7:000.000","LI:B0114_3:000.000","LI:B0114_1:009.290","LI:B0115_9:000.000","LI:B0116_2:000.000","LI:B0117_7:000.000","LI:B0118_2:000.000","LI:B0119_4:000.000","LI:B0120_1:000.000","LI:B0121_2:000.000","LI:B0122_2:000.000","LI:B0123_1:000.000","LI:B0124_9:000.000","LI:B0125_2:000.000","LI:B0126_6:000.000","LI:B0127_9:000.000","LI:B0128_3:000.000","LI:B0129_3:000.000","LI:B0130_3:000.000","LI:B0132_2:000.000","LI:B0133_7:000.000","LI:B0134_1:000.000","LI:B0135_2:000.000","LI:B0136_9:000.000","LI:B0137_6:000.000","LI:B0138_3:000.000","LI:B0139_3:000.000","LI:B0141_3:000.000","LI:B0142_3:000.000","LI:B0143_3:000.000","LI:B0144_1:000.000","LI:B0145_1:000.000","LI:B0146_1:000.000","LI:B0147_8:000.000","LI:B0148_2:000.000","LI:B0149_2:000.000","LI:B0150_2:000.000","LI:B0151_1:000.000","LI:B0152_1:000.000","LI:B0153_3:000.000","LI:B0154_2:000.000","LI:B0155_7:000.000","LI:B0156_3:000.000","LI:B0157_1:000.000","LI:B0158_1:000.000","LI:B0158_3:020.790","LI:B0160_9:000.000","LI:B0161_1:000.000","LI:B0162_2:000.000","LI:B0163_1:000.000","LI:B0164_7:000.000","LI:B0165_4:000.000","LI:B0166_6:000.000","LI:B0167_6:000.000","LI:B0168_1:000.000","LI:B0169_1:000.000","LI:B0170_4:000.000","LI:B0172_2:000.000","LI:B0173_2:000.000","LI:B0174_1:000.000","LI:B0176_3:000.000","LI:B0177_6:000.000","LI:B0178_6:000.000","LI:B0179_1:000.000","LI:B0180_2:000.000","LI:B0181_9:000.000","LI:B0182_7:000.000","LI:B0183_1:000.000","LI:B0184_5:000.000","LI:B0185_5:000.000","LI:B0186_10:000.000","LI:B0187_10:000.000","LI:B0188_1:000.000","LI:B0189_2:000.000","LI:B0190_6:000.000","LI:B0191_1:000.000","LI:B0192_4:000.000","LI:B0194_2:000.000","LI:B0195_2:000.000","LI:B0196_5:000.000","LI:B0197_4:000.000","LI:B0198_4:000.000","LI:B0199_9:000.000","LI:B0201_6:000.000","LI:B0202_3:000.000","LI:B0203_4:000.000","LI:B0204_1:000.000","LI:B0205_2:000.000","LI:B0206_2:000.000","LI:B0207_1:000.000","LI:B0208_1:000.000","LI:B0209_7:000.000","LI:B0210_7:000.000","LI:B0211_7:000.000","LI:B0212_7:000.000","LI:B0213_7:000.000","LI:B0214_1:000.000","LI:B0215_1:000.000","LI:B0216_1:000.000","LI:B0217_3:000.000","LI:B0218_2:000.000","LI:B0219_1:000.000","LI:B0220_1:000.000","LI:B0221_1:000.000","LI:B0222_9:000.000","LI:B0223_9:000.000","LI:B0224_1:000.000","LI:B0225_1:000.000","LI:B0226_1:000.000","LI:B0227_6:000.000","LI:B0228_1:000.000","LI:B0229_1:000.000","LI:B0230_4:000.000","LI:B0231_1:000.000","LI:B0232_1:000.000","LI:B0233_3:000.000","LI:B0234_7:000.000","LI:B0235_1:000.000","LI:B0236_8:000.000","LI:B0237_1:000.000","LI:B0238_3:000.000","LI:B0239_1:000.000","LI:B0240_1:000.000","LI:B0241_1:000.000","LI:B0242_2:000.000","LI:B0243_6:000.000","LI:B0244_6:000.000","LI:B0245_1:000.000","LI:B0246_1:000.000","LI:B0247_6:000.000","LI:B0248_3:000.000","LI:B0249_2:000.000","LI:B0250_5:000.000","LI:B0251_3:000.000","LI:B0252_2:000.000","LI:B0253_6:000.000","LI:B0254_1:000.000","LI:B0255_6:000.000","LI:B0256_2:000.000","LI:B0257_2:000.000","LI:B0258_6:000.000","LI:B0259_3:000.000","LI:B0260_4:000.000","LI:B0261_1:000.000","LI:B0262_1:000.000","LI:B0263_1:000.000","LI:B0264_6:000.000","LI:B0265_9:000.000","LI:B0266_10:000.000","LI:B0267_1:000.000","LI:B0268_4:000.000","LI:B0269_4:000.000","LI:B0270_4:000.000","LI:B0271_1:000.000","LI:B0272_6:000.000","LI:B0273_2:000.000","LI:B0274_2:000.000","LI:B0275_3:000.000","LI:B0276_4:000.000","LI:B0277_4:000.000","LI:B0278_9:000.000","LI:B0279_9:000.000","LI:B0279_2:020.508","LI:B0280_6:000.000","LI:B0281_6:000.000","LI:B0282_7:000.000","LI:B0283_7:000.000","LI:B0284_3:000.000","LI:B0285_1:000.000","LI:B0286_3:000.000","LI:B0286_9:003.485","LI:B0287_7:000.000","LI:B0288_1:000.000","LI:B0289_1:000.000","LI:B0290_1:000.000","LI:B0291_1:000.000","LI:B0292_1:000.000","LI:B0293_8:000.000","LI:B0294_7:000.000","LI:B0295_1:000.000","LI:B0296_4:000.000","LI:B0297_4:000.000","LI:B0298_5:000.000","LI:B0299_4:000.000","LI:B0300_6:000.000","LI:B0301_4:000.000","LI:B0302_1:000.000","LI:B0304_1:000.000","LI:B0305_4:000.000","LI:B0306_2:000.000","LI:B0307_1:000.000","LI:B0308_6:000.000","LI:B0309_3:000.000","LI:B0310_1:000.000","LI:B0311_2:000.000","LI:B0312_2:000.000","LI:B0312_7:022.260","LI:B0312_2:036.100","LI:B0315_4:000.000","LI:B0316_1:000.000","LI:B0317_2:000.000","LI:B0318_2:000.000","LI:B0319_2:000.000","LI:B0320_3:000.000","LI:B0321_1:000.000","LI:B0322_1:000.000","LI:B0323_1:000.000","LI:B0324_1:000.000","LI:B0325_4:000.000","LI:B0326_6:000.000","LI:B0328_2:000.000","LI:B0329_2:000.000","LI:B0330_2:000.000","LI:B0332_2:000.000","LI:B0332_8:041.610","LI:B0333_5:000.000","LI:B0334_4:000.000","LI:B0335_1:000.000","LI:B0337_5:000.000","LI:B0338_3:000.000","LI:B0339_9:000.000","LI:B0339_2:029.770","LI:B0340_5:000.000","LI:B0341_7:000.000","LI:B0342_7:000.000","LI:B0343_7:000.000","LI:B0344_5:000.000","LI:B0345_1:000.000","LI:B0346_2:000.000","LI:B0347_5:000.000","LI:B0348_6:000.000","LI:B0349_6:000.000","LI:B0350_10:000.000","LI:B0351_9:000.000","LI:B0352_4:000.000","LI:B0353_8:000.000","LI:B0354_1:000.000","LI:B0355_10:000.000","LI:B0356_6:000.000","LI:B0357_4:000.000","LI:B0358_9:000.000","LI:B0359_8:000.000","LI:B0360_8:000.000","LI:B0361_1:000.000","LI:B0363_1:000.000","LI:B0363_3:023.950","LI:B0364_2:000.000","LI:B0365_2:000.000","LI:B0366_5:000.000","LI:B0367_1:000.000","LI:B0368_1:000.000","LI:B0369_2:000.000","LI:B0370_4:000.000","LI:B0371_4:000.000","LI:B0372_8:000.000","LI:B0373_7:000.000","LI:B0374_10:000.000","LI:B0375_5:000.000","LI:B0376_3:000.000","LI:B0377_2:000.000","LI:B0378_4:000.000","LI:B0379_6:000.000","LI:B0380_4:000.000","LI:B0381_2:000.000","LI:B0382_1:000.000","LI:B0383_9:000.000","LI:B0384_9:000.000","LI:B0385_9:000.000","LI:B0386_1:000.000","LI:B0387_3:000.000","LI:B0388_1:000.000","LI:B0389_1:000.000","LI:B0390_9:000.000","LI:B0391_9:000.000","LI:B0392_2:000.000","LI:B0393_6:000.000","LI:B0396_8:000.000","LI:B0398_4:000.000","LI:B0399_4:000.000","LI:B0400_1:000.000","LI:B0401_1:000.000","LI:B0402_4:000.000","LI:B0403_4:000.000","LI:B0404_1:000.000","LI:B0405_1:000.000","LI:B0406_2:000.000","LI:B0407_1:000.000","LI:B0408_9:000.000","LI:B0408_1:013.800","LI:B0409_6:000.000","LI:B0409_2:014.975","LI:B0410_3:000.000","LI:B0411_3:000.000","LI:B0412_2:000.000","LI:B0413_2:000.000","LI:B0414_4:000.000","LI:B0415_3:000.000","LI:B0416_1:000.000","LI:B0417_4:000.000","LI:B0418_4:000.000","LI:B0419_6:000.000","LI:B0420_4:000.000","LI:B0421_9:000.000","LI:B0421_1:046.050","LI:B0422_3:000.000","LI:B0423_7:000.000","LI:B0423_2:032.680","LI:B0424_5:000.000","LI:B0425_1:000.000","LI:B0426_1:000.000","LI:B0427_9:000.000","LI:B0427_8:004.920","LI:B0428_6:000.000","LI:B0429_3:000.000","LI:B0430_1:000.000","LI:B0431_2:000.000","LI:B0432_6:000.000","LI:B0433_5:000.000","LI:B0434_1:000.000","LI:B0435_1:000.000","LI:B0436_4:000.000","LI:B0437_4:000.000","LI:B0438_4:000.000","LI:B0439_10:000.000","LI:B0440_10:000.000","LI:B0441_4:000.000","LI:B0442_4:000.000","LI:B0443_1:000.000","LI:B0444_1:000.000","LI:B0445_1:000.000","LI:B0445_9:017.680","LI:B0446_1:000.000","LI:B0447_5:000.000","LI:B0448_6:000.000","LI:B0449_1:000.000","LI:B0450_3:000.000","LI:B0451_1:000.000","LI:B0452_1:000.000","LI:B0453_1:000.000","LI:B0454_3:000.000","LI:B0455_3:000.000","LI:B0456_9:000.000","LI:B0456_1:002.500","LI:B0457_9:000.000","LI:B0458_1:000.000","LI:B0460_1:000.000","LI:B0461_2:000.000","LI:B0462_2:000.000","LI:B0463_3:000.000","LI:B0464_3:000.000","LI:B0465_3:000.000","LI:B0466_3:000.000","LI:B0467_9:000.000","LI:B0468_1:000.000","LI:B0469_1:000.000","LI:B0470_1:000.000","LI:B0471_8:000.000","LI:B0472_1:000.000","LI:B0473_6:000.000","LI:B0474_2:000.000","LI:B0475_6:000.000","LI:B0475_9:004.080","LI:B0476_1:000.000","LI:B0477_9:000.000","LI:B0479_1:000.000","LI:B0480_3:000.000","LI:B0481_1:000.000","LI:B0482_9:000.000","LI:B0483_10:000.000","LI:B0484_2:000.000","LI:B0485_3:000.000","LI:B0486_9:000.000","LI:B0486_3:012.160","LI:B0487_6:000.000","LI:B0488_7:000.000","LI:B0489_2:000.000","LI:B0490_2:000.000","LI:B0491_2:000.000","LI:B0492_2:000.000","LI:B0492_8:045.170","LI:B0493_2:000.000","LI:B0494_2:000.000","LI:B0495_9:000.000","LI:B0496_8:000.000","LI:B0497_8:000.000","LI:B0498_8:000.000","LI:B0499_3:000.000","LI:B0500_3:000.000","LI:B0501_7:000.000","LI:B0502_7:000.000","LI:B0503_6:000.000","LI:B0503_1:002.330","LI:B0504_1:000.000","LI:B0505_2:000.000","LI:B0506_2:000.000","LI:B0507_8:000.000","LI:B0508_8:000.000","LI:B0509_7:000.000","LI:B0510_1:000.000","LI:B0511_2:000.000","LI:B0512_2:000.000","LI:B0513_8:000.000","LI:B0515_7:000.000","LI:B0516_6:000.000","LI:B0517_10:000.000","LI:B0517_7:008.100","LI:B0518_2:000.000","LI:B0519_2:000.000","LI:B0520_2:000.000","LI:B0521_2:000.000","LI:B0522_8:000.000","LI:B0523_3:000.000","LI:B0524_3:000.000","LI:B0525_3:000.000","LI:B0526_3:000.000","LI:B0527_8:000.000","LI:B0527_10:049.740","LI:B0528_8:000.000","LI:B0528_9:040.100","LI:B0529_7:000.000","LI:B0530_1:000.000","LI:B0533_1:000.000","LI:B0534_1:000.000","LI:B0536_3:000.000","LI:B0537_2:000.000","LI:B0538_7:000.000","LI:B0539_9:000.000","LI:B0540_9:000.000","LI:B0541_5:000.000","LI:B0542_5:000.000","LI:B0543_5:000.000","LI:B0544_1:000.000","LI:B0545_1:000.000","LI:B0546_1:000.000","LI:B0547_1:000.000","LI:B0548_3:000.000","LI:B0549_9:000.000","LI:B0549_8:005.700","LI:B0550_2:000.000","LI:B0551_2:000.000","LI:B0552_7:000.000","LI:B0553_1:000.000","LI:B0554_1:000.000","LI:B0555_3:000.000","LI:B0556_7:000.000","LI:B0557_3:000.000","LI:B0558_3:000.000","LI:B0559_3:000.000","LI:B0560_1:000.000","LI:B0561_2:000.000","LI:B0562_3:000.000","LI:B0563_3:000.000","LI:B0563_9:008.380","LI:B0564_7:000.000","LI:B0565_3:000.000","LI:B0566_3:000.000","LI:B0567_3:000.000","LI:B0568_7:000.000","LI:B0569_1:000.000","LI:B0570_1:000.000","LI:B0571_10:000.000","LI:B0572_10:000.000","LI:B0573_10:000.000","LI:B0574_10:000.000","LI:B0575_10:000.000","LI:B0576_10:000.000","LI:B0577_8:000.000","LI:B0577_2:006.320","LI:B0578_3:000.000","LI:B0579_7:000.000","LI:B0580_8:000.000","LI:B0581_8:000.000","LI:B0582_2:000.000","LI:B0583_9:000.000","LI:B0584_1:000.000","LI:B0585_1:000.000","LI:B0586_2:000.000","LI:B0587_3:000.000","LI:B0587_8:007.165","LI:B0588_9:000.000","LI:B0589_9:000.000","LI:B0590_10:000.000","LI:B0591_2:000.000","LI:B0593_9:000.000","LI:B0594_3:000.000","LI:B0595_3:000.000","LI:B0596_1:000.000","LI:B0597_1:000.000","LI:B0598_1:000.000","LI:B0599_9:000.000","LI:B0600_9:000.000","LI:B0601_9:000.000","LI:B0603_9:000.000","LI:B0604_9:000.000","LI:B0605_9:000.000","LI:B0606_3:000.000","LI:B0607_3:000.000","LI:B0608_2:000.000","LI:B0609_6:000.000","LI:B0610_6:000.000","LI:B0611_6:000.000","LI:B0613_2:000.000","LI:B0614_6:000.000","LI:B0615_2:000.000","LI:B0615_7:018.115","LI:B0616_7:000.000","LI:B0617_7:000.000","LI:B0618_5:000.000","LI:B0619_5:000.000","LI:B0620_5:000.000","LI:B0621_2:000.000","LI:B0622_3:000.000","LI:B0623_3:000.000","LI:B0624_3:000.000","LI:B0625_3:000.000","LI:B0626_3:000.000","LI:B0627_3:000.000","LI:B0628_3:000.000","LI:B0629_3:000.000","LI:B0631_3:000.000","LI:B0632_1:000.000","LI:B0633_1:000.000","LI:B0634_1:000.000","LI:B0635_3:000.000","LI:B0636_3:000.000","LI:B0637_3:000.000","LI:B0638_7:000.000","LI:B0639_1:000.000","LI:B0640_6:000.000","LI:B0641_1:000.000","LI:B0642_1:000.000","LI:B0643_9:000.000","LI:B0644_1:000.000","LI:B0645_1:000.000","LI:B0159_6:000.000","LI:K0001_10:000.000","LI:K0003_2:000.000","LI:K0004_1:000.000","LI:K0005_1:000.000","LI:K0006_1:000.000","LI:K0008_8:000.000","LI:K0009_1:000.000","LI:K0010_1:000.000","LI:K0011_1:000.000","LI:K0012_3:000.000","LI:K0013_2:000.000","LI:K0014_9:000.000","LI:K0015_1:000.000","LI:K0016_4:000.000","LI:K0017_4:000.000","LI:K0018_4:000.000","LI:K0019_4:000.000","LI:K0020_4:000.000","LI:K0021_4:000.000","LI:K0022_3:000.000","LI:K0023_7:000.000","LI:K0024_2:000.000","LI:K0025_7:000.000","LI:K0026_2:000.000","LI:K0027_1:000.000","LI:K0028_1:000.000","LI:K0029_1:000.000","LI:K0031_4:000.000","LI:K0032_4:000.000","LI:K0033_10:000.000","LI:K0035_9:000.000","LI:K0036_3:000.000","LI:K0037_2:000.000","LI:K0038_5:000.000","LI:K0039_4:000.000","LI:K0040_1:000.000","LI:K0041_3:000.000","LI:K0042_2:000.000","LI:K0043_8:000.000","LI:K0044_8:000.000","LI:K0044_10:024.030","LI:K0044_7:046.865","LI:K0045_1:000.000","LI:A0005_10:199.920","LI:A0005_5:239.225","LI:B0100_8:000.000","LI:B0113_8:000.000","LI:B0131_10:000.000","LI:B0200_7:000.000","LI:B0303_3:000.000","LI:B0459_1:000.000","LI:B0478_6:000.000","LI:B0531_1:000.000","LI:B0602_9:000.000","LI:B0630_3:000.000","LI:E0001:000.000","LI:E0002:000.000","LI:E0003:000.000","LI:K0007_9:000.000","LI:B0588_2:010.060"
        };
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
//    private <T> List<T> fetchApiData(String url, String timestampKey, TypeReference<List<T>> typeReference) throws UnirestException {
//        try {
//            Timestamp latestTimestamp = collectionDateTimeService.getLatestCollectionDateTime(timestampKey);
//            String formattedTimestamp = new SimpleDateFormat(TIMESTAMP_FORMAT).format(latestTimestamp);
//
//            HttpResponse<String> response = Unirest.post(url)
//                    .header("APIKEY", apiKey)
//                    .queryString("since", formattedTimestamp)
//                    .asString();
//
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
//    public List<TL_RIS_ROADWIDTHDTO> risRoadWidths() throws UnirestException {
//        return fetchApiData(roadWidthApiUrl, "roadwidth", new TypeReference<>() {});
//    }
//
//    public List<TL_RIS_ROUGH_DISTRIDTO> risRoughDistris() throws UnirestException {
//        return fetchApiData(roughDistriApiUrl, "roughDistri", new TypeReference<>() {});
//    }
//
//    public List<TL_RIS_SURFACEDTO> risSurfaces() throws UnirestException {
//        return fetchApiData(surfaceApiUrl, "surface", new TypeReference<>() {});
//    }
//
//    public List<TL_TIS_AADTDTO> tisAadts() throws UnirestException {
//        return fetchApiData(aadtApiUrl, "aadt", new TypeReference<>() {});
//    }
//}
