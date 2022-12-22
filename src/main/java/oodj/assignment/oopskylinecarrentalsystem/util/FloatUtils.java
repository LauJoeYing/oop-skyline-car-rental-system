package oodj.assignment.oopskylinecarrentalsystem.util;

public class FloatUtils {
    public static float roundToTwoDecimals(float floatA) {
        return ((float) (Math.round(floatA * 100.0) / 100.0));
    }

}
