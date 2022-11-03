package oodj.assignment.oopskylinecarrentalsystem;

public enum View {
    LOGIN("loginPage.fxml"),
    SIGNUP("Sign_Up.fxml"),
    ADMIN_MAIN("adminMain.fxml"),
    CUST_MAIN("Customer_Main.fxml");


    private String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
