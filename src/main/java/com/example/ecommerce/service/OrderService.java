package com.example.ecommerce.service;

import com.example.ecommerce.entity.Order;

import java.util.List;

public interface OrderService {
    // 获取所有订单
    List<Order> getAllOrders();

    // 根据ID获取订单
    Order getOrderById(int id);

    // 根据用户ID获取订单
    List<Order> getOrdersByUserId(int userId);

    // 添加订单
    int addOrder(Order order);

    // 更新订单信息
    int updateOrder(Order order);

    // 删除订单
    int deleteOrder(int id);
}