package com.example.ecommerce.service;

import com.example.ecommerce.entity.CartItem;
import com.example.ecommerce.mapper.CartMapper;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final CartMapper cartMapper;  // 假设你已经创建了 CartMapper

    public CartService(CartMapper cartMapper) {
        this.cartMapper = cartMapper;
    }

    // 添加商品到购物车
    public boolean addToCart(CartItem cartItem) {
        int result = cartMapper.addToCart(cartItem);
        return result > 0;  // 返回添加是否成功
    }
}
