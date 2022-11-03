package com.workshop.carautionsystem.repository;

import com.workshop.carautionsystem.model.User;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserResponsitory extends JpaRepository<User,Integer> {
    public Optional<User> findUserByUserNameAndPassword(String username, String password);
    public List<User> findUserByUserName(String username);
    public User findUserById(int id);
}
