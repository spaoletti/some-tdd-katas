package org.tdd.katas.pwdvalidator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PasswordValidatorTest {

    private PasswordValidator pv;

    @BeforeEach
    public void beforeEach() {
        pv = new PasswordValidator();
    }

    @Test
    public void should_respond_true_if_pwd_is_at_least_8_chars_long() {
        PasswordValidatorResponse response = pv.validate("A#345678");
        Assertions.assertTrue(response.isValid());
        Assertions.assertEquals("", response.getErrors());
    }

    @Test
    public void should_respond_false_if_pwd_is_less_than_8_chars_long() {
        PasswordValidatorResponse response = pv.validate("A#34567");
        Assertions.assertFalse(response.isValid());
        Assertions.assertEquals("Password must be at least 8 characters", response.getErrors());
    }

    @Test
    public void should_respond_true_if_pwd_has_at_least_2_numbers() {
        PasswordValidatorResponse response = pv.validate("Ab#12345678");
        Assertions.assertTrue(response.isValid());
        Assertions.assertEquals("", response.getErrors());
    }

    @Test
    public void should_respond_false_if_pwd_has_less_than_2_numbers() {
        PasswordValidatorResponse response = pv.validate("Ab#1defgh");
        Assertions.assertFalse(response.isValid());
        Assertions.assertEquals("The password must contain at least 2 numbers", response.getErrors());
    }

    @Test
    public void should_respond_with_both_errors_if_pwd_has_less_than_2_numbers_and_is_less_than_8_chars_long() {
        PasswordValidatorResponse response = pv.validate("Abc#");
        Assertions.assertFalse(response.isValid());
        Assertions.assertEquals(
                "Password must be at least 8 characters\n" +
                        "The password must contain at least 2 numbers",
                response.getErrors()
        );
    }

    @Test
    public void should_respond_false_if_pwd_does_not_contain_at_least_one_capital_letter() {
        PasswordValidatorResponse response = pv.validate("ab#12defgh");
        Assertions.assertFalse(response.isValid());
        Assertions.assertEquals("The password must contain at least one capital letter", response.getErrors());
    }

    @Test
    public void should_respond_with_all_errors_if_pwd_has_less_than_2_numbers_and_is_less_than_8_chars_long_and_does_not_contain_at_least_one_capital_letter() {
        PasswordValidatorResponse response = pv.validate("abc#");
        Assertions.assertFalse(response.isValid());
        Assertions.assertEquals(
                "Password must be at least 8 characters\n" +
                        "The password must contain at least 2 numbers\n" +
                        "The password must contain at least one capital letter",
                response.getErrors()
        );
    }

    @Test
    public void should_respond_false_if_pwd_does_not_contain_at_least_one_special_character() {
        PasswordValidatorResponse response = pv.validate("A2345678");
        Assertions.assertFalse(response.isValid());
        Assertions.assertEquals("The password must contain at least one special character", response.getErrors());
    }

    @Test
    public void should_respond_with_all_errors_if_pwd_has_less_than_2_numbers_and_is_less_than_8_chars_long_and_does_not_contain_at_least_one_capital_letter_and_does_not_contain_at_least_one_special_character() {
        PasswordValidatorResponse response = pv.validate("abc");
        Assertions.assertFalse(response.isValid());
        Assertions.assertEquals(
                "Password must be at least 8 characters\n" +
                        "The password must contain at least 2 numbers\n" +
                        "The password must contain at least one capital letter\n" +
                        "The password must contain at least one special character",
                response.getErrors()
        );
    }


}
