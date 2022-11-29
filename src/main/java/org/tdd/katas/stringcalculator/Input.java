package org.tdd.katas.stringcalculator;

public class Input {

    private final String separators;

    private final String expression;

    public Input(String expression, String separators) {
        this.separators = separators;
        this.expression = expression;
    }

    public Input(String expression) {
        this.separators = "[,\n]";
        this.expression = expression;
    }

    public String getSeparators() {
        return separators;
    }

    public String getExpression() {
        return expression;
    }

}
