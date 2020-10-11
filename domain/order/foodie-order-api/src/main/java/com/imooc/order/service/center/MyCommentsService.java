package com.imooc.order.service.center;

import com.imooc.order.pojo.OrderItems;
import com.imooc.order.pojo.PagedGridResult;
import com.imooc.order.pojo.bo.center.OrderItemsCommentBO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("my-comments-api")
public interface MyCommentsService {

    /**
     * 根据订单id查询关联的商品
     * @param orderId
     * @return
     */
    @GetMapping("queryPendingComment/{orderId}")
    public List<OrderItems> queryPendingComment(@PathVariable("orderId") String orderId);

    /**
     * 保存用户的评论
     * @param orderId
     * @param userId
     * @param commentList
     */
    @PostMapping("saveComments")
    public void saveComments(@RequestParam("orderId") String orderId,
                             @RequestParam("userId") String userId,
                             @RequestBody List<OrderItemsCommentBO> commentList);


    // TODO: 2020/10/7 移入itemCommonService的服务中去
//    /**
//     * 我的评价查询 分页
//     * @param userId
//     * @param page
//     * @param pageSize
//     * @return
//     */
//    @GetMapping("queryMyComments")
//    public PagedGridResult queryMyComments(@RequestParam("userId") String userId,
//                                           @RequestParam(value = "page", required = false) Integer page,
//                                           @RequestParam(value = "pageSize", required = false) Integer pageSize);
}
