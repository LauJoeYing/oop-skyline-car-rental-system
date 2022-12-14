package oodj.assignment.oopskylinecarrentalsystem;

public enum ImagePath {

    CAMRY("C:\\Users\\User\\Desktop\\demo\\src\\main\\resources\\img\\TOYOTA_CAMRY.jpeg"),
    MYVI("C:\\Users\\User\\Desktop\\demo\\src\\main\\resources\\img\\PERODUA_MYVI.jpeg"),
    BEZZA("C:\\Users\\User\\Desktop\\demo\\src\\main\\resources\\img\\PERODUA_BEZZA.jpeg"),
    CIVIC("C:\\Users\\User\\Desktop\\demo\\src\\main\\resources\\img\\HONDA_CIVIC.jpeg"),
    CRV("C:\\Users\\User\\Desktop\\demo\\src\\main\\resources\\img\\HONDA_CRV.jpeg"),
    SKYLINE("C:\\Users\\User\\Desktop\\demo\\src\\main\\resources\\img\\Skyline_Logo.png"),
    PROFILE("C:\\Users\\User\\Desktop\\demo\\src\\main\\resources\\img\\profile.png"),
    TNG("C:\\Users\\User\\Desktop\\demo\\src\\main\\resources\\img\\tng.png")
    ;

    private String imageSource;

    ImagePath(String imageSource){
        this.imageSource = imageSource;
    }

    public String getImageSource() {
        return imageSource;
    }

    /**
     * CALL BY ImagePath.CAMRY.getImageSource()
     */


}
