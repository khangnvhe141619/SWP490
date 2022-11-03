package com.workshop.carautionsystem.model;

import javax.persistence.*;

@Entity
@Table(name = "category_car")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Name_Brand")
    private String nameCategory;

    public Category() {
    }

    public Category(Integer id, String nameCategory) {
        this.id = id;
        this.nameCategory = nameCategory;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name_Category='" + nameCategory + '\'' +
                '}';
    }
}
