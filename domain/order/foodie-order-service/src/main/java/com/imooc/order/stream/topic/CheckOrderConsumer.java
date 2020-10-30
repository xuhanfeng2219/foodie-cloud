package com.imooc.order.stream.topic;

import com.imooc.enums.OrderStatusEnum;
import com.imooc.order.mapper.OrderStatusMapper;
import com.imooc.order.pojo.OrderStatus;
import com.imooc.order.pojo.bo.OrderStatusCheckBO;
import com.imooc.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

/**
 * @author 2349
 */
@EnableBinding(
        value = {
                CheckOrderTopic.class
        }
)
@Slf4j
public class CheckOrderConsumer {

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @StreamListener(CheckOrderTopic.INPUT)
    public void consumeOrderStatusMessage(OrderStatusCheckBO statusCheckBO) {
        log.info("received order check request, orderId = {}", statusCheckBO.getOrderId());
        // 查询所有未付款订单，判断时间是否超时（1天），超时则关闭交易
        OrderStatus queryOrder = new OrderStatus();
        queryOrder.setOrderId(statusCheckBO.getOrderId());
        queryOrder.setOrderStatus(OrderStatusEnum.WAIT_PAY.type);
        List<OrderStatus> list = orderStatusMapper.select(queryOrder);

        if (CollectionUtils.isEmpty(list)) {
            log.error("order paid or closed, orderId = {}", statusCheckBO.getOrderId());
            return;
        }
        // 获得订单创建时间
        Date createdTime = list.get(0).getCreatedTime();
        // 和当前时间进行对比
        int days = DateUtil.daysBetween(createdTime, new Date());
        if (days >= 1) {
            // 超过1天，关闭订单
            OrderStatus orderStatus = new OrderStatus();
            orderStatus.setOrderId(statusCheckBO.getOrderId());
            orderStatus.setOrderStatus(OrderStatusEnum.CLOSE.type);
            orderStatus.setCloseTime(new Date());

            int count = orderStatusMapper.updateByPrimaryKey(orderStatus);

            log.info(" Closed order, orderId ={}, count={}", orderStatus.getOrderId(), count);
        }
    }
}
