package com.sym.web.controller.system;

import com.sym.common.controller.BaseController;
import com.sym.common.result.AjaxResult;
import com.sym.system.domain.SysUser;
import com.sym.system.service.ISysUserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController {

    @Resource
    private ISysUserService sysUserService;

    /**
     * 获取用户列表
     */
    @GetMapping("/list")
    public AjaxResult list() {
        System.out.println("获取用户列表");
        List<SysUser> list = sysUserService.list();
        System.out.println("用户列表: " + list);
        return success(list);
    }
}
