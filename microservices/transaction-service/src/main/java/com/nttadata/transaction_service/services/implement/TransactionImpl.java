package com.nttadata.transaction_service.services.implement;

import com.nttadata.transaction_service.dto.TransactionDto;
import com.nttadata.transaction_service.entity.Transaction;
import com.nttadata.transaction_service.repository.TransactionRepository;
import com.nttadata.transaction_service.services.TransactionService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TransactionImpl implements TransactionService {
    @NonNull
    private final TransactionRepository transactionRepository;

    @Override
    public Mono<Transaction> save(TransactionDto transactionDto) {
        if(Objects.nonNull(transactionDto)){
            return transactionRepository.save(creditDtoToEntity(transactionDto));
        }else{
            return null;
        }
    }

    @Override
    public Mono<Transaction> findById(String Id) {
        return transactionRepository.findById(Id);
    }

    @Override
    public Flux<Transaction> findAll() {
        return transactionRepository.findAll();
    }
    @Override
    public Mono<Transaction> udpate(TransactionDto transactionDto) {
        return this.transactionRepository.findById(transactionDto.getTransactionId())
                .map(credit -> creditDtoToEntity(transactionDto))
                .flatMap(this.transactionRepository::save);
    }

    @Override
    public Mono<Void> delete(String Id) {
        return transactionRepository.deleteById(Id);
    }

    public static Transaction creditDtoToEntity(TransactionDto transactionDto){
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(transactionDto, transaction);
        return transaction;
    }
}
