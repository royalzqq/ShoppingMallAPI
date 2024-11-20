package com.example.ecommerce.controller;

import com.example.ecommerce.dto.LoginDTO;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173") // 允许来自前端的请求
public class UserController {

    @Autowired
    private UserService userService;

    // 登录接口
    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        User user = userService.login(loginDTO.getUsername(), loginDTO.getPassword());
        return user != null ? "Login successful" : "Invalid credentials";
    }

    // 注册接口
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String email) {

        boolean success = userService.register(username, password, email);
        if (success) {
            return "注册成功！";
        } else {
            return "注册失败，用户名或验证码错误！";
        }
    }

}
