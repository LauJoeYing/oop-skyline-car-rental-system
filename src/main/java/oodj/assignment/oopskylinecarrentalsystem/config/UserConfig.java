package oodj.assignment.oopskylinecarrentalsystem.config;



import oodj.assignment.oopskylinecarrentalsystem.model.Admin;
import oodj.assignment.oopskylinecarrentalsystem.model.Customer;
import oodj.assignment.oopskylinecarrentalsystem.model.User;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserConfig {
    private static final List<User> userList;
    private static String userFilePath = "";

    static {
        userList = new ArrayList<>();

        String userFilePathRegex = "(?<=IdeaProjects/oop-skyline-car-rental-system/)(target/classes)(?=/oodj/assignment/oopskylinecarrentalsystem/textfiles/User\\.txt$)";
        Pattern userFilePathPattern = Pattern.compile(userFilePathRegex);
        Matcher userFilePathMatcher = userFilePathPattern.matcher(Objects.requireNonNull(UserConfig.class.getResource("/oodj/assignment/oopskylinecarrentalsystem/textfiles/User.txt")).getPath());
        String pathReplacement = "src/main/resources";

        String incompleteUserFilePath = userFilePathMatcher.replaceFirst(pathReplacement);

        userFilePath = URLDecoder.decode((incompleteUserFilePath.substring(1)), StandardCharsets.UTF_8);

        try {
            List<String> userDatabase = new ArrayList<>(FileUtils.readLines(new File(userFilePath), Charset.defaultCharset()));

            for(String user: userDatabase) {
                String[] userData = user.split(" \\|\\| ");
                switch (userData[0]) {
                    case "a" -> {
                        userList.add(new Admin(userData));
                    }
                    case "c" -> {
                        userList.add(new Customer(userData));
                    }
                    default -> {
                        System.out.printf("%nError parsing usertype (%s)! Please check your \"User.txt\".", userData[0]);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<User> getUserList() {
        return userList;
    }

    public static String getUserFilePath() {
        return userFilePath;
    }

    public static User login(String usernameInput, String passwordInput) {
        for (User user: userList) {
            if (usernameInput.trim().equalsIgnoreCase(user.getUsername()) && passwordInput.equals(user.getPassword())) {
                return user;
            }
        }

        return null;
    }

    public static void updateUserList() {
        for(Customer customer: CustomerConfig.getCustomerList()) {
            if (!userList.contains(customer)) {
                userList.add(customer);
            }
        }
    }

    public static void updateFile() {
        updateUserList();
        List<String> userDatabase = new ArrayList<>();

        for(User user: userList) {
            userDatabase.add(user.fileFormat());
        }

        try {
            FileUtils.writeLines(new File(userFilePath), userDatabase);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
