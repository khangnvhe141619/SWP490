package com.workshop.carautionsystem.security.jwt;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {


    private String token;
    private String type="Bearer";
    private String name;
    private Collection<? extends GrantedAuthority> role;

    public JwtResponse( String token, String type, String name, Collection<? extends GrantedAuthority> role) {

        this.token = token;
        this.type = type;
        this.name = name;
        this.role = role;
    }
    public JwtResponse( String token,  String name, Collection<? extends GrantedAuthority> role) {

        this.token = token;

        this.name = name;
        this.role = role;
    }

    public JwtResponse() {
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<? extends GrantedAuthority> getRole() {
        return role;
    }

    public void setRole(Collection<? extends GrantedAuthority> role) {
        this.role = role;
    }
}
