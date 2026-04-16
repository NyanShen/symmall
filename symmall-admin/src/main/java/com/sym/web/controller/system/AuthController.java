package com.sym.web.controller.system;

import com.sym.common.controller.BaseController;
import com.sym.common.enums.ResultCode;
import com.sym.common.exception.BusinessException;
import com.sym.common.result.AjaxResult;
import com.sym.common.utils.type.SymStringUtils;
import com.sym.framework.security.model.LoginUser;
import com.sym.framework.security.util.JwtUtil;
import com.sym.framework.security.util.PasswordUtil;
import com.sym.system.domain.SysUser;
import com.sym.system.dto.LoginRequest;
import com.sym.system.service.ISysUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController extends BaseController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ISysUserService sysUserService;

    /**
     * 登录
     * @param loginRequest
     * @return AjaxResult
     */
    @PostMapping("/login")
    public AjaxResult login(@Valid @RequestBody LoginRequest loginRequest) {
        if (SymStringUtils.isEmpty(loginRequest.getUsername()) || SymStringUtils.isEmpty(loginRequest.getPassword())) {
            throw new BusinessException(ResultCode.BUSINESS_ERROR.getCode(), "用户名或密码不能为空");
        }
        /**
         * 账号密码校验工具
         * 拿你传进来的 username + password
         * 去查用户（内存 / 数据库）--userDetailsService.loadUserByUsername(username)
         * 比对密码是否正确--自动调用 passwordEncoder 比对密码。
         * 正确 → 返回认证成功对象
         * 错误 → 直接抛异常（密码错误 / 用户不存在）
         */

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        // 认证通过后处理
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        // 获取认证信息
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        String token = JwtUtil.generateToken(loginUser.getUserId(), loginUser.getUsername());
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("username", loginUser.getUsername());
        map.put("permissions", loginUser.getPermissions());
        return success(map);
    }
    /**
     * 注册
     */
    @PostMapping("/register")
    public AjaxResult register(@RequestBody SysUser sysUser) {
        sysUser.setPassword(PasswordUtil.encode(sysUser.getPassword()));
        sysUserService.save(sysUser);
        return success("注册成功");
    }
}

