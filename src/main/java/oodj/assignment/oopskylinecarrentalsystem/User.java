package oodj.assignment.oopskylinecarrentalsystem;

import java.util.Scanner;

public class User {
    public void login() {

        while (true) {

//            Scanner scan = new Scanner(new File("the\\dir\\myFile.extension"));
//            Scanner input = new Scanner(System.in);
//            String userIdData = scan.nextline();
//            String passwordData = scan.nextline()

            String usernameData = "CUS00001";
            String passwordData = "12345A";

            Scanner input = new Scanner(System.in);
            System.out.println("Please Enter Your User ID : ");
            String username = input.next().toLowerCase();


            System.out.println("Please Enter Your Password : ");
            String password = input.next();

            if (username.equals(usernameData.toLowerCase()) && password.equals(passwordData)) {
                System.out.println("Welcome to Skyline Car Rental System!");
                break;
            } else if (username.equals(usernameData.toLowerCase())) {
                System.out.println("Invalid Password!");
            } else if (password.equals(passwordData)) {
                System.out.println("Invalid User ID!");
            } else {
                System.out.println("Invalid User ID and Password!");

            }

        }

    }
}

