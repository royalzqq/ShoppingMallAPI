package com.example.ecommerce.mapper;

import com.example.ecommerce.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    // 查询所有商品
    List<Product> getAllProducts();

    // 根据ID查询商品
    Product getProductById(@Param("id") Integer id);

    // 添加商品
    int addProduct(Product product);

    // 更新商品信息
    int updateProduct(Product product);

    // 删除商品
    int deleteProduct(@Param("id") Integer id);
}
