package org.tdd.katas.stringcalculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StringCalculatorTest {

    private StringCalculator sc;

    @BeforeEach
    public void beforeEach() {
        sc = new StringCalculator();
    }
    @Test
    public void should_return_0_when_the_input_is_empty() {
        int result = sc.add("");
        Assertions.assertEquals(0, result);
    }

    @Test
    public void when_the_input_is_a_single_digit_it_should_return_it() {
        int result = sc.add("7");
        Assertions.assertEquals(7, result);
    }

    @Test
    public void when_the_input_is_two_numbers_it_returns_the_sum() {
        int result = sc.add("7,9");
        Assertions.assertEquals(16, result);
    }

    @Test
    public void when_the_input_is_any_amount_of_numbers_it_returns_the_sum() {
        int result = sc.add("7,9,3,1");
        Assertions.assertEquals(20, result);
    }

    @Test
    public void should_accept_both_comma_and_newline_as_separators() {
        int result = sc.add("7\n9,3\n1");
        Assertions.assertEquals(20, result);
    }

    @Test
    public void should_throw_an_exception_if_there_is_a_separator_at_the_end() {
        IllegalArgumentException e = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> sc.add("7,9,3\n1,")
        );
        Assertions.assertEquals("Separator at the end", e.getMessage());
    }

    @Test
    public void should_accept_a_cumstom_separator() {
        int result = sc.add("$\n7$9$3$1");
        Assertions.assertEquals(20, result);
    }

    @Test
    public void should_throw_an_exception_if_there_is_a_custom_separator_at_the_end() {
        IllegalArgumentException e = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> sc.add("$\n7$9$3$1$")
        );
        Assertions.assertEquals("Separator at the end", e.getMessage());
    }

    @Test
    public void should_throw_an_exception_if_a_custom_separator_is_defined_but_a_different_one_is_used() {
        IllegalArgumentException e = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> sc.add("$\n7$9$3,1")
        );
        Assertions.assertEquals("'$' expected but ',' found at position 5.", e.getMessage());
    }

    @Test
    public void should_throw_an_exception_if_input_is_null() {
        IllegalArgumentException e = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> sc.add(null)
        );
        Assertions.assertEquals("input is null", e.getMessage());
    }

    @Test
    public void should_throw_an_exception_if_there_is_a_negative_numbers() {
        IllegalArgumentException e = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> sc.add("2,-4")
        );
        Assertions.assertEquals("Negative number(s) not allowed: -4 ", e.getMessage());
    }

    @Test
    public void should_throw_an_exception_if_there_are_multiple_negative_numbers() {
        IllegalArgumentException e = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> sc.add("2,-4,3,-5")
        );
        Assertions.assertEquals("Negative number(s) not allowed: -4 -5 ", e.getMessage());
    }

    @Test
    public void should_return_all_error_messages_separated_by_new_line_in_case_of_separator_at_the_end_and_negative_numbers() {
        IllegalArgumentException e = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> sc.add("2,-4,3,-5,")
        );
        Assertions.assertEquals(
                "Separator at the end\n" +
                        "Negative number(s) not allowed: -4 -5 ",
                e.getMessage()
        );
    }

    @Test
    public void should_return_all_error_messages_separated_by_new_line_in_case_of_negative_numbers_and_wrong_custom_separator() {
        IllegalArgumentException e = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> sc.add("|\n1|2,-3")
        );
        Assertions.assertEquals(
                "'|' expected but ',' found at position 3.\n" +
                        "Negative number(s) not allowed: -3 ",
                e.getMessage()
        );
    }

    @Test
    public void should_return_all_error_messages_in_case_of_negative_numbers_and_wrong_custom_separator_and_separator_at_the_end() {
        IllegalArgumentException e = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> sc.add("|\n1|2,-3,-4,5,-71|")
        );
        Assertions.assertEquals(
                "Separator at the end\n" +
                        "'|' expected but ',' found at position 3.\n" +
                        "Negative number(s) not allowed: -3 -4 -71 ",
                e.getMessage()
        );
    }

    @Test
    public void should_ignore_numbers_bigger_than_1000() {
        int result = sc.add("1,2,3,1500,1000,4");
        Assertions.assertEquals(1010, result);
    }

}
