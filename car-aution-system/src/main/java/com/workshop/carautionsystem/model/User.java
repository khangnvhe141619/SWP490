package com.workshop.carautionsystem.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames= {
                "username"
        }),
        @UniqueConstraint(columnNames= {
                "email"
        }),
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Username")
    private String userName;
    @Column(name = "Password")
    private String password;
    @Column(name = "FName")
    private String fName;
    @Column(name = "LName")
    private String lName;
    @Column(name = "Email")
    private String email;
    @Column(name = "Phone")
    private String phone;

    @Column(name = "Enabled")
    private int enabled;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "ID"))
    private Collection<Role> roles;

}
