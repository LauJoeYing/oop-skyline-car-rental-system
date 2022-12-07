package oodj.assignment.oopskylinecarrentalsystem;

public class Address {
    private String unitNum;
    private String streetLine1;
    private String streetLine2;
    private String postcode;
    private String city;
    private String state;

    public Address( String[] addressDetails) {

        for ( String addressDetail: addressDetails ) {
            addressDetail = addressDetail.trim();
        }
        this.unitNum = addressDetails[1];
        this.streetLine1 = addressDetails[2];
        this.streetLine2 = addressDetails[3];
        this.postcode = addressDetails[4];
        this.city = addressDetails[5];
        this.state = addressDetails[6];
    }

    public Address(String unitNum, String streetLine1, String streetLine2, String postcode, String city, String state) {
        this.unitNum = unitNum;
        this.streetLine1 = streetLine1;
        this.streetLine2 = streetLine2;
        this.postcode = postcode;
        this.city = city;
        this.state = state;
    }

    public String getUnitNum() {
        return unitNum;
    }

    public void setUnitNum(String unitNum) {
        this.unitNum = unitNum;
    }

    public String getStreetLine1() {
        return streetLine1;
    }

    public void setStreetLine1(String streetLine1) {
        this.streetLine1 = streetLine1;
    }

    public String getStreetLine2() {
        return streetLine2;
    }

    public void setStreetLine2(String streetLine2) {
        this.streetLine2 = streetLine2;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
