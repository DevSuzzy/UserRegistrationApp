package com.Brilloconnetz.validators;

import java.util.Optional;

public class UserNameValidator implements InputValidator {

    @Override
    public Optional<String> validate(String input, String fieldName) {
        if (input == null || input.isEmpty()) {
            return Optional.empty();
        }

        if (input.length() < 4) {
            return Optional.of(fieldName + ": must be length of 4");
        }

        return Optional.empty();
    }
}
