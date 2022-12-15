package oodj.assignment.oopskylinecarrentalsystem.config;

public class StringConfig {
    public static boolean isAnyContainsBlank(String ... strings) {
        for (String string: strings) {
            if (string.equals("")) {
                return true;
            }
        }
        return false;
    }
}
