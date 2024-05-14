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
 * description    : RIS 거칠기 분포 정보 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 2.        kjg08           최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TL_RIS_ROUGH_DISTRIDTO {

    //링크 코드
    @JsonProperty("")
    private String linkCode;
    //분류
    @JsonProperty("")
    private String category;
    //길이
    @JsonProperty("")
    private BigDecimal length;
    //설문 년
    @JsonProperty("")
    private String surveyYear;
    //수집 일시
    //private Timestamp collectionDateTime;
}
