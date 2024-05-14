package org.neighbor21.slakslramsapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * packageName    : org.neighbor21.slakslramsapi.entity
 * fileName       : RISRoughnessEntity.java
 * author         : kjg08
 * date           : 24. 5. 2.
 * description    : RIS 거칠기 분포 정보	 엔티티  TL_RIS_ROUGH_DISTRI
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 2.        kjg08           최초 생성
 */
@Entity
@Getter
@Setter
@Table(name = "TL_RIS_ROUGH_DISTRI")
public class TL_RIS_ROUGH_DISTRIEntity {

    //링크 코드
    @Id
    @Column(name = "LINK_CD", length = 50)
    private String linkCode;
    //분류
    @Column(name = "CLSF", length = 30)
    private String category;
    //길이
    @Column(name = "LEN", precision = 6, scale = 3)
    private BigDecimal length;
    //설문 년
    @Column(name = "SRVY_YY", length = 4)
    private String surveyYear;
    //수집 일시
    @Column(name = "CLCT_DT")
    private Timestamp collectionDateTime;


}
