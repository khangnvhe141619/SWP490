package com.workshop.carautionsystem.responsitory;

import com.workshop.carautionsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserResponsitory extends JpaRepository<User,Integer> {
    public Optional<User> findUserByUserNameAndPassword(String username, String password);
}
