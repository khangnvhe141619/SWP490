package com.workshop.carautionsystem.model;

import javax.persistence.*;

@Entity
@Table(name = "cartypes")
public class CarTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "seatcapacity")
    private String seatCapacity;

    @Column(name = "name")
    private String name;

    public CarTypes() {
    }

    public CarTypes(int id, String seatCapacity, String name) {
        this.id = id;
        this.seatCapacity = seatCapacity;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(String seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CarTypes{" +
                "id=" + id +
                ", seatCapacity='" + seatCapacity + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
