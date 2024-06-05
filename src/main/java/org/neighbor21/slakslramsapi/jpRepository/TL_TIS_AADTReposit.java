package org.neighbor21.slakslramsapi.jpRepository;

import org.neighbor21.slakslramsapi.entity.TL_TIS_AADTEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class TL_TIS_AADTReposit {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 연도별 최대 SQNO 조회
     *
     * @return 연도별 최대 SQNO 리스트
     */
    public List<Object[]> findMaxSqnoBySurveyYear() {
        String sql = "SELECT SRVY_YY, MAX(SQNO) FROM srlk.TL_TIS_AADT GROUP BY SRVY_YY";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Object[]{rs.getString(1), rs.getInt(2)});
    }

    /**
     * 가장 최근 수집 일시 조회
     *
     * @return 가장 최근 수집 일시
     */
    public Optional<Timestamp> findTopByOrderByCollectionDateTimeDesc() {
        String sql = "SELECT CLCT_DT FROM srlk.TL_TIS_AADT ORDER BY CLCT_DT DESC LIMIT 1";
        return jdbcTemplate.query(sql, (rs) -> {
            if (rs.next()) {
                return Optional.of(rs.getTimestamp("CLCT_DT"));
            } else {
                return Optional.empty();
            }
        });
    }

    /**
     * 엔티티 배치 삽입
     *
     * @param entities 삽입할 엔티티 리스트
     */
    public void batchInsert(List<TL_TIS_AADTEntity> entities) {
        String sql = "INSERT INTO srlk.TL_TIS_AADT (LINK_CD, AAD_TRFVLM, CATEG, LEN, SRVY_YY, CLCT_DT, SQNO) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, entities, entities.size(), (ps, entity) -> {
            ps.setString(1, entity.getLinkCode());
            ps.setBigDecimal(2, new BigDecimal(entity.getAverageDailyTraffic()));
            ps.setString(3, entity.getCategory());
            ps.setBigDecimal(4, entity.getLength());
            ps.setString(5, entity.getSurveyYear());
            ps.setTimestamp(6, entity.getCollectionDateTime());
            ps.setInt(7, entity.getSqno());
        });
    }
}