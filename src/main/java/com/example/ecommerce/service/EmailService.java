package com.example.ecommerce.service;

public interface EmailService {
    void sendVerificationCode(String toEmail, String code);
}
