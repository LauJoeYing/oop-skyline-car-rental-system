package oodj.assignment.oopskylinecarrentalsystem.util;

import oodj.assignment.oopskylinecarrentalsystem.constant.IMAGEPATH;

public class ImageUtils {               //

    public static String ImgAssign(String model){
        return switch (model) {
            case "Camry" -> IMAGEPATH.CAMRY;
            case "Myvi" -> IMAGEPATH.MYVI;
            case "Bezza" -> IMAGEPATH.BEZZA;
            case "Civic" -> IMAGEPATH.CIVIC;
            case "CR-V" -> IMAGEPATH.CRV;
            default -> null;
        };
    }
}
