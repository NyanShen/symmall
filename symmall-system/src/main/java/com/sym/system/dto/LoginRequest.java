package com.sym.system.dto;

import jakarta.validation.constraints.NotNull;

/**
 * 登录请求参数
 */
public class LoginRequest {
    /**
     * 登录用户名
     */
    @NotNull(message = "用户名不能为空")
    private String username;
    /**
     * 登录密码
     */
    @NotNull(message = "密码不能为空")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}