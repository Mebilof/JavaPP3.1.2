package com.project.PP312.service;

import com.project.PP312.model.Role;

public interface RoleService {
    void addRole(Role role);
    Role getRoleByName(String name);
}
