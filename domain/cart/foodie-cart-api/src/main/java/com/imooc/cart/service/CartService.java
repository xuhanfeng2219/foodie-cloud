package com.imooc.cart.service;

import com.imooc.order.pojo.ShopcartBO;
import org.springframework.web.bind.annotation.*;

@RequestMapping("cart-api")
public interface CartService {

    @PostMapping("addItem2Cart")
    public boolean addItem2Cart(@RequestParam("userId") String userId, @RequestBody ShopcartBO shopcartBO);

    @PostMapping("removeItemFromCart")
    public boolean removeItemFromCart(@RequestParam("userId") String userId, @RequestBody ShopcartBO shopcartBO);

    @PostMapping("clearCart")
    public boolean clearCart(@RequestParam("userId") String userId);
}
