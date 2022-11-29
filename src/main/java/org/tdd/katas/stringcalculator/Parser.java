package org.tdd.katas.stringcalculator;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Parser {

    private final Input parsedInput;

    public Parser(String input) {
        checkNullInput(input);
        if (hasCustomSeparator(input)) {
            parsedInput = new Input(
                    input.substring(2),
                    "[" + input.charAt(0) + "]"
            );
        } else {
            parsedInput = new Input(input);
        }
        Validator iv = new Validator(parsedInput);
        iv.throwIfNotValid();
    }

    private void checkNullInput(String i) {
        if (i == null) {
            throw new IllegalArgumentException("input is null");
        }
    }

    private boolean hasCustomSeparator(String input) {
        return (input.length() >= 2 && !Character.isDigit(input.charAt(0)) && input.charAt(1) == '\n');
    }

    public List<Integer> getNumberList() {
        String expression = parsedInput.getExpression();
        String separators = parsedInput.getSeparators();
        return Stream.of(expression.split(separators))
                .map(s -> s.isEmpty() ? "0" : s)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

}