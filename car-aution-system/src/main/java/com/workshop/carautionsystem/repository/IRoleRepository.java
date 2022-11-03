package com.workshop.carautionsystem.repository;

import com.workshop.carautionsystem.model.Role;
import com.workshop.carautionsystem.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(RoleName name);
}
