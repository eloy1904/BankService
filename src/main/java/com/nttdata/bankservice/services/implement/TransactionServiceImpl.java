package com.nttdata.bankservice.services.implement;


import com.nttdata.bankservice.dto.TransactionDto;
import com.nttdata.bankservice.entity.Transaction;
import com.nttdata.bankservice.repository.TransactionRepository;
import com.nttdata.bankservice.services.TransactionService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{
    @Override
    public Mono<Transaction> saveTrans(TransactionDto transactionDto) {
        return null;
    }

    @Override
    public Mono<Transaction> finById(String id) {
        return null;
    }

    @Override
    public Flux<Transaction> findAll() {
        return null;
    }

    @Override
    public Mono<Transaction> updateTrans(TransactionDto transactionDto) {
        return null;
    }

    @Override
    public Mono<Transaction> deleteTrans(String id) {
        return null;
    }
}
