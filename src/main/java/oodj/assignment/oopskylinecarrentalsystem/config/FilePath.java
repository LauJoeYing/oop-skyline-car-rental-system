package oodj.assignment.oopskylinecarrentalsystem.config;

public enum FilePath {

    USER("C:\\Users\\User\\Documents\\GitHub\\oop-skyline-car-rental-system\\src\\main\\resources\\oodj\\assignment\\oopskylinecarrentalsystem\\textfiles\\user.txt"),
    CAR("C:\\Users\\User\\Documents\\GitHub\\oop-skyline-car-rental-system\\src\\main\\resources\\oodj\\assignment\\oopskylinecarrentalsystem\\textfiles\\car.txt"),
    BOOKING(""),
    WALLET("C:\\Users\\User\\Documents\\GitHub\\oop-skyline-car-rental-system\\src\\main\\resources\\oodj\\assignment\\oopskylinecarrentalsystem\\textfiles\\wallet.txt"),
    TX(""),
    ;

    private String datafile;

    FilePath(String datafile){
        this.datafile = datafile;
    }

    public String getDataFile() {
        return datafile;
    }

    /**
     * CALL BY FilePath.USER.getDataFile()
     */


}