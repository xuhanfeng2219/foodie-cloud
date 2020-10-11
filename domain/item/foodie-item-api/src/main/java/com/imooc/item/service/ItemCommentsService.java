package com.imooc.item.service;

import com.imooc.order.pojo.PagedGridResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
