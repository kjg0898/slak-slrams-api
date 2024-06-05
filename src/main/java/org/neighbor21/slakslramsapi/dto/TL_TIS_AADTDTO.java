package org.neighbor21.slakslramsapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * packageName    : org.neighbor21.slakslramsapi.dto
 * fileName       : TISTrafficDispersionDTO.java
 * author         : kjg08
 * date           : 24. 5. 2.
 * description    : TIS 교통분산 정보 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 2.        kjg08           최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TL_TIS_AADTDTO {

    //링크 코드
    @JsonProperty("LinkCode")
    private String linkCode;
    //연평균일 교통량
    @JsonProperty("AADT")
    private BigInteger averageDailyTraffic;
    //카테고리
    @JsonProperty("Category")
    private String category;
    //길이
    @JsonProperty("Length")
    private BigDecimal length;
    //설문 년
    @JsonProperty("SurveyID")
    private String surveyYear;
}
