package oodj.assignment.oopskylinecarrentalsystem.model;

import oodj.assignment.oopskylinecarrentalsystem.interfaces.FileWrite;
import oodj.assignment.oopskylinecarrentalsystem.interfaces.Searchable;

import java.util.ArrayList;
import java.util.List;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public float getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(float dailyRate) {
        this.dailyRate = (float) (Math.round(dailyRate * 100.0) / 100.0);
    }

    public String emailFormat() {
        return String.format("<a style=\"font-weight:bold\">%s %s (%s)</a>", brand, model, id);
    }

    @Override
    public List<String> getSearchableProperties() {
        List<String> searchableProperties = new ArrayList<>();
        searchableProperties.add(id);
        searchableProperties.add(brand);
        searchableProperties.add(model);
        searchableProperties.add(type);
        searchableProperties.add(transmissionType);

        return searchableProperties;
    }
    @Override
    public String fileFormat() {
        return String.join(" || ", id, brand, model, type, transmissionType, String.valueOf(dailyRate));
    }
}
