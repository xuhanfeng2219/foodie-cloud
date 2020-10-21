package com.imooc;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * @author 2349
 */
@Configuration
public class RedisLimiterConfiguration {

    @Primary
    @Bean
    public KeyResolver remoteAddressKeyResolver() {
        return exchange -> Mono.just(
                Objects.requireNonNull(exchange.getRequest()
                        .getRemoteAddress())
                        .getAddress()
                        .getHostAddress()
        );
    }

    @Bean("redisLimiterUser")
    public RedisRateLimiter redisRateLimiterUser() {
        return new RedisRateLimiter(10, 20);
    }

    @Bean("redisLimiterItem")
    @Primary
    public RedisRateLimiter redisRateLimiterItem() {
        return new RedisRateLimiter(20, 50);
    }
}
