package io.github.eliassink.bankaccount.jpa;

import io.github.eliassink.bankaccount.model.Transaction;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "transaction")
public class TransactionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String sender;
    private String receiver;

    @Column(precision = 19,scale = 4)
    private BigDecimal amount;

    private LocalDate date;

    protected TransactionEntity() {}

    public TransactionEntity(String sender, String receiver, BigDecimal amount, LocalDate date) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.date = date;
    }

    public TransactionEntity(Transaction transaction) {
        this(transaction.from(),transaction.to(),transaction.amount(),transaction.date());
    }

    public Transaction toTransaction() {
        return new Transaction(sender,receiver,amount,date);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionEntity that = (TransactionEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(sender, that.sender) && Objects.equals(receiver, that.receiver) && Objects.equals(amount, that.amount) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sender, receiver, amount, date);
    }

    @Override
    public String toString() {
        return "TransactionEntity{" +
                "id=" + id +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }
}
