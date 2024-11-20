package com.example.ecommerce.entity;

import lombok.Data;

@Data
public class CartItem {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer quantity;
    private Boolean selected;
}
