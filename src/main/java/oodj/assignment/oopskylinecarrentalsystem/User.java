package oodj.assignment.oopskylinecarrentalsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

public class User {
    public void login() {

        AtomicBoolean pass = new AtomicBoolean(false);


        while (!pass.get()) {
            try (Stream<String> stream = Files.lines(Paths.get(Objects.requireNonNull(getClass().getResource("user.txt")).toURI()))) {

                Scanner input = new Scanner(System.in);


                System.out.println("Please Enter Your User ID : ");
                String usernameInput = input.nextLine().trim();

                System.out.println("Please Enter Your Password : ");
                String passwordInput = input.nextLine();

                //lamda expression
                stream.parallel().forEach(user -> {
                    String[] userData = user.split(" \\| ");
                    String userId = userData[0];
                    String username = userData[1];
                    String password = userData[2];
                    String userName = userData[3];

                    if (usernameInput.equalsIgnoreCase(username) && passwordInput.equals(password)) {
                            System.out.printf("Welcome back to Skyline Car Rental System, %s !", userName);
                            pass.set(true);
                        }
                    });
                if (!pass.get()){
                    System.out.println("Invalid Input! Please Try Again!");
                    continue;
                }

            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
}

