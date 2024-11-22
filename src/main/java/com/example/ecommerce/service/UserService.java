package com.example.ecommerce.service;

import com.example.ecommerce.entity.User;

public interface UserService {

    User login(String username, String password);

    boolean isAuthenticated(String sessionToken);

    // 根据 token 获取用户信息
    User getUserByToken(String token);

    boolean register(String username, String password, String email);

    void clearUserLoginState();

    // 新增方法，用于更新用户信息（比如密码修改等场景可能用到）
    void updateUser(User user);

    void recharge(User user);
}