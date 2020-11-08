package com.imooc;

import com.imooc.filter.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 2349
 */
@Configuration
public class RoutesConfiguration {

    @Autowired
    private KeyResolver keyResolver;

    @Autowired
    @Qualifier("redisLimiterUser")
    private RedisRateLimiter redisRateLimiterUser;

    @Autowired
    @Qualifier("redisLimiterItem")
    private RedisRateLimiter redisRateLimiterItem;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder, AuthFilter authFilter) {
        return builder.routes()
                .route(r -> r.path("address/list")
                        .filters(f -> f.filter(authFilter))
                        .uri("lb://FOODIE-USER-SERVICE"))
                .route(r -> r.path("address/**", "passport/**", "userInfo/**")
                        .filters(f -> f.requestRateLimiter(
                                config -> {
                                    config.setKeyResolver(keyResolver);
                                    config.setRateLimiter(redisRateLimiterUser);
                                }
                        ))
                        .uri("lb://FOODIE-USER-SERVICE"))
                .route(r -> r.path("items/**")
                        .uri("lb://FOODIE-ITEM-SERVICE"))
                .route(r -> r.path("orders/**", "myorders/**", "mycomments/**")
                        .uri("lb://FOODIE-ORDER-SERVICE"))
                // TODO: 2020/10/21 search 模块
                .route(r -> r.path("search/**", "/items/search/**")
                        .uri("lb://FOODIE-SEARCH-SERVICE"))
                .build();
    }
}
