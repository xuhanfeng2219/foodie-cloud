package com.imooc.order.fallback.itemservice;

import com.imooc.item.service.ItemCommentsService;
import org.springframework.cloud.openfeign.FeignClient;


/**
 * 在ItemCommentsService上定义了requestMapping，同时ItemCommentsServiceFeign
 * 继承了ItemCommentsService，因此相当于spring上下文中加载了两个访问路径一样的方法，
 * 会报“Ambiguous mapping”
 *
 * 避免访问相同的路径的方法：
 * 1：在启动类扫包的时候，不要把原始的feign接口扫描进来
 * 具体做法：可以使用EnableFeignClients注解clients属性，只加载需要的feign接口
 *  优点：服务提供者和服务调用者都不需要额外的配置
 *  缺点：启动的时候配置麻烦点，要指定加载每个用到的接口
 * 2、原始的feign接口不要定义requestMapping注解
 * 优点：启动的时候直接扫包即可，不用指定加载接口
 * 缺点：a、服务提供者要额外配置路径访问的注解
 *      b、任何情况下，即时不需要在调用端定义fallback类，服务调用者都需要声明一个
 * 3、原始feign接口不要定义@FeignClients注解，这样不会被加载到上下文中
 *  优点：启动时直接扫包即可，不用指定加载接口，服务提供者不用额外配置
 *  缺点：任何情况下，服务调用者都需要声明一个额外的@FeignClients接口
 */
@FeignClient("foodie-item-service")
public interface ItemCommentsFeignClient extends ItemCommentsService {
}
