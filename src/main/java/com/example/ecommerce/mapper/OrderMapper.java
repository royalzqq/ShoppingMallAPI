package com.example.ecommerce.mapper;

import com.example.ecommerce.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    // 查询所有订单
    List<Order> getAllOrders();

    // 根据ID查询订单
    Order getOrderById(@Param("id") Integer id);

    // 根据用户ID查询订单
    List<Order> getOrdersByUserId(@Param("userId") Integer userId);

    // 添加订单
    int addOrder(Order order);

    // 更新订单信息
    int updateOrder(Order order);

    // 删除订单
    int deleteOrder(@Param("id") Integer id);
}