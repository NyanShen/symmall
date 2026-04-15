package com.sym.web.controller.system;

import com.sym.common.controller.BaseController;
import com.sym.common.result.AjaxResult;
import com.sym.system.domain.SysUser;
import com.sym.system.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 获取用户列表
     */
    @GetMapping("/list")
    public AjaxResult list() {
        System.out.println("获取用户列表");
        List<SysUser> list = sysUserMapper.selectList(null);
        System.out.println("用户列表: " + list);
        return success(list);
    }
}
