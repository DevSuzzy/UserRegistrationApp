package com.Brilloconnetz.validators;

import java.util.Optional;
import java.util.regex.Pattern;

public class PasswordValidator implements InputValidator {

    @Override
    public Optional<String> validate(String input, String fieldName) {
        if (input == null || input.isEmpty()) {
            return Optional.of(fieldName + ": is required");
        }

        // Check for minimum length
        if (input.length() < 8) {
            return Optional.of(fieldName + ": Strong Password with  at least 8 characters long");
        }

        // Check for at least 1 upper case letter
        if (!Pattern.compile("[A-Z]").matcher(input).find()) {
            return Optional.of(fieldName + ": must contain at least 1 uppercase letter");
        }

        // Check for at least 1 special character
        if (!Pattern.compile("[!@#$%^&*]").matcher(input).find()) {
            return Optional.of(fieldName + ": must contain at least 1 special character (!@#$%^&*)");
        }

        // Check for at least 1 number
        if (!Pattern.compile("[0-9]").matcher(input).find()) {
            return Optional.of(fieldName + ": must contain at least 1 number");
        }

        return Optional.empty();
    }
}
