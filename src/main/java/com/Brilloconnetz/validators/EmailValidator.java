package com.Brilloconnetz.validators;

import java.util.Optional;
import java.util.regex.Pattern;

public class EmailValidator implements InputValidator {

    @Override
    public Optional<String> validate(String input, String fieldName) {
        if (input == null || input.isEmpty()) {
            return Optional.empty();
        }

        // Regular expression for a typical email format
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";

        if (!Pattern.compile(emailRegex).matcher(input).matches()) {
            return Optional.of(fieldName + ": is not a valid email address");
        }

        return Optional.empty();
    }
}
