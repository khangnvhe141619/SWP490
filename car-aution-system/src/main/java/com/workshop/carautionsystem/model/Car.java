package com.workshop.carautionsystem.model;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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

    @OneToOne
    @JoinColumn(name = "Cartype_id",referencedColumnName = "id")
    private CarTypes cartypeID;

    @OneToOne
    @JoinColumn(name = "Category_id",referencedColumnName = "id")
    private Category categoryID;

    @OneToOne
    @JoinColumn(name = "Session_id", referencedColumnName = "id")
    private Session sessionID;

    @OneToOne
    @JoinColumn(name = "Seller_id", referencedColumnName = "id")
    private User sellerID;

    @Column(name ="Description")
    private String description;

    public Car() {
    }

    public Car(String nameCar, String picture, Double price, String model, Integer yearOfMake,
               String color, CarTypes cartypeID, Category categoryID, Session sessionID, User sellerID,String description) {
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
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public CarTypes getCartypeID() {
        return cartypeID;
    }

    public void setCartypeID(CarTypes cartypeID) {
        this.cartypeID = cartypeID;
    }

    public Category getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Category categoryID) {
        this.categoryID = categoryID;
    }

    public Session getSessionID() {
        return sessionID;
    }

    public void setSessionID(Session sessionID) {
        this.sessionID = sessionID;
    }

    public User getSellerID() {
        return sellerID;
    }

    public void setSellerID(User sellerID) {
        this.sellerID = sellerID;
    }

    public Integer getYearMake() {
        return yearMake;
    }

    public void setYearMake(Integer yearMake) {
        this.yearMake = yearMake;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
                ", description=" + description +
                '}';
    }
}
