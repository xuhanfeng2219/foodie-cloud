package com.imooc.cart.service.impl;

import com.imooc.cart.service.CartService;
import com.imooc.order.pojo.ShopcartBO;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartServiceImpl implements CartService {

    @Override
    public boolean addItem2Cart(String userId, ShopcartBO shopcartBO) {
        return false;
    }

    @Override
    public boolean removeItemFromCart(String userId, ShopcartBO shopcartBO) {
        return false;
    }

    @Override
    public boolean clearCart(String userId) {
        return false;
    }
}
