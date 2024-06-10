package org.neighbor21.slakslramsapi.jpRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * packageName    : org.neighbor21.slakslramsapi.jpRepository
 * fileName       : TL_RIS_ROADWIDTHReposit.java
 * author         : kjg08
 * date           : 24. 5. 2.
 * description    : TL_RIS_ROADWIDTH 엔티티의 데이터베이스 작업을 처리하는 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 2.        kjg08           최초 생성
 */
@Repository
public class TL_RIS_ROADWIDTHReposit {

    private static final Logger logger = LoggerFactory.getLogger(TL_RIS_ROADWIDTHReposit.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 연도별 최대 SQNO 조회
     *
     * @return 연도별 최대 SQNO 리스트
     */
    public List<Object[]> findMaxSqnoBySurveyYear() {
        try {
            String sql = "SELECT SRVY_YY, MAX(SQNO) FROM srlk.TL_RIS_ROADWIDTH GROUP BY SRVY_YY";
            return jdbcTemplate.query(sql, (rs, rowNum) -> new Object[]{rs.getString(1), rs.getInt(2)});
        } catch (Exception e) {
            logger.error("Error querying max SQNO by survey year: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to query max SQNO by survey year", e);
        }
    }

    /**
     * 가장 최근 수집 일시 조회
     *
     * @return 가장 최근 수집 일시
     */
    public Optional<Timestamp> findTopByOrderByCollectionDateTimeDesc() {
        try {
            String sql = "SELECT CLCT_DT FROM srlk.TL_RIS_ROADWIDTH ORDER BY CLCT_DT DESC LIMIT 1";
            return jdbcTemplate.query(sql, (rs) -> {
                if (rs.next()) {
                    return Optional.of(rs.getTimestamp("CLCT_DT"));
                } else {
                    return Optional.empty();
                }
            });
        } catch (Exception e) {
            logger.error("Error querying top collection datetime: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to query top collection datetime", e);
        }
    }

}
