package oodj.assignment.oopskylinecarrentalsystem;

public class Car {

    private String carId;
    private String carBrand;
    private String carModel;
    private String carType;
    private String carTransmission;
    private String carRentalPrice;
    private String carStatus;

    public Car( String[] carDetails ) {


        for (String carDetail: carDetails ){
            carDetail = carDetail.trim();
        }

        this.carId = carDetails[0];
        this.carBrand = carDetails[1];
        this.carModel = carDetails[2];
        this.carType = carDetails[3];
        this.carTransmission = carDetails[4];
        this.carRentalPrice = carDetails[5];
        this.carStatus = carDetails[6];

    }



    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarTransmission() {
        return carTransmission;
    }

    public void setCarTransmission(String carTransmission) {
        this.carTransmission = carTransmission;
    }

    public String getCarRentalPrice() {
        return carRentalPrice;
    }

    public void setCarRentalPrice(String carRentalPrice) {
        this.carRentalPrice = carRentalPrice;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    @Override
    public String toString() {
        return String.join(" | ", carId, carBrand, carModel, carType, carTransmission, carRentalPrice, carStatus);
    }
}
