package com.example.ecommerce.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false); // 获取现有 Session，不创建新会话
        if (session != null) {
            String token = request.getHeader("Authorization"); // 从请求头获取 Token
            String sessionToken = (String) session.getAttribute("userToken");
            if (token != null && token.equals(sessionToken)) {
                return true; // 验证成功，继续请求
            }
        }

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 返回 401 未授权
        return false;
    }
}
