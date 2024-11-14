package com.nttdata.bankservice.services;


import com.nttdata.bankservice.entity.CreditProduct;
import com.nttdata.bankservice.dto.TransactionDto;
import reactor.core.publisher.Mono;
public interface CreditProductService {
    Mono<CreditProduct> payCredit(String creditId, TransactionDto payment);
    Mono<Double> getCreditBalance(String creditId);
}
