package com.sym.framework.util;

import com.sym.common.constants.Constants;
import com.sym.framework.security.util.PasswordUtil;
import org.junit.jupiter.api.Test;

public class PasswordUtilTest {

    @Test
    public void testEncode() {
        String password = "admin123";
        String encodedPassword = PasswordUtil.encode(password);
        System.out.println("加密后的密码: " + encodedPassword);
    }
    @Test
    public void testMatch() {
        String password = "admin123";
        String encodedPassword = "$2a$10$Jzi7D9K1J3xKdQutv3ifkOqNIhDH.MJXgn4sGekeeHSTnmTnbfOhO";
        boolean match = PasswordUtil.matches(password, encodedPassword);
        System.out.println("密码匹配结果: " + match);
    }

    @Test
    public void testTokenPrefix() {
        System.out.println("token prefix length: " + Constants.TOKEN_PREFIX.length());
    }
}
