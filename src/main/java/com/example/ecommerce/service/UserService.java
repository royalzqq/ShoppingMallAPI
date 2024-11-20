package com.example.ecommerce.service;

import com.example.ecommerce.entity.User;

public interface UserService {

    User login(String username, String password);

    boolean isAuthenticated(String sessionToken);

    boolean register(String username, String password, String email);

}
