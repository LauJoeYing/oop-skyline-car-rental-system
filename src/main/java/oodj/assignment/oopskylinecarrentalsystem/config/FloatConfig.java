package oodj.assignment.oopskylinecarrentalsystem.config;

public class FloatConfig {
    public static float roundToTwoDecimals(float floatA) {
        return ((float) (Math.round(floatA * 100.0) / 100.0));
    }

}
