package com.Brilloconnetz.service;

import com.Brilloconnetz.util.InputReader;
import com.Brilloconnetz.model.User;
import com.Brilloconnetz.jwt.JWTUtil;

import java.util.List;
import java.util.Objects;

public class Application {
    private int wrongOptionCounter = 0;

    private static  Application instance;

    private Application() {

    }

    public static Application getInstance() {
        if (instance == null) {
            instance = new Application();
        }
        return instance;
    }

    public void run() {
        System.out.println("Welcome to Brilloconnetz\n");

        while (true) {
            String option = getUserOption();

            if (Objects.equals(option, "1")) {
                register();
                continue;
            }

            if (Objects.equals(option, "2")) {
                validateUser();
                continue;
            }

            if (Objects.equals(option, "3")) {
                exit();
                continue;
            }

            manageWrongOption();
        }
    }


    private String getUserOption() {
        System.out.println("What would you like to do?");
        System.out.println("Please choose an option:\n1. Register\n2. Login\n3. Exit");
        String option = InputReader.getInstance().nextLine();
        System.out.println();
        return option;

    }

    private void register() {
        User user = getUser();
        validateUser(user);
        String jwt = new JWTUtil().generateJWT(user);
        System.out.println("Here is your authorization token: " + jwt);
    }

    private void validateUser() {
        System.out.println("Please enter a valid jwt token");
        String jwt = InputReader.getInstance().nextLine();
        String message = new JWTUtil().verifyJWT(jwt);
        System.out.println(message + "\n");
    }

    private void exit() {
        System.out.println("Goodbye!");
        System.exit(0);
    }

    private void validateUser(User user) {
        List<String> errors = user.validate();
        if (errors.size() > 0) {
            System.out.println(String.join(", ", errors));
            System.exit(0);
        }
    }

    private void manageWrongOption() {
        if (wrongOptionCounter == 5) {
            System.out.println("Exceeded chance. exiting application");
            exit();
            return;
        }
        System.out.println("Invalid option");
        wrongOptionCounter++;
    }

    private User getUser() {
        System.out.println("Hello, welcome to Brilloconnetz");

        System.out.println("put your username?");
        String username = InputReader.getInstance().nextLine();

        System.out.println("put your email?");
        String email = InputReader.getInstance().nextLine();

        System.out.println("put your password?");
        String password = InputReader.getInstance().nextLine();

        System.out.println("put your dob?");
        String dob = InputReader.getInstance().nextLine();

        return new User(username, password, email, dob);
    }
}
