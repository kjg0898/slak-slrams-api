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
 * fileName       : RISSurfaceTypeEntity.java
 * author         : kjg08
 * date           : 24. 5. 2.
 * description    : RIS Surface Type Information Entity   TL_RIS_SURFACE
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 2.        kjg08           Initial creation
 */
@Getter
@Setter
@Entity
@Table(name = "TL_RIS_SURFACE", schema = "srlk")
public class TL_RIS_SURFACEEntity {


    @Id
    @Column(name = "LINK_CD", length = 50)
    private String linkCode;
    @Column(name = "SURF_CLSF", length = 30)
    private String surfaceCategory;
    @Column(name = "SURF_DESCR", length = 400)
    private String surfaceDescription;
    @Column(name = "LEN", precision = 6, scale = 3)
    private BigDecimal length;
    @Column(name = "SRVY_YY", length = 4)
    private String surveyYear;
    @Column(name = "CLCT_DT", nullable = false)
    private Timestamp collectionDateTime;

    @Column(name = "SQNO")
    private int sqno;
}
