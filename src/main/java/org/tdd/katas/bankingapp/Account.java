package org.tdd.katas.bankingapp;

import java.time.Clock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private final String DEPOSIT = "deposit";
    private final String WITHDRAW = "withdraw";
    public final Clock clock;
    private List<Operation> operations = new ArrayList<>();
    private int balance = 0;

    public Account(Clock clock) {
        this.clock = clock;
    }

    public Account() {
        this.clock = Clock.systemDefaultZone();
    }

    public void deposit(int amount) {
        validate(amount, DEPOSIT);
        balance += amount;
        operations.add(new Operation(amount, LocalDate.now(clock), balance));
    }

    public void withdraw(int amount) {
        validate(amount, WITHDRAW);
        balance -= amount;
        operations.add(new Operation(-amount, LocalDate.now(clock), balance));
    }

    public void printStatement() {
        System.out.println("DATE | AMOUNT | BALANCE");
        for (Operation operation : operations) {
            System.out.println(operation);
        }
    }

    private void validate(int amount, String operation) {
        if (amount == 0) {
            throw new IllegalArgumentException("Can't " + operation + " nothing.");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Can't " + operation + " a negative amount.");
        }
        if (WITHDRAW.equals(operation) && balance <= 0) {
            throw new IllegalArgumentException("No money bud!");
        }
    }

}
