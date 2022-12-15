package com.workshop.carauctionsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brandname")
    private String brandName;

    @Column(name = "imgpath")
    private String imgPath;

    @Column(name = "status")
    private Long status;

    public Brand(String brandName, String imgPath, Long status) {
        this.brandName = brandName;
        this.imgPath = imgPath;
        this.status = status;
    }
}
