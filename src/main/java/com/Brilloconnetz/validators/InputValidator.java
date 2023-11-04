package com.Brilloconnetz.validators;

import java.util.Optional;

public interface InputValidator {
    Optional<String> validate(String input, String fieldName);
}
