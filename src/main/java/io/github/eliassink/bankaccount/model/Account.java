package io.github.eliassink.bankaccount.model;

import java.util.LinkedList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Account {
    private Cents balance = new Cents();
    private final LinkedList<Transaction> transactions = new LinkedList<>();

    public Cents balance() {
        return balance;
    }

    public Account deposit(Cents amount) {
        if (amount.compareTo(new Cents(0)) < 0)
            throw new IllegalArgumentException("negative deposit amount");
        balance = balance.add(amount);
        transactions.addFirst(new Transaction(amount,new Date()));
        return this;
    }

    public Account withdraw(Cents amount) {
        if (amount.compareTo(new Cents(0)) < 0)
            throw new IllegalArgumentException("negative withdrawal amount");
        balance = balance.subtract(amount);
        transactions.addFirst(new Transaction(amount.negate(),new Date()));
        return this;
    }

    public List<Transaction> transactions() {
        return Collections.unmodifiableList(transactions);
    }
}