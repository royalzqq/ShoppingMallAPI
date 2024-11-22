package com.example.ecommerce.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartItem {
    private int userId;      // 用户ID
    private int productId;   // 商品ID
    private int quantity;    // 商品数量
    private boolean selected;
}
