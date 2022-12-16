package oodj.assignment.oopskylinecarrentalsystem.config;

public class ImageConfig {

    public static String ImgAssign(String model){
        return switch (model) {
            case "Camry" -> ImagePath.CAMRY.getImageSource();
            case "Myvi" -> ImagePath.MYVI.getImageSource();
            case "Bezza" -> ImagePath.BEZZA.getImageSource();
            case "Civic" -> ImagePath.CIVIC.getImageSource();
            case "CR-V" -> ImagePath.CRV.getImageSource();
            default -> null;
        };
    }
}
