package com.example.demo.service;

import com.example.demo.model.Role;

public interface RoleService extends GenericService<Role> {
    Role getRoleByName(String roleName);
}

