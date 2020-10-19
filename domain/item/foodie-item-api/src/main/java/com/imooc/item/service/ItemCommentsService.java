package com.imooc.item.service;

import com.imooc.order.pojo.PagedGridResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 2349
 * 内部降级（商品中心），放到item-service里面实现
 * 调用方降级（订单中心、调用商品中心服务），由订单中心定义降级逻辑
 */
@FeignClient("foodie-item-service")
@RequestMapping("item-comments-api")
public interface ItemCommentsService {

    /**
     * 我的评价查询 分页
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("queryMyComments")
    public PagedGridResult queryMyComments(@RequestParam("userId") String userId,
                                           @RequestParam(value = "page", required = false) Integer page,
                                           @RequestParam(value = "pageSize", required = false) Integer pageSize);
    @PostMapping("saveComments")
    public void saveComments(@RequestBody Map<String, Object> map);
}
