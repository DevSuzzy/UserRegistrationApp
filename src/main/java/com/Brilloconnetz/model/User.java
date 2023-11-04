package com.Brilloconnetz.model;

import java.util.*;
import com.Brilloconnetz.validators.InputValidator;
import com.Brilloconnetz.validators.NotEmptyValidator;
import com.Brilloconnetz.validators.UserNameValidator;
import com.Brilloconnetz.validators.PasswordValidator;
import com.Brilloconnetz.validators.DateOfBirthValidator;
import com.Brilloconnetz.validators.EmailValidator;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class User {

    private String  username;

    private String password;

    private String email;

    private String dob;

    public User(String username, String password, String email, String dob) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.dob = dob;
    }

    public User() {
    }

    public List<String> validate() {
        List<String> errors = new ArrayList<>();
        errors.addAll(validateNotEmpty());
        errors.addAll(validateUsername());
        errors.addAll(validatePassword());
        errors.addAll(validateDateOfBirth());
        errors.addAll(validateEmail());
        return errors;

    }


    private List<String> validateNotEmpty() {
        List<String> errors = new ArrayList<>();

        InputValidator validator = new NotEmptyValidator();

        Optional<String> email = validator.validate(this.email, "Email");
        email.ifPresent(errors::add);

        Optional<String> password = validator.validate(this.password, "Password");
        password.ifPresent(errors::add);

        Optional<String> username = validator.validate(this.username, "UserName");
        username.ifPresent(errors::add);

        Optional<String> dob = validator.validate(this.dob, "DOB");
        dob.ifPresent(errors::add);

        return errors;
    }

    private List<String> validateUsername() {
        List<String> errors = new ArrayList<>();

        InputValidator validator = new UserNameValidator();

        Optional<String> username = validator.validate(this.username, "Username");
        username.ifPresent(errors::add);

        return errors;
    }


    private List<String> validatePassword() {
        List<String> errors = new ArrayList<>();

        InputValidator validator = new PasswordValidator();

        Optional<String> password = validator.validate(this.password, "Password");
        password.ifPresent(errors::add);

        return errors;
    }
    private List<String> validateDateOfBirth() {
        List<String> errors = new ArrayList<>();

        InputValidator validator = new DateOfBirthValidator();

        Optional<String> dob = validator.validate(this.dob, "Dob");
        dob.ifPresent(errors::add);

        return errors;
    }

    private List<String> validateEmail() {
        List<String> errors = new ArrayList<>();

        InputValidator validator = new EmailValidator();

        Optional<String> email = validator.validate(this.email, "Email");
        email.ifPresent(errors::add);

        return errors;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }


    public static String generateJWT(String username) {
        String secretKey = "yourSecretKey";
        long currentTimeMillis = System.currentTimeMillis();
        long expirationTimeMillis = currentTimeMillis + 15 * 60 * 1000; // 15 minutes

        String jwt = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(expirationTimeMillis))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        return jwt;
    }

    public static boolean verifyJWT(String jwt) {
        String secretKey = "yourSecretKey";
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
