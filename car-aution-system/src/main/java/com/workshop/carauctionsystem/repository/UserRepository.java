package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findUserByUserNameAndPassword(String username, String password);
    public Optional<User> findUserByUserName(String username);
    public User findUserById(int id);

    public User findByEmail(String email);
    public User findByUserName(String username);
}
