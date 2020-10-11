package com.imooc.item.service;

import com.imooc.item.pojo.Items;
import com.imooc.item.pojo.ItemsImg;
import com.imooc.item.pojo.ItemsParam;
import com.imooc.item.pojo.ItemsSpec;
import com.imooc.item.pojo.vo.CommentLevelCountsVO;
import com.imooc.order.pojo.PagedGridResult;
import com.imooc.item.pojo.vo.ShopcartVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("item-api")
public interface ItemService {

    /**
     * 根据商品ID查询详情
     * @param itemId
     * @return
     */
    @GetMapping("queryItemById")
    public Items queryItemById(@RequestParam("itemId") String itemId);

    /**
     * 根据商品id查询商品图片列表
     * @param itemId
     * @return
     */
    @GetMapping("queryItemImgList")
    public List<ItemsImg> queryItemImgList(@RequestParam("itemId") String itemId);

    /**
     * 根据商品id查询商品规格
     * @param itemId
     * @return
     */
    @GetMapping("queryItemSpecList")
    public List<ItemsSpec> queryItemSpecList(@RequestParam("itemId") String itemId);

    /**
     * 根据商品id查询商品参数
     * @param itemId
     * @return
     */
    @GetMapping("queryItemParam")
    public ItemsParam queryItemParam(@RequestParam("itemId") String itemId);

    /**
     * 根据商品id查询商品的评价等级数量
     * @param itemId
     */
    @GetMapping("queryCommentCounts")
    public CommentLevelCountsVO queryCommentCounts(@RequestParam("itemId") String itemId);

    /**
     * 根据商品id查询商品的评价（分页）
     * @param itemId
     * @param level
     * @return
     */
    @GetMapping("queryPagedComments")
    public PagedGridResult queryPagedComments(@RequestParam("itemId") String itemId, @RequestParam(value = "level", required = false) Integer level,
                                              @RequestParam(value = "page", required = false) Integer page,@RequestParam(value = "pageSize", required = false)  Integer pageSize);

//    /**
//     * 搜索商品列表
//     * @param keywords
//     * @param sort
//     * @param page
//     * @param pageSize
//     * @return
//     */
//    public PagedGridResult searhItems(String keywords, String sort,
//                                      Integer page, Integer pageSize);
//
//    /**
//     * 根据分类id搜索商品列表
//     * @param catId
//     * @param sort
//     * @param page
//     * @param pageSize
//     * @return
//     */
//    public PagedGridResult searhItems(Integer catId, String sort,
//                                      Integer page, Integer pageSize);

    /**
     * 根据规格ids查询最新的购物车中商品数据（用于刷新渲染购物车中的商品数据）
     * @param specIds
     * @return
     */
    @GetMapping("queryItemsBySpecIds")
    public List<ShopcartVO> queryItemsBySpecIds(@RequestParam("specIds") String specIds);

    /**
     * 根据商品规格id获取规格对象的具体信息
     * @param specId
     * @return
     */
    @GetMapping("queryItemSpecById")
    public ItemsSpec queryItemSpecById(@RequestParam("specId") String specId);

    /**
     * 根据商品id获得商品图片主图url
     * @param itemId
     * @return
     */
    @GetMapping("queryItemMainImgById")
    public String queryItemMainImgById(@RequestParam("itemId") String itemId);

    /**
     * 减少库存
     * @param specId
     * @param buyCounts
     */
    @PostMapping("decreaseItemSpecStock")
    public void decreaseItemSpecStock(@RequestParam("specId") String specId,@RequestParam("buyCounts") int buyCounts);
}
