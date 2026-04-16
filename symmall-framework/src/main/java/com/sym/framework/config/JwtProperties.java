package com.sym.framework.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*
 * @Description: jwt 配置类
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    /**
     * 密钥
     * 默认值：
     */
    private String secret = "symmall_jwt_secret_key_2026_this_is_a_long_enough_secret_for_hs256";

    /**
     * 过期时间
     * 默认7天
     */
    private Long expire = 7 * 24 * 60 * 60 * 1000L;
}
