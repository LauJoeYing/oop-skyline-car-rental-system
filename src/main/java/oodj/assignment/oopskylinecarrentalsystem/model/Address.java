package oodj.assignment.oopskylinecarrentalsystem.model;

// OOP Concept: Encapsulation
public class Address {
    private String unitNumber;
    private String streetAddressLine1;
    private String streetAddressLine2;
    private String city;
    private String postcode;
    private String state;

    // This constructor initializes an Address object with the specified unit number, street address, city, postcode, and state
    // OOP Concept: Encapsulation
    public Address(String unitNumber, String streetAddressLine1, String city, String postcode, String state) {
        this.unitNumber = unitNumber;
        this.streetAddressLine1 = streetAddressLine1;
        this.city = city;
        this.postcode = postcode;
        this.state = state;
    }


    // OOP Concept: Encapsulation
    public Address(String unitNumber, String streetAddressLine1, String streetAddressLine2, String city, String postcode, String state) {
        this.unitNumber = unitNumber;
        this.streetAddressLine1 = streetAddressLine1;
        this.streetAddressLine2 = streetAddressLine2;
        this.city = city;
        this.postcode = postcode;
        this.state = state;
    }

    // OOP Concept: Encapsulation
    public Address(String[] registeredAddress) {
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

    // This method returns the current Unit Number of the object
    public String getUnitNumber() {
        return unitNumber;
    }

    // Sets the Unit Number field of the address to the provided value.
    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    // This method returns the current Street Address Line 1 of the object
    public String getStreetAddressLine1() {
        return streetAddressLine1;
    }


    // Sets the Street Address Line 2 field of the address to the provided value.
    public void setStreetAddressLine1(String streetAddressLine1) {
        this.streetAddressLine1 = streetAddressLine1;
    }

    // This method returns the current Street Address Line 2 of the object
    public String getStreetAddressLine2() {
        return streetAddressLine2;
    }

    // Sets the Street Address Line 2 field of the address to the provided value.
    public void setStreetAddressLine2(String streetAddressLine2) {
        this.streetAddressLine2 = streetAddressLine2;
    }

    // This method returns the current city of the object
    public String getCity() {
        return city;
    }

    // Sets the city field of the address to the provided value.
    public void setCity(String city) {
        this.city = city;
    }

    // This method returns the current postcode of the object
    public String getPostcode() {
        return postcode;
    }

    // Sets the postcode field of the address to the provided value.
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    // This method returns the current state of the object
    public String getState() {
        return state;
    }

    // Sets the state field of the address to the provided value.
    public void setState(String state) {
        this.state = state;
    }


    // To Return a string representation of the address in a specific file format
    // The string will contain the unit number, street address line 1, street address line 2 (or "-" if null),
    // city, postcode, and state separated by " | "
    public String fileFormat() {
        String addressLine2;
        if (!(streetAddressLine2 == null)) {
            addressLine2 = streetAddressLine2;
        } else {
            addressLine2 = "-";
        }

        return String.join(" | ", unitNumber, streetAddressLine1, addressLine2, city, postcode, state);
    }

    // Address Formatter
    // Returns a string representation of the address.
    // If the streetAddressLine2 field is null:
    // the string will contain the unit number, street address line 1, city, postcode, and state separated by commas.
    @Override
    public String toString() {
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

