package org.neighbor21.slakslramsapi.service;

import org.neighbor21.slakslramsapi.config.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
// * JdbcTemplate

/**
 * The type Batch service.
 */
@Service
public class BatchService {
    private static final Logger logger = LoggerFactory.getLogger(BatchService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Performs batch insertion for a list of entities.
     * Retries up to two times in case of SQLException.
     *
     * @param <T>      The entity type.
     * @param entities The list of entities to insert.
     * @param sql      The SQL query for inserting data.
     * @param setter   The BatchPreparedStatementSetter for configuring PreparedStatement.
     */
    @Retryable(value = {SQLException.class}, maxAttempts = 2)
    @Transactional
    public <T> void batchInsertWithRetry(List<T> entities, String sql, BatchPreparedStatementSetter<T> setter) {
        int batchSize = Constants.DEFAULT_BATCH_SIZE;
        int totalRecords = entities.size();

        for (int i = 0; i < totalRecords; i += batchSize) {
            int end = Math.min(i + batchSize, totalRecords);
            List<T> batchList = entities.subList(i, end);

            if (!batchList.isEmpty()) {
                try {
                    jdbcTemplate.batchUpdate(sql, new org.springframework.jdbc.core.BatchPreparedStatementSetter() {
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
                    logger.error("Batch insertion failed in the range of index {} to {}: {}", i, end, e.getMessage(), e);
                    throw new RuntimeException("Batch insert failed", e);
                }
            }
        }
    }

    /**
     * The interface Batch prepared statement setter.
     *
     * @param <T> the type parameter
     */
    @FunctionalInterface
    public interface BatchPreparedStatementSetter<T> {
        /**
         * Sets values.
         *
         * @param ps     the ps
         * @param entity the entity
         * @throws SQLException the sql exception
         */
        void setValues(PreparedStatement ps, T entity) throws SQLException;
    }
}
