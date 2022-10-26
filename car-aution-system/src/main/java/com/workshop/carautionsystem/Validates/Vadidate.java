package com.workshop.carautionsystem.Validates;

import com.workshop.carautionsystem.model.Car;
import com.workshop.carautionsystem.model.CarTypes;
import com.workshop.carautionsystem.model.Category;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Vadidate {

    public boolean isExistCar(Iterable<Car> listCar, String nameCar, String model,
                              String color, CarTypes carTyple, Category category){
        for(Car car : listCar){
            if (nameCar.toLowerCase().equalsIgnoreCase(car.getNameCar().toLowerCase())
                && model.toLowerCase().equalsIgnoreCase(car.getModel().toLowerCase())
                && color.toLowerCase().equalsIgnoreCase(car.getColor().toLowerCase())
                && carTyple == car.getCartypeID()
                && category == car.getCategoryID())
                return true;
        }
        return false;
    }
}
