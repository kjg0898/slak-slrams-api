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
 * description    : Class for handling database operations for the TL_RIS_ROADWIDTH entity
 * JdbcTemplate
 * A utility class provided by the Spring Framework to simplify JDBC operations.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 2.        kjg08           Initial creation
 */
@Repository
public class TL_RIS_ROADWIDTHReposit {

    private static final Logger logger = LoggerFactory.getLogger(TL_RIS_ROADWIDTHReposit.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Find the maximum SQNO by survey year.
     *
     * @return List of maximum SQNOs by survey year.
     */
    public List<Object[]> findMaxSqnoBySurveyYear() {
        try {
            String sql = "SELECT SRVY_YY, MAX(SQNO) FROM srlk.TL_RIS_ROADWIDTH GROUP BY SRVY_YY";
            return jdbcTemplate.query(sql, (rs, rowNum) -> new Object[]{rs.getString(1), rs.getInt(2)});
        } catch (Exception e) {
            logger.error("Error occurred while querying maximum SQNO by survey year: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to query max SQNO by survey year", e);
        }
    }

    /**
     * Retrieve the most recent collection datetime.
     *
     * @return The most recent collection datetime.
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
            logger.error("Error occurred while querying the most recent collection datetime: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to query top collection datetime", e);
        }
    }

}
