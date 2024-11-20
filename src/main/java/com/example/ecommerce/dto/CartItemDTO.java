package com.example.ecommerce.dto;

import lombok.Data;

@Data
public class CartItemDTO {
    private Integer productId;
    private Integer quantity;
    private Boolean selected;
}
