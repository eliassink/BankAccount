package io.github.eliassink.bankaccount.model;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

    @Test
    void testConstructor() {
        Date now = new Date();
        Cents c1 = new Cents(25);
        Cents c2 = new Cents(100);
        Cents c3 = new Cents(-50);
        List<Transaction> transactions = List.of( //out of order
                new Transaction(c2, now),
                new Transaction(c3, new Date(now.getTime() + 10)),
                new Transaction(c1, new Date(now.getTime() - 10))
        );
        Account account = new Account(transactions);
        assertEquals(c1.add(c2).add(c3), account.balance());
        assertEquals(c1,account.transactions().get(0).amount());
        assertEquals(c3,account.transactions().get(2).amount());
    }

    @Test
    void testDepositAddsToBalance() {
        Account account = new Account();

        account.deposit(new Cents(500));
        assertEquals(new Cents(500),account.balance());

        account.deposit(new Cents(200));
        assertEquals(new Cents(700),account.balance());
    }

    @Test
    void testDepositNegativeAmountThrows() {
        Account account = new Account();
        assertThrows(IllegalArgumentException.class, () -> account.deposit(new Cents(-10)));
    }

    @Test
    void testWithdrawSubtractsFromBalance() {
        Account account = new Account();
        account.deposit(new Cents(1000))
                .withdraw(new Cents(200));
        assertEquals(new Cents(800),account.balance());
    }

    @Test
    void testWithdrawNegativeAmountThrows() {
        Account account = new Account();
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(new Cents(-10)));
    }

    @Test
    void testDepositAndWithdrawModifyTransactions() {
        Account account = new Account();
        account.deposit(new Cents(200))
                .withdraw(new Cents(150))
                .deposit(new Cents(50));

        assertEquals(new Cents(50),account.transactions().get(0).amount());
        assertEquals(new Cents(-150),account.transactions().get(1).amount());
        assertEquals(new Cents(200),account.transactions().get(2).amount());
    }

    @Test
    void testDirectModificationOfTransactionsThrows() {
        Transaction transaction = new Transaction(new Cents(100),new Date());
        assertThrows(Exception.class, ()-> new Account().transactions().add(transaction));
    }
}
