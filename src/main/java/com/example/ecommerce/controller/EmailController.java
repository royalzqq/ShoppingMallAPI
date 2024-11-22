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



    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/send-code")
    public String sendVerificationCode(@RequestParam String email) {
        System.out.println(email);
        if (email == null || email.isEmpty()) {
            return "邮箱地址不能为空！";
        }

        // 生成验证码
        String code = generateCode();

        // 发送验证码邮件
        emailService.sendVerificationCode(email, code);

        // 存储验证码（例如存储在一个 Map 中）
        verificationCodes.put(email, code);

        return "验证码已发送，请检查您的邮箱。";
    }


    @PostMapping("/verify-code")
    public String verifyCode(@RequestParam String email, @RequestParam String code) {
        String storedCode = verificationCodes.get(email);
        System.out.println("从verificationCodes中获取到的验证码:"+storedCode);
        if (storedCode!= null && storedCode.equals(code)) {
            System.out.println("验证码验证成功");
            return "Registration successful！";
        } else {
            System.out.println("验证码验证失败");
            return "验证码错误或已过期！";
        }
    }

    private String generateCode() {
        return String.valueOf((int)((Math.random() * 9 + 1) * 100000));
    }
}
