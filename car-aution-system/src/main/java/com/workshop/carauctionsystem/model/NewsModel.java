package com.workshop.carauctionsystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsModel {
    private Long id;
    private String title;
    private String author;
    private String shortDescribe;
    private String describe;
    private Timestamp createAt;
    private String img;
    private String describe1;
    private String describe2;
    private String describe3;
    private String describe4;

}
