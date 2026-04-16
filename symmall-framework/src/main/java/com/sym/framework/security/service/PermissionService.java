package com.sym.framework.security.service;

import org.springframework.stereotype.Service;

@Service("pms")
public class PermissionService {
    public boolean hasPerm(String permission) {
        return true;
    }

    public boolean hasRole(String role) {
        return true;
    }
}
