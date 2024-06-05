package org.neighbor21.slakslramsapi.jpRepository;

import org.neighbor21.slakslramsapi.entity.TL_RIS_ROADWIDTHEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class TL_RIS_ROADWIDTHReposit {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Object[]> findMaxSqnoBySurveyYear() {
        String sql = "SELECT SRVY_YY, MAX(SQNO) FROM srlk.TL_RIS_ROADWIDTH GROUP BY SRVY_YY";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Object[]{rs.getString(1), rs.getInt(2)});
    }

    public Optional<Timestamp> findTopByOrderByCollectionDateTimeDesc() {
        String sql = "SELECT CLCT_DT FROM srlk.TL_RIS_ROADWIDTH ORDER BY CLCT_DT DESC LIMIT 1";
        return jdbcTemplate.query(sql, (rs) -> {
            if (rs.next()) {
                return Optional.of(rs.getTimestamp("CLCT_DT"));
            } else {
                return Optional.empty();
            }
        });
    }

    public void batchInsert(List<TL_RIS_ROADWIDTHEntity> entities) {
        String sql = "INSERT INTO srlk.TL_RIS_ROADWIDTH (LINK_CD, ROAD_TYPE, WIDTH_CLSF, LEN, SRVY_YY, CLCT_DT, SQNO) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, entities, entities.size(), (ps, entity) -> {
            ps.setString(1, entity.getLinkCode());
            ps.setString(2, entity.getRoadType());
            ps.setString(3, entity.getWidthCategory());
            ps.setBigDecimal(4, entity.getLength());
            ps.setString(5, entity.getSurveyYear());
            ps.setTimestamp(6, entity.getCollectionDateTime());
            ps.setInt(7, entity.getSqno());
        });
    }
}
