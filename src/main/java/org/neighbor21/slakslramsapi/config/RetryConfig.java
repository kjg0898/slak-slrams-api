package org.neighbor21.slakslramsapi.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * packageName    : org.neighbor21.slakslramsapi.config
 * fileName       : RetryConfig.java
 * author         : kjg08
 * date           : 24. 6. 17.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 6. 17.        kjg08           최초 생성
 */
@Configuration
@EnableRetry
public class RetryConfig {

    @Bean
    public RetryTemplate retryTemplate() {
        RetryTemplate retryTemplate = new RetryTemplate();

        // 지수 백오프 정책 설정
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(2000);  // 초기 지연 시간 설정
        backOffPolicy.setMultiplier(2);          // 지연 시간 증가 배율 설정
        backOffPolicy.setMaxInterval(30000);     // 최대 지연 시간 설정

        // 간단한 재시도 정책 설정
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(2);           // 최대 재시도 횟수 설정

        retryTemplate.setBackOffPolicy(backOffPolicy);
        retryTemplate.setRetryPolicy(retryPolicy);

        return retryTemplate;
    }
}