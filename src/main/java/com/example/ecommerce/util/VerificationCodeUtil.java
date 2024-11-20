package com.example.ecommerce.util;

import java.util.Random;

public class VerificationCodeUtil {

    public static String generateCode() {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) { // 生成6位数字验证码
            code.append(random.nextInt(10));
        }
        return code.toString();
    }
}
