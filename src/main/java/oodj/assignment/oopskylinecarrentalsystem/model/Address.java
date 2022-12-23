package oodj.assignment.oopskylinecarrentalsystem.model;

public class Address {
    private String unitNumber;
    private String streetAddressLine1;
    private String streetAddressLine2;
    private String city;
    private String postcode;
    private String state;

    public Address(String unitNumber, String streetAddressLine1, String city, String postcode, String state) {      //Constructor for Address Class
        this.unitNumber = unitNumber;
        this.streetAddressLine1 = streetAddressLine1;
        this.city = city;
        this.postcode = postcode;
        this.state = state;
    }

    public Address(String unitNumber, String streetAddressLine1, String streetAddressLine2, String city, String postcode, String state) { //OverloadedConstructor for Address Class
        this.unitNumber = unitNumber;
        this.streetAddressLine1 = streetAddressLine1;
        this.streetAddressLine2 = streetAddressLine2;
        this.city = city;
        this.postcode = postcode;
        this.state = state;
    }

    public Address(String[] registeredAddress) {                //Overloaded Constructor for Address Class
        this.unitNumber = registeredAddress[0];
        this.streetAddressLine1 = registeredAddress[1];
        if (registeredAddress[2].equals("-")) {
            this.streetAddressLine2 = null;
        } else {
            this.streetAddressLine2 = registeredAddress[2];
        }
        this.city = registeredAddress[3];
        this.postcode = registeredAddress[4];
        this.state = registeredAddress[5];
    }


    /**
     *
     * Below are getter and setters for each object attributes
     */
    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getStreetAddressLine1() {
        return streetAddressLine1;
    }

    public void setStreetAddressLine1(String streetAddressLine1) {
        this.streetAddressLine1 = streetAddressLine1;
    }

    public String getStreetAddressLine2() {
        return streetAddressLine2;
    }

    public void setStreetAddressLine2(String streetAddressLine2) {
        this.streetAddressLine2 = streetAddressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String fileFormat() {
        String addressLine2;
        if (!(streetAddressLine2 == null)) {
            addressLine2 = streetAddressLine2;
        } else {
            addressLine2 = "-";
        }

        return String.join(" | ", unitNumber, streetAddressLine1, addressLine2, city, postcode, state);
    }



    @Override
    public String toString() {              //Overriden toString method for Address Class Object
        if (streetAddressLine2 == null) {
            return String.join(", ",
                    unitNumber,
                    streetAddressLine1,
                    String.join(" ",
                            postcode,
                            city
                    ),
                    state
            );
        }

        return String.join(", ",
                unitNumber,
                streetAddressLine1,
                streetAddressLine2,
                String.join(" ",
                        postcode,
                        city
                ),
                state
        );
    }
}
