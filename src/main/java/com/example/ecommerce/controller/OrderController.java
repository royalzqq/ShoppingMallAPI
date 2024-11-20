package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Resource
    private OrderService orderService;

    // 获取所有订单
    @GetMapping("/getAllOrders")
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    // 根据ID获取订单
    @GetMapping("/getOrderById")
    public ResponseEntity<Order> getOrderById(int id) {
        Order order = orderService.getOrderById(id);
        System.out.println("entered GetOrderById order = " + order);
        if (order!= null) {
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 根据用户ID获取订单
    @GetMapping("/user")
    public ResponseEntity<List<Order>> getOrdersByUserId( int userId) {
        List<Order> orders = orderService.getOrdersByUserId(userId);
        if (orders!= null &&!orders.isEmpty()) {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // 添加订单
    @PostMapping("/addOrder")
    public ResponseEntity<Integer> addOrder(@RequestBody Order order) {
        System.out.println("entered addOrder order = " + order);
        int result = orderService.addOrder(order);
        System.out.println("result = " + result);
        if (result > 0) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 更新订单信息
    @PutMapping("/updateOrder")
    public ResponseEntity<Integer> updateOrder( @RequestBody Order order) {
        int result = orderService.updateOrder(order);
        if (result > 0) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 删除订单
    @DeleteMapping("/deleteOrder")
    public ResponseEntity<Void> deleteOrder(@RequestBody Order order) {
        int result = orderService.deleteOrder(Integer.parseInt(order.getId()));
        if (result > 0) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 新增：用于管理员标记订单为已发货
    @PutMapping("/markOrderAsShipped")
    public ResponseEntity<Void> markOrderAsShipped(@RequestBody Order order) {
        Order orderById = orderService.getOrderById(Integer.parseInt(order.getId()));
        if (orderById!= null) {
            order.setShipped(true);
            int result = orderService.updateOrder(order);
            if (result > 0) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
