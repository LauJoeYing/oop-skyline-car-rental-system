package oodj.assignment.oopskylinecarrentalsystem.util;

public class StringUtils {
    public static boolean isAnyContainsBlank(String ... strings) {
        for (String string: strings) {
            if (string.equals("")) {
                return true;
            }
        }
        return false;
    }
}
