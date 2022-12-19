package com.workshop.carauctionsystem.service.impl;

import com.workshop.carauctionsystem.entity.Role;
import com.workshop.carauctionsystem.entity.Room;
import com.workshop.carauctionsystem.entity.User;
import com.workshop.carauctionsystem.exception.NotFoundException;
import com.workshop.carauctionsystem.model.UserDTO;
import com.workshop.carauctionsystem.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import java.sql.Timestamp;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
@Rollback(value = true)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {
        "com.workshop.carauctionsystem.repository",
        "com.workshop.carauctionsystem.service"
})
class UserServiceImplTest {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    RoleRepository roleRepository;

    @Test
    void givenValidInformation_whenLogin_thenSucceed() {
        Collection<Role> roles = null;
        User u = new User(4, "viethoang", "$10$Kr9bEuihI1iPYZL2lSR5w.n47LowWVGpuLqQQeqRlaagl.zr8r6.u",
                "Viet Hoang", "viethoang42gmail.com", "0234123123",
                "img/avatar.jpg", 1, null, null, roles);
        User u1 = userService.login("viethoang");
        assertEquals(u.getUserName(), u1.getUserName());
        assertEquals(u.getEmail(), u1.getEmail());
        assertEquals(u.getPhone(), u1.getPhone());
    }

    @Test
    void givenValidUsername_whenSearch_thenUserFound() {
        Collection<Role> roles = null;
        User u = new User(4, "viethoang", "$10$Kr9bEuihI1iPYZL2lSR5w.n47LowWVGpuLqQQeqRlaagl.zr8r6.u",
                "Viet Hoang", "viethoang42gmail.com", "0234123123",
                "img", 1, null, null, roles);
        User u1 = userService.findByUsername("viethoang");
        assertEquals(u.getUserName(), u1.getUserName());
        assertEquals(u.getEmail(), u1.getEmail());
        assertEquals(u.getPhone(), u1.getPhone());
    }

    @Test
    void givenValidEmail_whenSearch_thenUserFound() {
        Collection<Role> roles = null;
        User u = new User(4, "viethoang", "$10$Kr9bEuihI1iPYZL2lSR5w.n47LowWVGpuLqQQeqRlaagl.zr8r6.u",
                "Viet Hoang", "viethoang42gmail.com", "0234123123",
                "img", 1, null, null, roles);
        User u1 = userService.findByEmail("viethoang42gmail.com");
        assertEquals(u.getUserName(), u1.getUserName());
        assertEquals(u.getEmail(), u1.getEmail());
        assertEquals(u.getPhone(), u1.getPhone());
    }

    @Test
    void givenValidId_whenSearch_thenUserFound() {
        Collection<Role> roles = null;
        User u = new User(4, "viethoang", "$10$Kr9bEuihI1iPYZL2lSR5w.n47LowWVGpuLqQQeqRlaagl.zr8r6.u",
                "Viet Hoang", "viethoang42gmail.com", "0234123123",
                "img", 1, null, null, roles);
        User u1 = userService.findUserById(4);
        assertEquals(u.getUserName(), u1.getUserName());
        assertEquals(u.getEmail(), u1.getEmail());
        assertEquals(u.getPhone(), u1.getPhone());
    }

    @Test
    void givenValidPass_whenChangePassword_thenSucceed() {
        userService.changePassword(4, "1234");
    }

    @Test
    void givenValidInformation_whenUpdateUser_thenSucceed() {
        userService.updateUserById("Khang", "khang123", "0111111111", "khang123@gmail.com", 4);
    }

    @Test
    void givenRoom_thenDisplayListUser() {
        List<User> lst = userService.listUserByRoom();
        assertEquals(1, lst.size());
    }

    @Test
    void givenVerificationToken_thenUserFound() {
        String verify = "097abc0e-8d16-4b57-9d6b-39c97f4a37a0";
        User u = userService.getUser(verify);
    }

    @Test
    void givenValidInformation_whenSaveRegisteredUser_thenSucceed() {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        Collection<Role> roles = Arrays.asList(roleRepository.findByName("ROLE_User"));
        User u = new User(8, "viethoang", "$10$Kr9bEuihI1iPYZL2lSR5w.n47LowWVGpuLqQQeqRlaagl.zr8r6.u",
                "Viet Hoang", "viethoang42gmail.com", "0234123123",
                "img/avatar.jpg", 1, null, timestamp, roles);
        userService.saveRegisteredUser(u);
    }

    @Test
    void whenEmailExist_thenTrue() {
        boolean _email = userService.isEmailExist("viethoang42gmail.com");
        assertEquals(true, _email);
    }

    @Test
    void givenValidInformation_whenRegisterNewUserAccount_thenSucceed() {
        UserDTO userDTO = new UserDTO("khang123", "123", "Khang", "khang123@gmail.com", "0111111111", "img/avatar.jpg", "empty", 4);
        userService.registerNewUserAccount(userDTO);
    }

    @Test
    void whenSaveVerificationTokenForUser_thenSucceed() {
        Collection<Role> roles = Arrays.asList(roleRepository.findByName("User"));
        User u = new User(8, "viethoang", "$10$Kr9bEuihI1iPYZL2lSR5w.n47LowWVGpuLqQQeqRlaagl.zr8r6.u",
                "Viet Hoang", "viethoang42gmail.com", "0234123123",
                "img", 1, null, null, roles);
        String token = UUID.randomUUID()
                .toString();
        userService.saveVerificationTokenForUser(u, token);
    }

    @Test
    void getVerificationToken() {
        String token = "097abc0e-8d16-4b57-9d6b-39c97f4a37a0";
        userService.getVerificationToken(token);
    }

    @Test
    void generateNewVerificationToken() {
        String token = "097abc0e-8d16-4b57-9d6b-39c97f4a37a0";
        userService.generateNewVerificationToken(token);
    }

    @Test
    void whenSavePasswordResetTokenForUser_thenSucceed() {
        Collection<Role> roles = Arrays.asList(roleRepository.findByName("User"));
        User u = new User(8, "viethoang", "$10$Kr9bEuihI1iPYZL2lSR5w.n47LowWVGpuLqQQeqRlaagl.zr8r6.u",
                "Viet Hoang", "viethoang42gmail.com", "0234123123",
                "img", 1, null, null, roles);
        String token = UUID.randomUUID()
                .toString();
        userService.savePasswordResetTokenForUser(u, token);
    }

    @Test
    void getPasswordResetToken() {
        String token = "076abc0e-8f56-4b54-9d6b-67c97f4a67a0";
        userService.getPasswordResetToken(token);
    }

    @Test
    void getUserByPasswordResetToken() {
        String token = "3e01555f-8ad6-4ad8-98b5-65e4f59bb67e";
        userService.getUserByPasswordResetToken(token);
    }

    @Test
    void givenValidPass_whenChangePassword_thenSucceed_1() {
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        Collection<Role> roles = Arrays.asList(roleRepository.findByName("User"));
        User u = new User(8, "viethoang", "$10$Kr9bEuihI1iPYZL2lSR5w.n47LowWVGpuLqQQeqRlaagl.zr8r6.u",
                "Viet Hoang", "viethoang42gmail.com", "0234123123",
                "img", 1, null, timestamp, roles);
        userService.changePassword(u, "1234");
    }

    @Test
    void whenSearch_thenListBannedUserFound() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(
                Sort.Order.desc("id")));
        Page<User> pr = userService.ListUserBan(pageable, null);
        assertEquals(0, pr.getTotalElements());
        Page<User> pr1 = userService.ListUserBan(pageable, "viethoang1");
        assertEquals(0, pr1.getTotalElements());
    }

    @Test
    void whenSearch_thenListUserFound() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by(
                Sort.Order.desc("id")));
        Page<User> pr = userService.ListUserUnBan(pageable, null);
        assertEquals(4, pr.getTotalElements());
        Page<User> pr1 = userService.ListUserUnBan(pageable, "viethoang");
        assertEquals(1, pr1.getTotalElements());
    }

    @Test
    void whenUnBanUser_thenSucceed() throws NotFoundException {
        userService.UnBanUser(4l);
    }

    @Test
    void whenBanUser_thenSucceed() throws NotFoundException {
        userService.BanUser(4l);
    }

    @Test
    void whenFindAdminCarRole_thenListFound() {
        List<User> lst = userService.getRoleByAdminCar();
        assertEquals(1, lst.size());
    }

    @Test
    void whenFindAdminAuctionRole_thenListFound() {
        List<User> lst = userService.getRoleByAdminAuction();
        assertEquals(1, lst.size());
    }
}