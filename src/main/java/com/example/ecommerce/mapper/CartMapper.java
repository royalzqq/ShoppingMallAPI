package com.example.ecommerce.mapper;

import com.example.ecommerce.entity.CartItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartMapper {

    // 添加商品到购物车
    int addToCart(CartItem cartItem);
}
