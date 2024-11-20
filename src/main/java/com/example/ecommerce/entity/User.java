package com.example.ecommerce.entity;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String userType; // 'normal_user', 'admin_user'
    private LocalDateTime createdAt;

    public void setToken(String sessionToken) {

    }
}
