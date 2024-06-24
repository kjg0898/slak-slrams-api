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
// * Spring 프레임워크에서 제공하는 유틸리티 클래스로, JDBC 작업을 간소화.

@Service
public class BatchService {
    private static final Logger logger = LoggerFactory.getLogger(BatchService.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 엔티티 리스트를 배치 삽입합니다.
     * SQLException 발생 시 최대 2회까지 재시도합니다.
     *
     * @param entities 삽입할 엔티티 리스트
     * @param sql      삽입할 SQL 문
     * @param setter   PreparedStatement 설정을 위한 BatchPreparedStatementSetter
     * @param <T>      엔티티 타입
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
