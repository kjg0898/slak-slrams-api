package org.neighbor21.slakslramsapi.service;

import org.neighbor21.slakslramsapi.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.BatchUpdateException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
public class BatchService {
    private static final Logger logger = LoggerFactory.getLogger(BatchService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public <T> void batchInsertWithRetry(List<T> entities, String sqls, BatchPreparedStatementSetter<T> setter) {
        int batchSize = Constants.DEFAULT_BATCH_SIZE;
        int totalRecords = entities.size();

        for (int i = 0; i < totalRecords; i += batchSize) {
            int end = Math.min(i + batchSize, totalRecords);
            List<T> batchList = entities.subList(i, end);

            if (!batchList.isEmpty()) {
                try {
                    jdbcTemplate.batchUpdate(sqls, new org.springframework.jdbc.core.BatchPreparedStatementSetter() {
                        @Override
                        public void setValues(PreparedStatement ps, int i) throws SQLException {
                            setter.setValues(ps, batchList.get(i));
                        }

                        @Override
                        public int getBatchSize() {
                            return batchList.size();
                        }
                    });
                } catch (Exception e) {
                    logger.error("Batch insert attempt failed at index {} to {}: {}", i, end, e.getMessage(), e);
                    throw new RuntimeException("Batch insert failed", e);
                }
            }
        }
    }


    @FunctionalInterface
    public interface BatchPreparedStatementSetter<T> {
        void setValues(PreparedStatement ps, T entity) throws SQLException;
    }
}
