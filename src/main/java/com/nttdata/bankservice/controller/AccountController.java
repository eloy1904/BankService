package com.nttdata.bankservice.controller;


import com.nttdata.bankservice.dto.AccountDto;
import com.nttdata.bankservice.dto.CustomerDto;
import com.nttdata.bankservice.dto.TransactionDto;
import com.nttdata.bankservice.entity.Account;
import com.nttdata.bankservice.entity.Customer;
import com.nttdata.bankservice.entity.Transaction;
import com.nttdata.bankservice.services.AccountService;
import com.nttdata.bankservice.services.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/api/v1/accounts")
@RequiredArgsConstructor
@Slf4j
public class AccountController {
    private final AccountService accountService;


    @PostMapping("/save")
    public ResponseEntity<Mono<Account>> save(@RequestBody AccountDto accountDto) throws ExecutionException, InterruptedException {
        Mono<Account> custSave = accountService.saveAccount(accountDto);
        return new ResponseEntity<Mono<Account>>(custSave, HttpStatus.OK);
    }

    // Endpoint para depositar dinero en una cuenta
    @PostMapping("/{accountId}/deposit")
    public Mono<ResponseEntity<Account>> deposit(@PathVariable String accountId, @RequestBody TransactionDto transactionDto) {
        return accountService.deposit(accountId, transactionDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // Endpoint para retirar dinero de una cuenta
    @PostMapping("/{accountId}/withdraw")
    public Mono<ResponseEntity<Account>> withdraw(@PathVariable String accountId, @RequestBody TransactionDto transactionDto) {
        return accountService.withdraw(accountId, transactionDto)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // Endpoint para obtener el balance de una cuenta
    @GetMapping("/{accountId}/balance")
    public Mono<ResponseEntity<Double>> getBalance(@PathVariable String accountId) {
        return accountService.getBalance(accountId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // Endpoint para obtener las transacciones de una cuenta
    @GetMapping("/{accountId}/transactions")
    public Flux<Transaction> getTransactions(@PathVariable String accountId) {
        return accountService.getTransactions(accountId);
    }
}
