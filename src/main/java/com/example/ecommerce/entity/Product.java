package com.example.ecommerce.entity;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Product {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private String image;
    private LocalDateTime createdAt;
}
