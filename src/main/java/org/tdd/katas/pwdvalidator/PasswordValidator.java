package org.tdd.katas.pwdvalidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PasswordValidator {

    List<Criterion> criteria = new ArrayList<>(Arrays.asList(
            new Criterion(
                    c -> true,
                    n -> n >= 8,
                    "Password must be at least 8 characters"
            ),
            new Criterion(
                    c -> Character.isDigit(c),
                    n -> n >= 2,
                    "The password must contain at least 2 numbers"
            ),
            new Criterion(
                    c -> Character.isLetter(c) && Character.isUpperCase(c),
                    n -> n >= 1,
                    "The password must contain at least one capital letter"
            ),
            new Criterion(
                    c -> !Character.isLetterOrDigit(c),
                    n -> n >= 1,
                    "The password must contain at least one special character"
            )
    ));

    public PasswordValidatorResponse validate(String password) {
        List<String> errors = new ArrayList<>();

        for (Criterion cr : criteria) {
            String error = cr.match(password);
            if (!error.isEmpty()) {
                errors.add(error);
            }
        }

        return new PasswordValidatorResponse(String.join("\n", errors));
    }

}
