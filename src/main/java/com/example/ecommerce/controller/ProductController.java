package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:5173") // 允许来自前端的请求
public class ProductController {

    @Autowired
    private ProductService productService;

    // 获取所有商品
    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            System.out.println("访问了 getAllProducts 接口");
            List<Product> products = productService.getAllProducts();
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace(); // Log the full stack trace
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
