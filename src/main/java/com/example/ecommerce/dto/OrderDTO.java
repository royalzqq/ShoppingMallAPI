package com.example.ecommerce.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderDTO {
    private String id;
    private BigDecimal total;
    private List<OrderItemDTO> items;
}
