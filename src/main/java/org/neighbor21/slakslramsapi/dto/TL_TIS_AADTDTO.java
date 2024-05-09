package org.neighbor21.slakslramsapi.dto;

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
    private String linkCode;
    //연평균일 교통량
    private BigInteger averageDailyTraffic;
    //카테고리
    private String category;
    //길이
    private BigDecimal length;
    //설문 년
    private String surveyYear;
    //수집 일시
    //private Timestamp collectionDateTime;


}
