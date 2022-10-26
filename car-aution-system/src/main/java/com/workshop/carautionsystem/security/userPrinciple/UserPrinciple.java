package com.workshop.carautionsystem.security.userPrinciple;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.workshop.carautionsystem.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrinciple implements UserDetails {
    private int id;

    private String userName;

    @JsonIgnore
    private String password;

    private String fName;

    private String lName;

    private String email;

    private int phone;
    Collection<? extends GrantedAuthority> role;
    private int enabled;
    public UserPrinciple() {
    }

    public UserPrinciple(int id, String userName, String password, String fName, String lName, String email, int phone, Collection<? extends GrantedAuthority> role,int enabled) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.enabled=enabled;
    }
    public static UserPrinciple build(User user){
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());
        return new UserPrinciple(user.getId(),
                user.getUserName(),
                user.getPassword(),
                user.getfName(),
                user.getlName(),
                user.getEmail(),
                user.getPhone(),
                authorities,user.getEnabled());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Collection<? extends GrantedAuthority> getRole() {
        return role;
    }

    public void setRole(Collection<? extends GrantedAuthority> role) {
        this.role = role;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
