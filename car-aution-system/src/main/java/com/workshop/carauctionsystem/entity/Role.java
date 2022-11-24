package com.workshop.carauctionsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "role")
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;
    @Column(name = "rolename")
    private String name;


}
