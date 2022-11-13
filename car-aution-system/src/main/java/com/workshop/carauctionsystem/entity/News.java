package com.workshop.carauctionsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    @Pattern(regexp = "[a-zA-Z]+",message = "Name not number and special characters")
    private String author;

    @Column(name = "shortdescribe")
    private String shortDescribe;

    @Column(name = "describe")
    private String describe;

    @Column(name = "createat")
    private Timestamp createAt;

    @Column(name = "img")
    private String img;
}
