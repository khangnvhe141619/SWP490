package com.workshop.carautionsystem.service;

import com.workshop.carautionsystem.model.Role;
import com.workshop.carautionsystem.model.RoleName;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(RoleName name);
}
