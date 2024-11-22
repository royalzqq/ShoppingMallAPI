package com.example.ecommerce.controller;

import com.example.ecommerce.dto.LoginDTO;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:5173") // 允许来自前端的请求
public class UserController {

    @Autowired
    private UserService userService;

    // 登录接口
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        User user = userService.login(loginDTO.getUsername(), loginDTO.getPassword());

        if (user != null) {
            // 登录成功，返回用户信息，包括 userId 和用户名
            return ResponseEntity.ok().body(new LoginResponse(user.getId(), user.getUsername()));
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }


    // 获取用户信息接口
    @GetMapping("/profile")
    public String getUserProfile(@CookieValue(value = "token", defaultValue = "") String token) {
        if (!token.isEmpty()) {
            User user = userService.getUserByToken(token);  // 根据 Token 获取用户信息
            if (user != null) {
                return "User: " + user.getUsername();
            }
        }
        return "No user logged in";
    }

    // 注册接口
    @PostMapping("/register")
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String email) {
        boolean success = userService.register(username, password, email);
        System.out.println(username+" "+password+" "+email);
        if (success) {
            return "Successfully registered";
        } else {
            return "Registration failed, username or verification code error!";
        }
    }

    // 注销接口，退出登录时清除 cookie
    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        // 清除 cookie
        Cookie cookie = new Cookie("token", null);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);  // 立即过期
        cookie.setPath("/");
        response.addCookie(cookie);

        // 在服务端清除用户登录状态（假设UserService中有相应方法）
        userService.clearUserLoginState();

        return "Logged out successfully";
    }

    // 充值接口
    @PostMapping("/recharge")
    public ResponseEntity<Map<String, Object>> recharge(@RequestBody User user) {

        if (user != null) {
            userService.recharge(user); // 调用service层的方法进行充值

            // 创建返回对象
            Map<String, Object> response = new HashMap<>();
            response.put("newBalance", user.getBalance()); // 返回新余额

            // 返回包含新余额的 JSON 响应
            return ResponseEntity.ok().body(response);
        }

        // 返回未登录或无效token的响应
        return ResponseEntity.status(401).body(Map.of("message", "No user logged in or invalid token."));
    }

    // 登录成功后的返回数据对象
    static class LoginResponse {
        private int userId;
        private String username;

        public LoginResponse(int userId, String username) {
            this.userId = userId;
            this.username = username;
        }

        public int getUserId() {
            return userId;
        }

        public String getUsername() {
            return username;
        }
    }
}
