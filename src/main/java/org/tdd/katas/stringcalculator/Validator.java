package org.tdd.katas.stringcalculator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Validator {

    private final Input input;

    public Validator(Input input) {
        this.input = input;
    }

    public void throwIfNotValid() {
        throwIfErrors(
                checkSeparatorAtTheEnd(input.getExpression(), input.getSeparators()),
                checkIllegalSeparators(input.getExpression(), input.getSeparators()),
                checkNegativeNumbers(input.getExpression())
        );
    }

    private void throwIfErrors(String... errors) {
        List<String> errorList = Arrays.stream(errors).filter(s -> !s.isEmpty()).collect(Collectors.toList());
        if (errorList.size() > 0) {
            throw new IllegalArgumentException(String.join("\n", errorList));
        }
    }

    private String checkSeparatorAtTheEnd(String input, String separators) {
        if (!input.isEmpty() && input.substring(input.length() - 1).matches(separators)) {
            return "Separator at the end";
        }
        return "";
    }

    private String checkIllegalSeparators(String input, String separators) {
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (!Character.isDigit(c) && c != '-' && !String.valueOf(c).matches(separators)) {
                return "'" + separators.replaceAll("[\\[\\]]", "") + "' expected but " +
                        "'" + c + "' found at position " + i + ".";
            }
        }
        return "";
    }

    private String checkNegativeNumbers(String input) {
        StringBuilder negativeNumbers = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '-') {
                int firstNonDigitChar = findFirstNonDigitCharacter(input, i + 1);
                negativeNumbers.append(input, i, firstNonDigitChar).append(" ");
                i = firstNonDigitChar;
            }
        }
        if (!negativeNumbers.isEmpty()) {
            return "Negative number(s) not allowed: " + negativeNumbers;
        }
        return "";
    }

    private int findFirstNonDigitCharacter(String str, int startingAt) {
        int next = startingAt;
        while (next < str.length() && Character.isDigit(str.charAt(next))) {
            next++;
        }
        return next;
    }

}