package com.imooc.cart;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan(basePackages = "com.imooc.cart.service")
@ComponentScan(basePackages = {"com.imooc", "org.n3r.idworker"})
public class CartApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(CartApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
