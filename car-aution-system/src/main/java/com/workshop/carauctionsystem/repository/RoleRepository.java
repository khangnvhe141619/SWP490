package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(String name);
    @Override
    void delete(Role role);
}
