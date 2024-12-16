package com.nttdata.bankservice.services.implement;

import com.nttdata.bankservice.entity.Account;
import com.nttdata.bankservice.dto.TransactionDto;
import com.nttdata.bankservice.repository.AccountRepository;
import com.nttdata.bankservice.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AccountServiceImplTest {

    @Mock
    private  TransactionRepository transactionRepository;

    @Mock
    private  AccountRepository accountRepo;

    @InjectMocks
    private AccountServiceImpl accountService;

    private Account account;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        account = new Account();
        account.setId("12345");
        account.setBalance(200.0);
    }

    @Test
    void deposit() {
        // Arrange
        String accountId = "12345";
        TransactionDto transaction = new TransactionDto();
        transaction.setAmount(100.0);

        Account updatedAccount = new Account();
        updatedAccount.setId(accountId);
        updatedAccount.setBalance(300.0); // 200.0 + 100.0

        // Mock de comportamiento
        when(accountRepo.findById(accountId)).thenReturn(Mono.just(account));
        when(accountRepo.save(any(Account.class))).thenReturn(Mono.just(updatedAccount));
        when(transactionRepository.save(any())).thenReturn(Mono.empty());

        // Act
        Mono<Account> result = accountService.deposit(accountId, transaction);

        // Assert
        StepVerifier.create(result)
                .expectNext(updatedAccount)
                .verifyComplete();

        verify(accountRepo).findById(accountId);
        verify(accountRepo).save(any(Account.class));
        verify(transactionRepository).save(any());


    }
}