package io.github.eliassink.bankaccount.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {

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
    void testTransactionAmounts() {
        Account account = new Account();
        account.deposit(new Cents(200))
                .withdraw(new Cents(150))
                .deposit(new Cents(50));

        assertEquals(new Cents(50),account.transactions().get(0).amount());
        assertEquals(new Cents(-150),account.transactions().get(1).amount());
        assertEquals(new Cents(200),account.transactions().get(2).amount());
    }

}
