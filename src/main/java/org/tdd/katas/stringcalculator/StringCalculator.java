package org.tdd.katas.stringcalculator;

public class StringCalculator {

    public int add(String input) {
        Parser parser = new Parser(input);
        return parser.getNumberList().stream()
                .mapToInt(i -> i)
                .filter(n -> n <= 1000)
                .sum();
    }

}
