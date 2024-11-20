package com.example.ecommerce.entity;

import lombok.Data;

@Data
public class OrderItem {
    private Integer id;
    private String orderId;
    private Integer productId;
    private Integer quantity;
}
