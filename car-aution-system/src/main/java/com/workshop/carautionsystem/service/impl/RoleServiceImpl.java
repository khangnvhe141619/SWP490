package com.workshop.carautionsystem.service.impl;

import com.workshop.carautionsystem.model.Role;
import com.workshop.carautionsystem.model.RoleName;
import com.workshop.carautionsystem.repository.IRoleRepository;
import com.workshop.carautionsystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    IRoleRepository repository;
    @Override
    public Optional<Role> findByName(RoleName name) {
        return repository.findByName(name);
    }
}
