package com.workshop.carauctionsystem.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "[title]")
    private String title;

    @Column(name = "[author]")
    @Pattern(regexp = "[a-zA-Z\\s]+",message = "Name not number and special characters")
    private String author;

    @Column(name = "[shortdescribe]")
    private String shortDescribe;

    @Column(name = "[describe]")
    private String describe;

    @Column(name = "createat")
    private Timestamp createAt;

    @Column(name = "img")
    private String img;

    @Column(name = "describe1")
    private String describe1;

    @Column(name = "describe2")
    private String describe2;

    @Column(name = "describe3")
    private String describe3;

    @Column(name = "describe4")
    private String describe4;

}
