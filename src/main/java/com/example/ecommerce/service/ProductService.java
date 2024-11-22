package com.example.ecommerce.service;

import com.example.ecommerce.entity.Product;

import java.util.List;

public interface ProductService {
    // 查询所有商品
    List<Product> getAllProducts();

    // 根据ID获取商品
    Product getProductById(int id);

    // 添加商品
    int addProduct(Product product);

    // 更新商品信息
    int updateProduct(Product product);

    // 删除商品
    int deleteProduct(int id);
}