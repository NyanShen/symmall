package com.sym.framework.security.filter;

import com.sym.common.constants.Constants;
import com.sym.common.utils.type.SymStringUtils;
import com.sym.framework.security.model.LoginUser;
import com.sym.framework.security.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

/**
 * JWT 认证过滤器
 * spring security 配置类 需要设置的认证过滤器
 * OncePerRequestFilter: 确保过滤器只被调用一次，防止重复处理
 */
@Component // 将过滤器注册为 Spring Bean
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    /**
     * 执行 JWT 认证过滤逻辑
     * <p>
     * 从请求头中提取 JWT Token，验证其有效性。如果 Token 有效，
     * 则解析用户信息并构建认证对象存入 SecurityContext（请求入口处讲认证信息存入上下文，后期业务判断时可随时获取）；
     * 如果 Token 无效或不存在，则继续过滤器链。
     * </p>
     *
     * @param request HTTP 请求对象，用于获取 Authorization 头中的 Token
     * @param response HTTP 响应对象
     * @param filterChain 过滤器链，用于将请求传递给下一个过滤器
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException {
        String token = getTokenFromRequest(request);
        // 验证Token
        if (SymStringUtils.isNotNull(token) && JwtUtil.validateToken(token)) {
            Long userId = JwtUtil.getUserIdFromToken(token);
            String username = JwtUtil.getUsernameFromToken(token);
            LoginUser loginUser = new LoginUser(userId, username, null, new ArrayList<>());

            // 构建认证对象，因为loginUser继承了UserDetails，所以这里可以传入loginUser
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            // 设置认证信息
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // 将认证信息存入SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        // 继续过滤器链
        filterChain.doFilter(request, response);
    }

    /**
     * 从请求头获取token
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(Constants.TOKEN_HEADER);
        if (SymStringUtils.isNotNull(bearerToken) && bearerToken.startsWith(Constants.TOKEN_PREFIX)) {
            return bearerToken.substring(Constants.TOKEN_PREFIX.length());
        }
        return null;
    }
}
