package com.example.ecommerce.service.impl;

import com.example.ecommerce.entity.User;
import com.example.ecommerce.mapper.UserMapper;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    // 登录方法
    @Override
    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);

        if (user == null ||!passwordMatches(password, user.getPassword())) {
            return null;
        }

        return user;
    }


    @Override
    public boolean isAuthenticated(String sessionToken) {
        User user = getUserByToken(sessionToken);
        return user!= null && user.isLoggedIn();
    }

    // 验证密码
    private boolean passwordMatches(String rawPassword, String encodedPassword) {
        return rawPassword.equals(encodedPassword);
    }

    // 根据 token 获取用户信息
    @Override
    public User getUserByToken(String token) {
        // 根据 token 查询数据库获取用户信息并验证Token有效性
        return userMapper.findByToken(token);
    }



    // 注册方法
    @Override
    public boolean register(String username, String password, String email) {
        User existingUser = userMapper.findByUsername(username);
        if (existingUser!= null) {
            return false; // 用户名已存在
        }

        // 注册新用户
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);  // 密码应加密（这里暂时未实现加密逻辑，可后续添加）
        user.setEmail(email);
        userMapper.insert(user);
        return true;
    }

    @Override
    public void clearUserLoginState() {
        // 获取所有已登录用户（这里假设通过查询数据库中isLoggedIn为true的用户）
        User[] loggedInUsers = userMapper.findAllLoggedInUsers();

        for (User user : loggedInUsers) {
            // 将用户的登录状态设置为未登录
            user.setLoggedIn(false);
            userMapper.updateUser(user);
        }
    }

    // 新增方法，用于更新用户信息
    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void recharge(User user) {
        User byId = userMapper.findById(user.getId());
        if (byId == null) {
            throw new RuntimeException("User not found");
        }
        // 充值逻辑，这里假设充值100元
        user.setBalance(byId.getBalance()+100);
        userMapper.updateUser(user);
    }
}