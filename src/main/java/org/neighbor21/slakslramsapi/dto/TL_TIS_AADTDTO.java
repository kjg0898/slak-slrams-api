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
 * description    : TIS About traffic balancing DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 2.        kjg08           Initial creation
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TL_TIS_AADTDTO {

    @JsonProperty("LinkCode")
    private String linkCode;
    @JsonProperty("AADT")
    private BigInteger averageDailyTraffic;
    @JsonProperty("Category")
    private String category;
    @JsonProperty("Length")
    private BigDecimal length;
    @JsonProperty("SurveyID")
    private String surveyYear;
}
