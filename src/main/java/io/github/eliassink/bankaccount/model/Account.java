package io.github.eliassink.bankaccount.model;

import java.util.*;

public final class Account {
    private Cents balance;
    private final LinkedList<Transaction> transactions;

    public Account() {
        balance = new Cents();
        this.transactions = new LinkedList<>();
    }

    public Account(Collection<Transaction> transactions) {
        this.transactions = new LinkedList<>(transactions);
        this.transactions.sort((tx1,tx2) -> tx2.date().compareTo(tx1.date()));
        balance = this.transactions.stream().map(Transaction::amount).reduce(new Cents(), Cents::add);
    }

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