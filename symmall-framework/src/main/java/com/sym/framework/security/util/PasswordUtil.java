package com.sym.framework.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 密码工具类
 */
public class PasswordUtil {
    // 密码加密, 使用 BCryptPasswordEncoder
    private static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
    /**
     * 密码加密
     */
    public static String encode(String password) {
        return PASSWORD_ENCODER.encode(password);
    }
    /**
     * 密码匹配
     */
    public static boolean matches(String password, String encodedPassword) {
        return PASSWORD_ENCODER.matches(password, encodedPassword);
    }
}

