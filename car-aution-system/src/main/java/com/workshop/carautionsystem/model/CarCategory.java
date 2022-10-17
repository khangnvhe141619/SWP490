package com.workshop.carautionsystem.model;

import javax.persistence.*;

@Entity
@Table(name = "category_car")
public class CarCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Name_Brand")
    private String nameBrand;

    public CarCategory() {
    }

    public CarCategory(String nameBrand) {
        this.nameBrand = nameBrand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameBrand() {
        return nameBrand;
    }

    public void setNameBrand(String nameBrand) {
        this.nameBrand = nameBrand;
    }

    @Override
    public String toString() {
        return "CarCategory{" +
                "id=" + id +
                ", nameBrand='" + nameBrand + '\'' +
                '}';
    }
}
