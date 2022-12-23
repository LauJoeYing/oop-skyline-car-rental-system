package oodj.assignment.oopskylinecarrentalsystem.util;

import oodj.assignment.oopskylinecarrentalsystem.model.Customer;
import oodj.assignment.oopskylinecarrentalsystem.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang.WordUtils.capitalize;

public class CustomerUtils {                //Utilities for Customer User
    private static final List<Customer> customerList;

    static {
        customerList = new ArrayList<>();

        for(User user: UserUtils.getUserList()) {
            if (user instanceof Customer) {
                customerList.add((Customer) user);
            }
        }
    }

    public static boolean isValidUsername(String username) {
        if (isRegexMatchCaseInsensitive(username, "^[a-z][\\w\\-]{7,14}$")) {
            for (Customer customer: customerList) {
                if (username.equals(customer.getUsername())) {
                    return false;
                }
            }
            return true;
        };
        return false;
    }

    public static boolean isValidPassword(String password) {
        return isRegexMatch(password, "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[\\W_]).{8,32}$");
    }

    public static boolean isValidName(String name) {
        return isRegexMatch(name, "^[A-Z][a-z]*+\\s[A-Z][a-z]*(?:\\s[A-Z][a-z]*)*$");
    }

    public static boolean isValidEmailToModify(Customer targetCustomer, String email){
        if (isValidEmail(email)) {
            for (Customer customer: customerList) {
                if(customer.getEmailAddress().equals(email) && !targetCustomer.getEmailAddress().equals(email)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isValidEmailToCreate(String email){
        if (isValidEmail(email)) {
            for (Customer customer: customerList) {
                if(customer.getEmailAddress().equals(email)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static boolean isValidEmail(String email) {
        return (isRegexMatchCaseInsensitive(email, "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"));
    }

    public static boolean isValidPhoneNumberToModify(Customer targetCustomer,String phoneNumber){
        if (isValidPhoneNumber(phoneNumber)) {
            for (Customer customer: customerList) {
                if(customer.getPhoneNumber().equals(phoneNumber) && !targetCustomer.getPhoneNumber().equals(phoneNumber)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isValidPhoneNumberToCreate(String phoneNumber){
        if (isValidPhoneNumber(phoneNumber)) {
            for (Customer customer: customerList) {
                if(customer.getPhoneNumber().equals(phoneNumber)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        return isRegexMatch(phoneNumber, "^601(?:[02-46-9]\\d{7}|1\\d{8})$");
    }

    public static boolean isValidIcNumber(String icNumber){
        if (isRegexMatchCaseInsensitive(icNumber, "^\\d{6}-\\d{2}-\\d{4}$")) {
            for (Customer customer: customerList) {
                if(customer.getIcNumber().equals(icNumber)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isValidPostcode(String postcode){
        return isRegexMatchCaseInsensitive(postcode, "^\\d{5}$");
    }

    public static boolean isRegexMatch(String property, String regex) {
        Pattern validPropertyPattern = Pattern.compile(regex);
        Matcher validPropertyMatcher = validPropertyPattern.matcher(property);

        return validPropertyMatcher.matches();
    }

    public static boolean isRegexMatchCaseInsensitive(String property, String regex) {
        Pattern validPropertyPattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher validPropertyMatcher = validPropertyPattern.matcher(property);

        return validPropertyMatcher.matches();
    }

    public static String toStandardName(String name) {
        return capitalize(name);
    }

    public static String toStandardEmail(String email) {
        return email.toLowerCase();
    }

    public static String toStandardPhoneNumber(String phoneNumber) {
        return phoneNumber
                .replaceAll("^(?:\\+6|6)?01([02-46-9]-?\\d{7}|1-?\\d{8})$", "601$1")
                .replaceAll("-", "");
    }

    public static String toStandardIcNumber(String icNumber) {
        return icNumber.replaceAll("^(\\d{6})-?(\\d{2})-?(\\d{4})$", "$1-$2-$3");
    }

    public static void register(Customer customer) {
        customerList.add(customer);
    }

    public static void removeCustomer(String username) {
        customerList.removeIf(customer ->
                customer.getUsername().equals(username)
        );
    }

    public static void updateCustomer(Customer customer) {
        customerList.replaceAll(oldCustomer ->
                oldCustomer.getUsername().equals(customer.getUsername()) ? customer : oldCustomer
        );
    }

    public static List<Customer> getCustomerList() {
        return customerList;
    }

    public static Customer getCustomerFromUsername(String username) {
        for (Customer customer: customerList) {
            if (customer.getUsername().equals(username)) {
                return customer;
            }
        }
        return null;
    }
}
