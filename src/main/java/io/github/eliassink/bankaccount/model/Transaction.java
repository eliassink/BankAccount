package io.github.eliassink.bankaccount.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Transaction(String from, String to, BigDecimal amount, LocalDate date)
        implements Comparable<Transaction> {
    public Transaction(String from, String to, BigDecimal amount) {
        this(from,to,amount,LocalDate.now());
    }


    @Override
    public int compareTo(Transaction o) {
        return date.compareTo(o.date);
    }
}
