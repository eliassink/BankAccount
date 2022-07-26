package io.github.eliassink.bankaccount.jpa;

import io.github.eliassink.bankaccount.model.Bank;
import io.github.eliassink.bankaccount.model.Transaction;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

@Component
public class JPABank implements Bank {
    private final TransactionRepository repo;

    public JPABank(TransactionRepository repo) {
        this.repo = repo;
    }

    @Override
    public BigDecimal balance(String account) {
        BigDecimal totalReceived = repo.findByReceiver(account).stream()
                .map(TransactionEntity::toTransaction)
                .map(Transaction::amount)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        BigDecimal totalSent = repo.findBySender(account).stream()
                .map(TransactionEntity::toTransaction)
                .map(Transaction::amount)
                .reduce(BigDecimal.ZERO,BigDecimal::add);
        return totalReceived.subtract(totalSent);
    }

    @Override
    public void post(Transaction transaction) {
        repo.save(new TransactionEntity(transaction));
    }

    @Override
    public List<Transaction> statement(String account) {
        return Stream.concat(repo.findByReceiver(account).stream(),repo.findBySender(account).stream())
                .map(TransactionEntity::toTransaction)
                .sorted().toList();
    }

    @Override
    public List<Transaction> statement(String account, LocalDate begin, LocalDate end) {
        return statement(account).stream()
                .filter(tx -> tx.date().compareTo(begin) >= 0 && tx.date().compareTo(end) <= 0)
                .toList();
    }
}
