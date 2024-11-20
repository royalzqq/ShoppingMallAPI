package com.example.ecommerce.controller;

import com.example.ecommerce.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = "http://localhost:5173") // 允许来自前端的请求
public class EmailController {

    @Autowired
    private EmailService emailService;

    private final Map<String, String> verificationCodes = new HashMap<>();

    @GetMapping("/send-code")
    public String sendVerificationCode(String email) {
        String code = generateCode();
        emailService.sendVerificationCode(email, code);
        verificationCodes.put(email, code);
        return "验证码已发送，请检查您的邮箱。";
    }

    @PostMapping("/verify-code")
    public String verifyCode(@RequestParam String email, @RequestParam String code) {
        String storedCode = verificationCodes.get(email);
        if (storedCode != null && storedCode.equals(code)) {
            return "验证码验证成功！";
        }
        return "验证码错误或已过期！";
    }

    private String generateCode() {
        return String.valueOf((int)((Math.random() * 9 + 1) * 100000));
    }
}
