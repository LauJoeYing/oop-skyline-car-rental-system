package oodj.assignment.oopskylinecarrentalsystem;

public class Transaction {

    public String getTxDate() {
        return txDate;
    }

    public void setTxDate(String txDate) {
        this.txDate = txDate;
    }

    public String getTxID() {
        return txID;
    }

    public void setTxID(String txID) {
        this.txID = txID;
    }

    private String txDate;
    private String txID;
    private String carID;

    private String carBrand;

    private String carModel;

    private String startDate;

    private String endDate;

    private String duration;

    private String total;

    public Transaction(String[] txDetails){

        for (String txDetail: txDetails ){
            txDetail = txDetail.trim();
        }

        this.txID = txDetails[0];
        this.txDate = txDetails[1];
        this.carID = txDetails[2];
        this.carBrand = txDetails[3];
        this.carModel = txDetails[4];
        this.startDate = txDetails[5];
        this.endDate = txDetails[6];
        this.duration = txDetails[7];
        this.total = txDetails[8];

    }

    public Transaction(String carID, String carBrand, String carModel, String startDate, String endDate, String duration, String total) {
        this.carID = carID;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.total = total;
    }


    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
