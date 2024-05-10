/*
package org.neighbor21.slakslramsapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

*/
/**
 * packageName    : org.neighbor21.slakslramsapi.service
 * fileName       : DatabaseConnectionKeeper.java
 * author         : kjg08
 * date           : 24. 5. 9.
 * description    : db 연결 테스트 (select 1) <- 이거 대신 spring.datasource.hikari.keepalive-time 를 사용하여 연결 유지하는쪽으로 변경하여 주석처리함
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 9.        kjg08           최초 생성
 *//*

@EnableScheduling
@Component
public class DatabaseConnectionKeeper {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnectionKeeper.class);
    private final JdbcTemplate jdbcTemplate;

    //생성자 매개변수 추가
    public DatabaseConnectionKeeper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Scheduled(fixedRate = 300000) // 300000 ms = 5 minutes 마다 db 커넥션 확인
    public void keepConnectionAlive() {
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            logger.info("Database connection keep-alive query executed successfully.");
        } catch (Exception e) {
            logger.error("Error while executing keep-alive query: ", e);
        }
    }
}

*/
