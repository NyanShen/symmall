package com.sym.system.service.impl;

import com.sym.common.utils.type.SymStringUtils;
import com.sym.framework.security.model.LoginUser;
import com.sym.system.domain.SysUser;
import com.sym.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * 用户验证服务
 *
 * @author sym
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 根据用户名查询用户信息
     * 重写spring security的loadUserByUsername方法-改为获取数据库的用户信息
     * @param username the username identifying the user whose data is required.
     * @return UserDetails
     * @throws UsernameNotFoundException 账号不存在 | 账户或密码错误
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.selectUserByUserName(username);
        if (SymStringUtils.isNull(sysUser)) {
            throw new UsernameNotFoundException("用户不存在:" + username);
        }
        return new LoginUser(sysUser.getUserId(), sysUser.getUserName(), sysUser.getPassword(), new ArrayList<>());
    }
}
