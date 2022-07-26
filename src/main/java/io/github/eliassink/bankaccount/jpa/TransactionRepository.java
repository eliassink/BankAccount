package io.github.eliassink.bankaccount.jpa;

import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface TransactionRepository
        extends Repository<TransactionEntity, Long> {

    List<TransactionEntity> findBySender(String sender);

    List<TransactionEntity> findByReceiver(String receiver);

    void save(TransactionEntity transaction);
}