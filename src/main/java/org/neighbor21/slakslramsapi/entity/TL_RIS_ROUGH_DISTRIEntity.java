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
 * fileName       : RISRoughnessEntity.java
 * author         : kjg08
 * date           : 24. 5. 2.
 * description    : RIS Roughness Distribution Information Entity  TL_RIS_ROUGH_DISTRI
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 2.        kjg08           Initial creation
 */
@Entity
@Getter
@Setter
@Table(name = "TL_RIS_ROUGH_DISTRI", schema = "srlk")
public class TL_RIS_ROUGH_DISTRIEntity {

    @Id
    @Column(name = "LINK_CD", length = 50)
    private String linkCode;
    @Column(name = "CLSF", length = 30)
    private String category;
    @Column(name = "LEN", precision = 6, scale = 3)
    private BigDecimal length;
    @Column(name = "SRVY_YY", length = 4)
    private String surveyYear;
    @Column(name = "CLCT_DT")
    private Timestamp collectionDateTime;

    @Column(name = "SQNO")
    private int sqno;
}
