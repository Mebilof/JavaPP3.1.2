package com.project.PP312.dao;

import com.project.PP312.model.Role;

public interface RoleDao {
    void addRole(Role role);
    Role getRoleByName(String name);
    Iterable<Role> getAllRoles();
}
