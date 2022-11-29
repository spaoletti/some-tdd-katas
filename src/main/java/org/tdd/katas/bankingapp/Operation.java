package org.tdd.katas.bankingapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Operation {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final int amount;
    private LocalDate date;
    private final int balance;

    public Operation(int amount, LocalDate date, int balance) {
        this.amount = amount;
        this.date = date;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return date.format(formatter) + " | " + amount + " | " + balance;
    }
}
