package org.tdd.katas.pwdvalidator;

import java.util.function.Predicate;

class Criterion {

    private final Predicate<Character> characterType;
    private final Predicate<Integer> occurrences;
    private final String error;

    public Criterion(Predicate<Character> characterType, Predicate<Integer> occurrences, String error) {
        this.characterType = characterType;
        this.occurrences = occurrences;
        this.error = error;
    }

    public String match(String password) {
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (characterType.test(c)) {
                count++;
            }
        }
        return occurrences.test(count) ? "" : error;
    }

}
