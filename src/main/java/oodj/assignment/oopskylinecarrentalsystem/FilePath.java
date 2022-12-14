package oodj.assignment.oopskylinecarrentalsystem;

public enum FilePath {

    USER("C:\\Users\\User\\Desktop\\demo\\src\\main\\resources\\com\\example\\demo\\textfiles\\user.txt"),
    CAR("C:\\Users\\User\\Desktop\\demo\\src\\main\\resources\\com\\example\\demo\\textfiles\\car.txt"),
    BOOKING(""),
    WALLET("C:\\Users\\User\\Desktop\\demo\\src\\main\\resources\\com\\example\\demo\\textfiles\\wallet.txt"),
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