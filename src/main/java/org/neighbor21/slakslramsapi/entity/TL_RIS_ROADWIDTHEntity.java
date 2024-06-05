package org.neighbor21.slakslramsapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * packageName    : org.neighbor21.slakslramsapi.entity
 * fileName       : RISRoadWidthEntity.java
 * author         : kjg08
 * date           : 24. 5. 2.
 * description    : RIS 도로너비 정보 엔티티 TL_RIS_ROADWIDTH
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 2.        kjg08           최초 생성
 */
@Getter
@Setter
@Entity
@Table(name = "TL_RIS_ROADWIDTH", schema = "srlk")
public class TL_RIS_ROADWIDTHEntity {

    //링크 코드
    @Id
    @Column(name = "LINK_CD", length = 50)
    private String linkCode;
    //도로 유형
    @Column(name = "ROAD_TYPE", length = 30)
    private String roadType;
    //너비 분류
    @Column(name = "WIDTH_CLSF", length = 30)
    private String widthCategory;
    //길이
    @Column(name = "LEN", precision = 6, scale = 3)
    private BigDecimal length;
    //설문 년
    @Column(name = "SRVY_YY", length = 4)
    private String surveyYear;
    //수집 일시
    @Column(name = "CLCT_DT")
    private Timestamp collectionDateTime;

    @Column(name = "SQNO")
    private int sqno; // 순번 추가

}
