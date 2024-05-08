package org.neighbor21.slakslramsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

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
    private String linkCode;
    //분류
    private String category;
    //길이
    private BigDecimal length;
    //설문 년
    private String surveyYear;
    //수집 일시
    private Timestamp collectionDateTime;
}
