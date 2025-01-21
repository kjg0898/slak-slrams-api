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
 * fileName       : TL_TIS_AADTReposit.java
 * author         : kjg08
 * date           : 24. 5. 2.
 * description    : This class handles database operations for the TL_TIS_AADT entity.
 * * JdbcTemplate
 * * A utility class provided by the Spring Framework to simplify JDBC operations.
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 2.        kjg08           Initial creation
 */
@Repository
public class TL_TIS_AADTReposit {

    private static final Logger logger = LoggerFactory.getLogger(TL_TIS_AADTReposit.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Retrieve the maximum SQNO grouped by survey year.
     *
     * @return A list of maximum SQNO values grouped by survey year.
     */
    public List<Object[]> findMaxSqnoBySurveyYear() {
        try {
            String sql = "SELECT SRVY_YY, MAX(SQNO) FROM srlk.TL_TIS_AADT GROUP BY SRVY_YY";
            return jdbcTemplate.query(sql, (rs, rowNum) -> new Object[]{rs.getString(1), rs.getInt(2)});
        } catch (Exception e) {
            logger.error("Error occurred while querying maximum SQNO by survey year: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to query max SQNO by survey year", e);
        }
    }

    /**
     * Retrieve the most recent collection timestamp.
     *
     * @return The most recent collection timestamp.
     */
    public Optional<Timestamp> findTopByOrderByCollectionDateTimeDesc() {
        try {
            String sql = "SELECT CLCT_DT FROM srlk.TL_TIS_AADT ORDER BY CLCT_DT DESC LIMIT 1";
            return jdbcTemplate.query(sql, (rs) -> {
                if (rs.next()) {
                    return Optional.of(rs.getTimestamp("CLCT_DT"));
                } else {
                    return Optional.empty();
                }
            });
        } catch (Exception e) {
            logger.error("Error occurred while querying the most recent collection timestamp: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to query top collection datetime", e);
        }
    }

}
