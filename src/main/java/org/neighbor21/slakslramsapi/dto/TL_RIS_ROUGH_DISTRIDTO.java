package org.neighbor21.slakslramsapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * packageName    : org.neighbor21.slakslramsapi.dto
 * fileName       : RISRoughnessDTO.java
 * author         : kjg08
 * date           : 24. 5. 2.
 * description    : RIS About Roughness Distribution DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 2.        kjg08           Initial creation
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TL_RIS_ROUGH_DISTRIDTO {

    @JsonProperty("LinkCode")
    private String linkCode;
    @JsonProperty("Category")
    private String category;
    @JsonProperty("Length")
    private BigDecimal length;
    @JsonProperty("SurveyID")
    private String surveyYear;
}
