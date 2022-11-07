package com.workshop.carautionsystem.repository;

import com.workshop.carautionsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserResponsitory extends JpaRepository<User, Integer> {
    public Optional<User> findUserByUserNameAndPassword(String username, String password);
    public List<User> findUserByUserName(String username);
    public User findUserById(int id);
}
