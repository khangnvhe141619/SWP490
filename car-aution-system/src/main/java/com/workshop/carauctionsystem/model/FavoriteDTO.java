package com.workshop.carauctionsystem.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class FavoriteDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "id")
    private int id;
     @Column(name = "userid")
    private int userId;
     @Column(name = "carid")
    private int carId;
     @Column(name = "createdat")
    private Timestamp createdAt;
     @Column(name = "number")
    private int number;
     @Column(name = "carname")
    private String carName;
     @Column(name = "imgpath")
    private String imgPath;
}
