package oodj.assignment.oopskylinecarrentalsystem.model;

import oodj.assignment.oopskylinecarrentalsystem.interfaces.FileWrite;
import oodj.assignment.oopskylinecarrentalsystem.interfaces.Searchable;

import java.util.ArrayList;
import java.util.List;

// OOP Concept Implemented: Encapsulation, Abstraction, Run-time Polymorphism
public class Car implements FileWrite, Searchable {
    private String id;
    private String brand;
    private String model;
    private String type;
    private String transmissionType;
    private float dailyRate;


    public Car(String id, String brand, String model, String type, String transmissionType, float dailyRate) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.type = type;
        this.transmissionType = transmissionType;
        this.dailyRate = dailyRate;
    }


    public Car(String[] registeredCar) {
        this.id = registeredCar[0];
        this.brand = registeredCar[1];
        this.model = registeredCar[2];
        this.type = registeredCar[3];
        this.transmissionType = registeredCar[4];
        this.dailyRate = Float.parseFloat(registeredCar[5]);
    }


    // Returns the ID of this car.
    public String getId() {
        return id;
    }

    // Sets the ID of this car.
    public void setId(String id) {
        this.id = id;
    }

    // Returns the brand of this car.
    public String getBrand() {
        return brand;
    }

    // Sets the brand of this car.
    public void setBrand(String brand) {
        this.brand = brand;
    }

    // Returns the model of this car.
    public String getModel() {
        return model;
    }

    // Sets the model of this car.
    public void setModel(String model) {
        this.model = model;
    }

    // Returns the car type of this car.
    public String getType() {
        return type;
    }

    // Sets the car type of this car.
    public void setType(String type) {
        this.type = type;
    }

    // Returns the transmission type of this car.
    public String getTransmissionType() {
        return transmissionType;
    }

    // Sets the transmission type of this car.
    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    // Returns the daily rate of this car.
    public float getDailyRate() {
        return dailyRate;
    }

    // Sets the daily rate of this car.
    public void setDailyRate(float dailyRate) {
        this.dailyRate = (float) (Math.round(dailyRate * 100.0) / 100.0);
    }

    // To format the Email
    public String emailFormat() {
        return String.format("<a style=\"font-weight:bold\">%s %s (%s)</a>", brand, model, id);
    }

    // OOP Concept Implemented: Run-Time Polymorphism
    @Override
    public List<String> getSearchableProperties() {                 //Overriden method for get car properties from searched
        List<String> searchableProperties = new ArrayList<>();
        searchableProperties.add(id);
        searchableProperties.add(brand);
        searchableProperties.add(model);
        searchableProperties.add(type);
        searchableProperties.add(transmissionType);

        return searchableProperties;
    }

    // OOP Concept Implemented: Run-Time Polymorphism
    @Override
    public String fileFormat() {                //Overriden method for joining car string format in file
        return String.join(" || ", id, brand, model, type, transmissionType, String.valueOf(dailyRate));
    }
}
