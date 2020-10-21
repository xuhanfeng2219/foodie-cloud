package com.imooc.order.fallback.itemservice;

import com.google.common.collect.Lists;
import com.imooc.item.pojo.vo.MyCommentVO;
import com.imooc.item.service.ItemCommentsService;
import com.imooc.order.pojo.PagedGridResult;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ItemCommentsFallbackFactory implements FallbackFactory<ItemCommentsService> {
    @Override
    public ItemCommentsService create(Throwable throwable) {
        return new ItemCommentsService() {
            @Override
            public PagedGridResult queryMyComments(String userId, Integer page, Integer pageSize) {
                MyCommentVO commentVO = new MyCommentVO();
                commentVO.setContent("正在加载中");

                PagedGridResult pagedGridResult = new PagedGridResult();
                pagedGridResult.setPage(1);
                pagedGridResult.setRecords(1);
                pagedGridResult.setTotal(1);
                pagedGridResult.setRows(Lists.newArrayList(commentVO));
                return pagedGridResult;
            }

            @Override
            public void saveComments(Map<String, Object> map) {

            }
        };
    }
}
