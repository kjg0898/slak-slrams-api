package org.neighbor21.slakslramsapi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * packageName    : org.neighbor21.slakslramsapi.dto
 * fileName       : RISSurfaceTypeDTO.java
 * author         : kjg08
 * date           : 24. 5. 2.
 * description    : RIS About surface types DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 2.        kjg08           Initial creation
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TL_RIS_SURFACEDTO {

    @JsonProperty("LinkCode")
    private String linkCode;
    @JsonProperty("SurfaceType")
    private String surfaceCategory;
    @JsonProperty("SurfaceTypeDescription")
    private String surfaceDescription;
    @JsonProperty("Length")
    private BigDecimal length;
    @JsonProperty("surveyid")
    private String surveyYear;

}
