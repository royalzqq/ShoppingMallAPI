package com.example.ecommerce.service.impl;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.mapper.OrderMapper;
import com.example.ecommerce.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Override
    public List<Order> getAllOrders() {
        return orderMapper.getAllOrders();
    }

    @Override
    public Order getOrderById(int id) {
        return orderMapper.getOrderById(id);
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        return orderMapper.getOrdersByUserId(userId);
    }

    @Override
    public int addOrder(Order order) {
        return orderMapper.addOrder(order);
    }

    @Override
    public int updateOrder(Order order) {
        return orderMapper.updateOrder(order);
    }

    @Override
    public int deleteOrder(int id) {
        return orderMapper.deleteOrder(id);
    }
}
