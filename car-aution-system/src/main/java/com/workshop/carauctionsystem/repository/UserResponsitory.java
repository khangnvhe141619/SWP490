package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserResponsitory extends JpaRepository<User, Integer> {
    public User findUserByUserNameAndPassword(String username, String password);
    public User findUserByUserName(String username);
    public User findUserById(int id);
    @Modifying
    @Query(value = "update user u set u.fullname = :fullName, u.username = :userName, u.phone = :phone, u.email = :email where u.id = :id", nativeQuery = true)
    public void updateUserById(String fullName, String userName, String phone, String email, int id);
}
