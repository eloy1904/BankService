package com.nttdata.bankservice.services;


import com.nttdata.bankservice.dto.TransactionDto;
import com.nttdata.bankservice.entity.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
public interface TransactionService {
    Mono<Transaction> saveTrans(TransactionDto transactionDto);

    Mono<Transaction> finById(String id);

    Flux<Transaction> findAll();

    Mono<Transaction> updateTrans(TransactionDto transactionDto);

    Mono<Transaction> deleteTrans(String id);
}
