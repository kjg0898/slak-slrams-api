package org.neighbor21.slakslramsapi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * packageName    : org.neighbor21.slakslramsapi.entity
 * fileName       : TISTrafficDispersionEntity.java
 * author         : kjg08
 * date           : 24. 5. 2.
 * description    : TIS Traffic Distribution Information Entity TL_TIS_AADT
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 2.        kjg08           Initial creation
 */
@Getter
@Setter
@Entity
@Table(name = "TL_TIS_AADT", schema = "srlk")
public class TL_TIS_AADTEntity {

    @Id
    @Column(name = "LINK_CD", length = 50)
    private String linkCode;

    @Column(name = "AAD_TRFVLM", precision = 19, scale = 0)
    private BigInteger averageDailyTraffic;

    @Column(name = "CATEG", length = 30)
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
