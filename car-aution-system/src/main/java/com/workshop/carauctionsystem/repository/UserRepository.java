package com.workshop.carauctionsystem.repository;

import com.workshop.carauctionsystem.entity.Role;
import com.workshop.carauctionsystem.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByEmail(String email);
    public User findByUserName(String username);
    public User findUserByUserNameAndPassword(String username, String password);
    public User findUserByUserName(String username);
    public User findUserById(int id);
    @Modifying
    @Query(value = "update user u set u.fullname = :fullName, u.username = :userName, u.phone = :phone, u.email = :email where u.id = :id", nativeQuery = true)
    public void updateUserById(String fullName, String userName, String phone, String email, int id);
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE `swp490_cab`.`user` SET `password` = :pass WHERE (`id` = :id);")
    public void changePasswordById(int id, String pass);

    @Query(nativeQuery = true, value = "select * from user where user.enabled = 0")
    public Page<User> ListUserBan(Pageable pageable);

    @Query(nativeQuery = true, value = "select * from user where user.username like %?1% and user.enabled = 0 order by id desc")
    public Page<User> ListUserBan(Pageable pageable, String name);

    @Query(nativeQuery = true, value = "select * from user where user.enabled = 1")
    public Page<User> ListUserUnban(Pageable pageable);

    @Query(nativeQuery = true, value = "select * from user where user.username like %?1% and user.enabled = 1 order by id desc")
    public Page<User> ListUserUnban(Pageable pageable,String name);

    @Modifying
    @Query(nativeQuery = true, value = "update user set user.enabled = 1 where id = ?1")
    @Transactional
    public void UnBanUser(Long id);

    @Modifying
    @Query(nativeQuery = true, value = "update user set user.enabled = 0 where id = ?1")
    @Transactional
    public void BanUser(Long id);

    @Query(nativeQuery = true,value = "select * from user join user_role on user.id = user_role.userid where userid = 2")
    public List<User> getRoleByAdminCar();

    @Query(nativeQuery = true,value = "select * from user join user_role on user.id = user_role.userid where userid = 3")
    public List<User> getRoleByAdminAuction();

    @Query(nativeQuery = true, value = "select * from user join roomdetailplayer on user.id = roomdetailplayer.userId where roomdetailplayer.roomId = 1")
    public List<User> listUserByRoom();
}
