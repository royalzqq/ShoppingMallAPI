package com.example.ecommerce.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {
    private String id;
    private Integer userId;
    private LocalDateTime date;
    private BigDecimal total;
    private Boolean shipped;
    private List<OrderItem> items; // 订单项列表
}
