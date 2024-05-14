package org.neighbor21.slakslramsapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * packageName    : org.neighbor21.slakslramsapi.entity
 * fileName       : RISSurfaceTypeEntity.java
 * author         : kjg08
 * date           : 24. 5. 2.
 * description    : RIS 표면유형 정보	 엔티티  TL_RIS_SURFACE
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 2.        kjg08           최초 생성
 */
@Getter
@Setter
@Entity
@Table(name = "TL_RIS_SURFACE")
public class TL_RIS_SURFACEEntity {


    //링크 코드
    @Id
    @Column(name = "LINK_CD", length = 50)
    private String linkCode;
    //표면 분류
    @Column(name = "SURF_CLSF", length = 30)
    private String surfaceCategory;
    //표면 설명
    @Column(name = "SURF_DESCR", length = 400)
    private String surfaceDescription;
    //길이
    @Column(name = "LEN", precision = 6, scale = 3)
    private BigDecimal length;
    //설문 년
    @Column(name = "SRVY_YY", length = 4)
    private String surveyYear;
    //수집 일시
    @Column(name = "CLCT_DT", nullable = false)
    private Timestamp collectionDateTime;

}
