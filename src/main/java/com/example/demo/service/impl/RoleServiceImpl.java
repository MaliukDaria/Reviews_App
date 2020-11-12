package com.example.demo.service.impl;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.RoleService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleRepository.getByRoleName(Role.RoleName.valueOf(roleName));
    }

    @Override
    public Role add(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addAll(List<Role> roles) {
        roleRepository.saveAll(roles);
    }
}
