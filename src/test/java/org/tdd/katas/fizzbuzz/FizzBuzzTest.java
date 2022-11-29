package org.tdd.katas.fizzbuzz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class FizzBuzzTest {

    private FizzBuzz fb;

    @BeforeEach
    public void beforeEach() {
        fb = new FizzBuzz();
    }
    @Test
    public void should_accept_a_number_as_input_and_return_it_as_string() {
        String result = fb.convert(11);
        Assertions.assertEquals("11", result);
    }

    @Test
    public void should_return_fizz_if_the_input_is_multiple_of_three() {
        String result = fb.convert(9);
        Assertions.assertEquals("fizz", result);
    }

    @Test
    public void should_return_buzz_if_the_input_is_multiple_of_five() {
        String result = fb.convert(20);
        Assertions.assertEquals("buzz", result);
    }

    @Test
    public void should_return_fizzbuzz_if_the_input_is_multiple_of_both_five_and_three() {
        String result = fb.convert(15);
        Assertions.assertEquals("fizzbuzz", result);
    }

}