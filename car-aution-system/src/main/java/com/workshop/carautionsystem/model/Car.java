package com.workshop.carautionsystem.model;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Name_car")
    private String nameCar;

    @Column(name = "Picture")
    private String picture;

    @Column(name = "Price")
    private Double price;

    @Column(name = "Model")
    private String model;

    @Column(name = "yearofmake")
    private Integer yearMake;

    @Column(name = "Color")
    private String color;

    @Column(name = "Cartype_id")
    private Integer cartypeID;

    @Column(name = "Category_id")
    private Integer categoryID;

    @Column(name = "Session_id")
    private Integer sessionID;

    @Column(name = "Seller_id")
    private Integer sellerID;

    public Car() {
    }

    public Car(String nameCar, String picture, Double price, String model, Integer yearOfMake,
               String color, Integer cartypeID, Integer categoryID, Integer sessionID, Integer sellerID) {
        this.nameCar = nameCar;
        this.picture = picture;
        this.price = price;
        this.model = model;
        this.yearMake = yearOfMake;
        this.color = color;
        this.cartypeID = cartypeID;
        this.categoryID = categoryID;
        this.sessionID = sessionID;
        this.sellerID = sellerID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCar() {
        return nameCar;
    }

    public void setNameCar(String nameCar) {
        this.nameCar = nameCar;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYearOfMake() {
        return yearMake;
    }

    public void setYearOfMake(Integer yearOfMake) {
        this.yearMake = yearOfMake;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getCartypeID() {
        return cartypeID;
    }

    public void setCartypeID(Integer cartypeID) {
        this.cartypeID = cartypeID;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public Integer getSessionID() {
        return sessionID;
    }

    public void setSessionID(Integer sessionID) {
        this.sessionID = sessionID;
    }

    public Integer getSellerID() {
        return sellerID;
    }

    public void setSellerID(Integer sellerID) {
        this.sellerID = sellerID;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", nameCar='" + nameCar + '\'' +
                ", picture='" + picture + '\'' +
                ", price=" + price +
                ", model='" + model + '\'' +
                ", yearOfMake=" + yearMake +
                ", color='" + color + '\'' +
                ", cartypeID=" + cartypeID +
                ", categoryID=" + categoryID +
                ", sessionID=" + sessionID +
                ", sellerID=" + sellerID +
                '}';
    }
}
