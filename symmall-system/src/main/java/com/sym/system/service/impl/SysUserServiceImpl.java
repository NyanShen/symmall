package com.sym.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sym.system.domain.SysUser;
import com.sym.system.mapper.SysUserMapper;
import com.sym.system.service.ISysUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser selectUserByUserName(String username) {
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUserName, username);
        return sysUserMapper.selectOne(wrapper);
    }
}
