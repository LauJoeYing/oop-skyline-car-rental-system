package oodj.assignment.oopskylinecarrentalsystem.config;

public enum ImagePath {

    CAMRY("https://drive.google.com/uc?id=1pUGT31DTeDh59QNY64y4HSM9AMhdEMzj"),
    MYVI("https://drive.google.com/uc?id=1EiRzcU8S9Clwk9KT0OZzSB29crbGRGEQ"),
    BEZZA("https://drive.google.com/uc?id=1TlMvyTlBJ98nAGe0JGZm7ZFyo_p_R0Yi"),
    CIVIC("https://drive.google.com/uc?id=1_Wnm3LmDUuk9ZN9yW9nPGxOws_R4PujW"),
    CRV("https://drive.google.com/uc?id=1ezwBdII3-alESOJTWPpzTmpe4iftZ5RD"),
    SKYLINE("https://drive.google.com/uc?id=1Kizs441ErP6i7W7oEJrzcZ4nEiD8eJlo"),
    PROFILE("https://drive.google.com/uc?id=1O_PMpFMv1utGU3TpIrTGpap0uyQYMMEp"),
    TNG("https://drive.google.com/uc?id=1PXshLbdRJfWDE-dFaeyRARM8eh6jvxuv"),
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
