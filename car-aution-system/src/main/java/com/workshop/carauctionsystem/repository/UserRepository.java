package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByEmail(String email);
    public User findByUserName(String username);
    public User findUserByUserName(String username);
    public User findUserById(int id);
    public User findUserByIdAndPassword(int id, String pass);
    @Modifying
    @Query(value = "update user u set u.fullname = :fullName, u.username = :userName, u.phone = :phone, u.email = :email where u.id = :id", nativeQuery = true)
    public void updateUserById(String fullName, String userName, String phone, String email, int id);
    @Modifying
    @Query(value = "UPDATE `swp490_cab`.`user` SET `password` = :newPassword WHERE (`id` = :id);", nativeQuery = true)
    public void changePasswordById(int id, String newPassword);
}
