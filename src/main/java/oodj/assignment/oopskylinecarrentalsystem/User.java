package oodj.assignment.oopskylinecarrentalsystem;


public class User {

    private String userType;
    private String username;
    private String password;
    private String name;
    private String gender;
    private String phoneNum;
    private String email;
    private String ic;
    private String unitNum;
    private String streetLine1;
    private String streetLine2;
    private String postcode;
    private String city;
    private String state;

    public User(String[] userDetails ){

        for (String userDetail: userDetails) {
            userDetail = userDetail.trim();
        }

        this.userType = userDetails[0];
        this.username = userDetails[1];
        this.password = userDetails[2];
        this.name = userDetails[3];
        this.gender = userDetails[4];
        this.phoneNum = userDetails[5];
        this.email = userDetails[6];
        this.ic = userDetails[7];
        this.unitNum = userDetails[8];
        this.streetLine1 = userDetails[9];
        this.streetLine2 = userDetails[10];
        this.postcode = userDetails[11];
        this.city = userDetails[12];
        this.state = userDetails[13];

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
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

