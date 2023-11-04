package com.Brilloconnetz.validators;

import java.util.Optional;

public class NotEmptyValidator implements InputValidator {

    @Override
    public Optional<String> validate(String input, String fieldName) {
        if (input == null || input.trim().length() == 0) {
             return Optional.of(fieldName + " :not empty");
        }
        return Optional.empty();
    }
}
