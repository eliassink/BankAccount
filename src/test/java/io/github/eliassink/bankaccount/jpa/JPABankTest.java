package io.github.eliassink.bankaccount.jpa;

import io.github.eliassink.bankaccount.model.Bank;
import io.github.eliassink.bankaccount.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class JPABankTest {
    private final String account = "ACC001";

    private final List<TransactionEntity> received = List.of(
            new TransactionEntity(account,"ACC002",new BigDecimal(10),
                    LocalDate.of(2000,1,1)),
            new TransactionEntity(account,"ACC003",new BigDecimal(15),
                    LocalDate.of(2000,1,10))
    );

    private final List<TransactionEntity> sent = List.of(
            new TransactionEntity("ACC004",account,new BigDecimal(5),
                    LocalDate.of(2000,1,5)),
            new TransactionEntity("ACC005",account,new BigDecimal(7),
                    LocalDate.of(2000,2,1))
    );

    TransactionRepository repo = mock(TransactionRepository.class);

    Bank bank = new JPABank(repo);

    @BeforeEach
    void setUp() {
        when(repo.findByReceiver(account)).thenReturn(received);
        when(repo.findBySender(account)).thenReturn(sent);
    }

    @Test
    void testBalance() {
        assertEquals(new BigDecimal( 10 + 15 - 5 - 7),bank.balance(account));
    }

    @Test
    void testPostTransaction() {
        Transaction transaction = new Transaction(account,"ACC002",new BigDecimal(5));
        bank.post(transaction);
        verify(repo).save(new TransactionEntity(transaction));
    }

    @Test
    void testStatementReturnsAllTransactionsSorted() {
        List<Transaction> all = Stream.concat(
                sent.stream().map(TransactionEntity::toTransaction),
                received.stream().map(TransactionEntity::toTransaction)
                ).sorted().toList();
        assertEquals(all,bank.statement(account));
    }

    @Test
    void testStatementReturnsDateRange() {
        assertEquals(
                List.of(received.get(0).toTransaction(),sent.get(0).toTransaction()),
                bank.statement(
                        account,
                        LocalDate.of(2000,1,1),
                        LocalDate.of(2000,1,5)
                )
        );
    }
}
