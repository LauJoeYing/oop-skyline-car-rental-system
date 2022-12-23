package oodj.assignment.oopskylinecarrentalsystem.util;

public class FloatUtils {           //Utility function for float type conversion
    public static float roundToTwoDecimals(float floatA) {
        return ((float) (Math.round(floatA * 100.0) / 100.0));
    }

}
