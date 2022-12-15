package oodj.assignment.oopskylinecarrentalsystem.config;

public enum ImagePath {

    CAMRY("C:\\Users\\User\\Documents\\GitHub\\oop-skyline-car-rental-system\\src\\main\\resources\\oodj\\assignment\\oopskylinecarrentalsystem\\img\\TOYOTA_CAMRY.jpeg"),
    MYVI("C:\\Users\\User\\Documents\\GitHub\\oop-skyline-car-rental-system\\src\\main\\resources\\oodj\\assignment\\oopskylinecarrentalsystem\\img\\PERODUA_MYVI.jpeg"),
    BEZZA("C:\\Users\\User\\Documents\\GitHub\\oop-skyline-car-rental-system\\src\\main\\resources\\oodj\\assignment\\oopskylinecarrentalsystem\\img\\PERODUA_BEZZA.jpeg"),
    CIVIC("C:\\Users\\User\\Documents\\GitHub\\oop-skyline-car-rental-system\\src\\main\\resources\\oodj\\assignment\\oopskylinecarrentalsystem\\img\\HONDA_CIVIC.jpeg"),
    CRV("C:\\Users\\User\\Documents\\GitHub\\oop-skyline-car-rental-system\\src\\main\\resources\\oodj\\assignment\\oopskylinecarrentalsystem\\img\\HONDA_CRV.jpeg"),
    SKYLINE("C:\\Users\\User\\Documents\\GitHub\\oop-skyline-car-rental-system\\src\\main\\resources\\oodj\\assignment\\oopskylinecarrentalsystem\\img\\Skyline_Logo.png"),
    PROFILE("C:\\Users\\User\\Documents\\GitHub\\oop-skyline-car-rental-system\\src\\main\\resources\\oodj\\assignment\\oopskylinecarrentalsystem\\img\\profile.png"),
    TNG("C:\\Users\\User\\Documents\\GitHub\\oop-skyline-car-rental-system\\src\\main\\resources\\oodj\\assignment\\oopskylinecarrentalsystem\\img\\tng.png"),
//    BOOKICO("C:\\Users\\User\\Desktop\\demo\\src\\main\\resources\\img\\book-and-pencil.png"),
//    USERICO("C:\\Users\\User\\Desktop\\demo\\src\\main\\resources\\img\\user-male-circle--v1.png"),
//    TESTICO("C:\\Users\\User\\Desktop\\demo\\src\\main\\resources\\img\\test-partial-passed.png"),
//    WALLETICO("C:\\Users\\User\\Desktop\\demo\\src\\main\\resources\\img\\wallet.png")
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
