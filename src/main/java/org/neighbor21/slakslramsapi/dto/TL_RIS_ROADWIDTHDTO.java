package org.neighbor21.slakslramsapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * packageName    : org.neighbor21.slakslramsapi.dto
 * fileName       : RISRoadWidthDTO.java
 * author         : kjg08
 * date           : 24. 5. 2.
 * description    : RIS Road width information DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 2.        kjg08           Initial creation
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TL_RIS_ROADWIDTHDTO {

    @JsonProperty("LinkCode")
    private String linkCode;
    @JsonProperty("RoadType")
    private String roadType;
    @JsonProperty("WidthClass")
    private String widthCategory;
    @JsonProperty("Length")
    private BigDecimal length;
    @JsonProperty("SurveyID")
    private String surveyYear;

}
