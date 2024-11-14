package com.nttdata.bankservice.services;


import com.nttdata.bankservice.dto.AccountDto;
import com.nttdata.bankservice.dto.TransactionDto;
import com.nttdata.bankservice.entity.Account;
import com.nttdata.bankservice.entity.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
public interface AccountService {
    Mono<Account> deposit(String accountId, TransactionDto transaction);
    Mono<Account> withdraw(String accountId, TransactionDto transaction);
    Mono<Double> getBalance(String accountId);
    Flux<Transaction> getTransactions(String accountId);
    Mono<Account> saveAccount(AccountDto accountDto);

    //Mono<Account> updateAccount(AccountDto accountDto);

    //Mono<Void> deleteAccount(String id);

}
