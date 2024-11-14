package com.nttdata.bankservice.repository;

import com.nttdata.bankservice.entity.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String>{
    Flux<Transaction> findByProductId(String productId);
}
