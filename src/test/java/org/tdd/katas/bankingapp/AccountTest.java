package org.tdd.katas.bankingapp;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    Account a;

    private ByteArrayOutputStream mockStdOut = new ByteArrayOutputStream();
    private static final PrintStream originalStdOut = System.out;
    private Clock mockClock = Mockito.mock(Clock.class);

    @AfterAll
    public static void afterAll() {
        System.setOut(originalStdOut);
    }

    @BeforeEach
    public void beforeEach() {
        setSystemClock(2014, 4, 1);
        resetStandardOutput();
        a = new Account(mockClock);
    }

    private void resetStandardOutput() {
        mockStdOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(mockStdOut));
    }

    private void setSystemClock(int year, int month, int day) {
        LocalDate newDate = LocalDate.of(year, month, day);
        Clock newClock = Clock.fixed(
                newDate.atStartOfDay(ZoneId.systemDefault()).toInstant(),
                ZoneId.systemDefault()
        );
        Mockito.doReturn(newClock.instant()).when(mockClock).instant();
        Mockito.doReturn(newClock.getZone()).when(mockClock).getZone();
    }

    @Test
    public void should_throw_if_deposit_is_zero() {
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> a.deposit(0)
        );
        assertEquals("Can't deposit nothing.", e.getMessage());
    }

    @Test
    public void should_throw_if_deposit_is_negative() {
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> a.deposit(-7)
        );
        assertEquals("Can't deposit a negative amount.", e.getMessage());
    }

    @Test
    public void should_throw_if_withdraw_is_zero() {
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> a.withdraw(0)
        );
        assertEquals("Can't withdraw nothing.", e.getMessage());
    }

    @Test
    public void should_throw_if_withdraw_is_negative() {
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> a.withdraw(-7)
        );
        assertEquals("Can't withdraw a negative amount.", e.getMessage());
    }

    @Test
    public void should_throw_if_withdraw_against_an_empty_account() {
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> a.withdraw(70)
        );
        assertEquals("No money bud!", e.getMessage());
    }

    @Test
    public void should_log_a_deposit() {
        a.deposit(787654);
        a.printStatement();
        assertTrue(mockStdOut.toString().contains("787654"));
    }

    @Test
    public void should_log_the_timestamp_of_a_deposit() {
        a.deposit(787654);
        a.printStatement();
        assertTrue(mockStdOut.toString().contains("01/04/2014"));
    }

    @Test
    public void should_log_two_deposits() {
        a.deposit(787654);
        a.deposit(123456);
        a.printStatement();
        assertTrue(mockStdOut.toString().contains("787654"));
        assertTrue(mockStdOut.toString().contains("123456"));
    }

    @Test
    public void should_log_two_deposits_in_different_dates() {
        a.deposit(787654);
        setSystemClock(2017, 5, 1);
        a.deposit(123456);
        a.printStatement();
        assertTrue(mockStdOut.toString().contains("01/04/2014"));
        assertTrue(mockStdOut.toString().contains("01/05/2017"));
    }

    @Test
    public void should_log_a_withdraw() {
        a.deposit(Integer.MAX_VALUE);
        a.withdraw(765);
        a.printStatement();
        assertTrue(mockStdOut.toString().contains("-765"));
    }

    @Test
    public void should_log_two_withdraws() {
        a.deposit(Integer.MAX_VALUE);
        a.withdraw(3876542);
        a.withdraw(3234562);
        a.printStatement();
        assertTrue(mockStdOut.toString().contains("-3876542"));
        assertTrue(mockStdOut.toString().contains("-3234562"));
    }

    @Test
    public void should_log_two_withdraws_in_different_dates() {
        a.deposit(Integer.MAX_VALUE);
        a.withdraw(678);
        setSystemClock(2017, 5, 1);
        a.withdraw(981);
        a.printStatement();
        assertTrue(mockStdOut.toString().contains("01/04/2014"));
        assertTrue(mockStdOut.toString().contains("01/05/2017"));
    }

    @Test
    public void should_log_a_deposit_and_a_withdraw() {
        a.deposit(3876542);
        a.withdraw(3234562);
        a.printStatement();
        assertTrue(mockStdOut.toString().contains("3876542"));
        assertTrue(mockStdOut.toString().contains("-3234562"));
    }

    @Test
    public void should_log_the_total_balance() {
        a.deposit(150);
        a.withdraw(51);
        a.printStatement();
        assertTrue(mockStdOut.toString().contains("99"));
        a.withdraw(51);
        a.printStatement();
        assertTrue(mockStdOut.toString().contains("48"));
    }

    @Test
    public void should_log_the_formatted_operations_in_chronological_order_with_header() {
        a.deposit(500);
        a.withdraw(600);
        setSystemClock(2017, 5, 1);
        a.deposit(201);
        a.printStatement();
        String expectedLog =
                "DATE | AMOUNT | BALANCE\n" +
                "01/04/2014 | 500 | 500\n" +
                "01/04/2014 | -600 | -100\n" +
                "01/05/2017 | 201 | 101\n";
        assertEquals(expectedLog, mockStdOut.toString());
    }

}