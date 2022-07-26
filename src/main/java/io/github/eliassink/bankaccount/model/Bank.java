package io.github.eliassink.bankaccount.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface Bank {
    BigDecimal balance(String account);

    void post(Transaction transaction);

    List<Transaction> statement(String account);

    List<Transaction> statement(String account, LocalDate begin, LocalDate end);
}
