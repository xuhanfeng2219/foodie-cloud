package com.imooc.order.service.center;

import com.imooc.order.pojo.Orders;
import com.imooc.order.pojo.PagedGridResult;
import com.imooc.order.pojo.vo.OrderStatusCountsVO;
import org.springframework.web.bind.annotation.*;

@RequestMapping("my-order-api")
public interface MyOrdersService {

    /**
     * 查询我的订单列表
     *
     * @param userId
     * @param orderStatus
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("queryMyOrders")
    public PagedGridResult queryMyOrders(@RequestParam(value = "userId", required = false) String userId,
                                         @RequestParam(value = "orderStatus", required = false) Integer orderStatus,
                                         @RequestParam(value = "page", required = false) Integer page,
                                         @RequestParam(value = "pageSize", required = false) Integer pageSize);

    /**
     * @Description: 订单状态 --> 商家发货
     */
    @PostMapping("updateDeliverOrderStatus/{orderId}")
    public void updateDeliverOrderStatus(@PathVariable("orderId") String orderId);

    /**
     * 查询我的订单
     *
     * @param userId
     * @param orderId
     * @return
     */
    @GetMapping("queryMyOrder")
    public Orders queryMyOrder(@RequestParam("userId") String userId,@RequestParam("orderId") String orderId);

    /**
     * 更新订单状态 —> 确认收货
     *
     * @return
     */
    @PostMapping("updateReceiveOrderStatus/{orderId}")
    public boolean updateReceiveOrderStatus(@PathVariable("orderId") String orderId);

    /**
     * 删除订单（逻辑删除）
     *
     * @param userId
     * @param orderId
     * @return
     */
    @DeleteMapping("deleteOrder/{userId}/{orderId}")
    public boolean deleteOrder(@PathVariable("userId") String userId,@PathVariable("orderId") String orderId);

    /**
     * 查询用户订单数
     *
     * @param userId
     */
    @GetMapping("getOrderStatusCounts/{userId}")
    public OrderStatusCountsVO getOrderStatusCounts(@PathVariable("userId") String userId);

    /**
     * 获得分页的订单动向
     *
     * @param userId
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("getOrdersTrend")
    public PagedGridResult getOrdersTrend(@RequestParam(value = "userId", required = false) String userId,
                                          @RequestParam(value = "page", required = false) Integer page,
                                          @RequestParam(value = "pageSize", required = false) Integer pageSize);
}