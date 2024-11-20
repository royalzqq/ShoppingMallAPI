package com.example.ecommerce.service.impl;

import com.example.ecommerce.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendVerificationCode(String toEmail, String code) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setFrom("q17871152478@163.com");
            helper.setTo(toEmail);
            helper.setSubject("验证码邮件");
            helper.setText("【购物商城】注册————您好，您的验证码是：" + code + "，请在有效期内使用。", true);
            mailSender.send(message);
            System.out.println("邮件发送成功！");
        } catch (Exception e) {
            System.err.println("邮件发送失败：" + e.getMessage());
        }
    }
}
