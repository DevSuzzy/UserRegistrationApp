package com.Brilloconnetz.validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class DateOfBirthValidator implements InputValidator {

    @Override
    public Optional<String> validate(String input, String fieldName) {
        if (input == null || input.isEmpty()) {
            return Optional.empty();
        }

        // Check if the input is a valid date
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate dob = LocalDate.parse(input, dateFormat);

            // Calculate the age based on the birthdate
            LocalDate currentDate = LocalDate.now();
            int age = currentDate.getYear() - dob.getYear();

            if (age < 16) {
                return Optional.of(fieldName + ": should be 16 years or older");
            }
        } catch (Exception e) {
            return Optional.of(fieldName + ": is not a valid date (yyyy-MM-dd)");
        }

        return Optional.empty();
    }
}
