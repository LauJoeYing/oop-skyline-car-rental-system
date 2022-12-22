package oodj.assignment.oopskylinecarrentalsystem.util;

import oodj.assignment.oopskylinecarrentalsystem.model.Admin;
import oodj.assignment.oopskylinecarrentalsystem.model.User;

import java.util.ArrayList;
import java.util.List;

public class AdminUtils {
    private static final List<Admin> adminList;

    static {
        adminList = new ArrayList<>();

        for(User user: UserUtils.getUserList()) {
            if (user instanceof Admin) {
                adminList.add((Admin) user);
            }
        }
    }

    public static List<Admin> getAdminList() {
        return adminList;
    }
}
