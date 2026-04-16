package com.sym.framework.security.model;

import com.sym.common.utils.type.SymStringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 登录用户模型 实现 UserDetails 接口 取数据库中用户信息， 封装成 LoginUser
 * @author sym
 * 作用：封装用户信息，用于登录认证
 * 1. 用户提交用户名密码
 *    ↓
 * 2. UserDetailsService.loadUserByUsername() 查询用户
 *    ↓
 * 3. 返回 LoginUser 对象
 *    ↓
 * 4. Spring Security 验证密码是否正确
 *    ↓
 * 5. ✅ 密码正确后，依次检查以下方法：
 *    ├── isAccountNonExpired()    ← 账户是否过期
 *    ├── isAccountNonLocked()     ← 账户是否锁定
 *    ├── isCredentialsNonExpired() ← 密码是否过期
 *    └── isEnabled()              ← 账户是否启用
 *    ↓
 * 6. 全部通过 → 认证成功
 *    任一失败 → 抛出对应异常，登录失败
 */
@Data
@AllArgsConstructor
public class LoginUser implements UserDetails {
    private Long userId;
    private String username;
    private String password;
    private List<String> permissions;

    /**
     * 作用：返回用户的权限列表（如 ROLE_ADMIN, sys:user:list）
     * 何时调用：鉴权时（@PreAuthorize）
     * 问题：返回 null 可能导致空指针异常，应该返回空集合
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return SymStringUtils.isNotNull(permissions)
                ? permissions.stream()
                  .map(SimpleGrantedAuthority::new)
                  .collect(Collectors.toList())
                : Collections.emptyList();
    }

    /**
     * 返回登陆用户密码
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * 获取登陆用户名
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * 作用：检查账户是否过期
     * 何时调用：认证成功后，Spring Security 会检查
     * 如果返回 false：抛出 AccountExpiredException，登录失败
     */
    @Override
    public boolean isAccountNonExpired() {
        return true; // 账户是否未过期，默认是false，如果为true表示账户已经过期。
    }

    /**
     * 作用：检查账户是否被锁定（如密码错误次数过多）
     * 何时调用：认证成功后检查
     * 如果返回 false：抛出 LockedException，登录失败
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 作用：检查密码是否过期（如90天强制修改密码）
     * 何时调用：认证成功后检查
     * 如果返回 false：抛出 CredentialsExpiredException，登录失败
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 作用：检查账户是否被禁用
     * 何时调用：认证成功后检查
     * 如果返回 false：抛出 DisabledException，登录失败
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
