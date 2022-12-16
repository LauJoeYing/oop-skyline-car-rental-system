package oodj.assignment.oopskylinecarrentalsystem.config;

import oodj.assignment.oopskylinecarrentalsystem.model.Admin;
import oodj.assignment.oopskylinecarrentalsystem.model.Customer;
import oodj.assignment.oopskylinecarrentalsystem.model.User;

import java.util.ArrayList;
import java.util.List;

public class AdminConfig {
    private static final List<Admin> adminList;

    static {
        adminList = new ArrayList<>();

        for(User user: UserConfig.getUserList()) {
            if (user instanceof Admin) {
                adminList.add((Admin) user);
            }
        }
    }

    public static List<Admin> getAdminList() {
        return adminList;
    }
}
