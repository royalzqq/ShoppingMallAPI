package com.example.ecommerce.service.impl;

import com.example.ecommerce.entity.User;
import com.example.ecommerce.mapper.UserMapper;
import com.example.ecommerce.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HttpServletRequest request; // 注入 HttpServletRequest

    @Value("${session.timeout:3600}") // 会话超时时间 (秒)
    private int sessionTimeout;

    @Override
    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);

        if (user == null || !passwordMatches(password, user.getPassword())) {
            return null;
        }

        // 登录成功后生成 Token
        HttpSession session = request.getSession(true);
        String sessionToken = UUID.randomUUID().toString(); // 生成唯一会话 Token
        session.setAttribute("user", user);
        session.setAttribute("sessionToken", sessionToken);
        session.setMaxInactiveInterval(sessionTimeout); // 设置会话超时时间

        // 将 Token 返回给前端
        user.setToken(sessionToken);
        return user;
    }

    private boolean passwordMatches(String rawPassword, String encodedPassword) {
        return rawPassword.equals(encodedPassword);
        // 使用 BCrypt 时：
        // return BCrypt.checkpw(rawPassword, encodedPassword);
    }

    @Override
    public boolean isAuthenticated(String sessionToken) {
        HttpSession session = request.getSession(false);
        if (session == null) return false;

        String storedToken = (String) session.getAttribute("sessionToken");
        return sessionToken != null && sessionToken.equals(storedToken);
    }

    @Override
    public boolean register(String username, String password, String email) {
        // 检查用户名是否已存在
        User existingUser = userMapper.findByUsername(username);
        if (existingUser != null) {
            return false; // 用户名已存在
        }

        // 注册新用户
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);  // 密码应该加密存储
        user.setEmail(email);
        userMapper.insert(user);  // 保存用户信息
        return true;
    }

}
