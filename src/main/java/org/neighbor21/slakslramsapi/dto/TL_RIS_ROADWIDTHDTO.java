package org.neighbor21.slakslramsapi.dto;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * packageName    : org.neighbor21.slakslramsapi.dto
 * fileName       : RISRoadWidthDTO.java
 * author         : kjg08
 * date           : 24. 5. 2.
 * description    : RIS 도로너비 정보 DTO
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 2.        kjg08           최초 생성
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TL_RIS_ROADWIDTHDTO {

    //링크 코드
    private String linkCode;
    //도로 타입
    private String roadType;
    //너비 분류
    private String widthCategory;
    //길이
    private BigDecimal length;
    //설문 년
    private String surveyYear;
    //수집 일시
    private Timestamp collectionDateTime;

}
