package oodj.assignment.oopskylinecarrentalsystem;

public enum View {
    LOGIN("loginPage.fxml"),
    ADMIN_MAIN("adminMain.fxml");

    private String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
