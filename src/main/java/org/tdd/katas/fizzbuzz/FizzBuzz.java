package org.tdd.katas.fizzbuzz;

public class FizzBuzz {
    public String convert(int i) {
        String fizzbuzz = "";
        if (i % 3 == 0) {
            fizzbuzz += "fizz";
        }
        if (i % 5 == 0) {
            fizzbuzz += "buzz";
        }
        return fizzbuzz.isEmpty() ? String.valueOf(i) : fizzbuzz;
    }
}
