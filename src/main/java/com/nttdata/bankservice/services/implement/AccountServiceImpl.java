package com.nttdata.bankservice.services.implement;


import com.nttdata.bankservice.dto.AccountDto;
import com.nttdata.bankservice.dto.CustomerDto;
import com.nttdata.bankservice.dto.TransactionDto;
import com.nttdata.bankservice.entity.Account;
import com.nttdata.bankservice.entity.Customer;
import com.nttdata.bankservice.entity.Transaction;
import com.nttdata.bankservice.repository.AccountRepository;
import com.nttdata.bankservice.repository.TransactionRepository;
import com.nttdata.bankservice.services.AccountService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{


    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepo;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepo, TransactionRepository transactionRepository){
        this.transactionRepository = transactionRepository;
        this.accountRepo = accountRepo;
    }

    @Override
    public Mono<Account> saveAccount(AccountDto accountDto){
        if(Objects.nonNull(accountDto)){
            return accountRepo.save(accountDtoToEntity(accountDto));
        }else{
            return null;
        }
    }

    public Mono<Account> deposit(String accountId, TransactionDto transaction) {
        return accountRepo.findById(accountId)
                .flatMap(account -> {
                    account.setBalance(account.getBalance() + transaction.getAmount());
                    return accountRepo.save(account)
                            .flatMap(updatedAccount ->
                                    recordTransaction(accountId, transaction.getAmount(), "deposit")
                                            .thenReturn(updatedAccount)  // Retorna el `Account` actualizado después de guardar la transacción
                            );
                });
    }

    @Override
    public Mono<Account> withdraw(String accountId, TransactionDto transaction) {
        return accountRepo.findById(accountId)
                .flatMap(account -> {
                    if (account.getBalance() < transaction.getAmount()) {
                        return Mono.error(new IllegalArgumentException("Insufficient funds"));
                    }
                    account.setBalance(account.getBalance() - transaction.getAmount());
                    return accountRepo.save(account)
                            .flatMap(updatedAccount ->
                                    recordTransaction(accountId, transaction.getAmount(),"withdrawal")
                                            .thenReturn(updatedAccount)
                            );
                            //.then(recordTransaction(accountId, transaction.getAmount(), "withdrawal"));
                });
    }

    @Override
    public Mono<Double> getBalance(String accountId) {
        return accountRepo.findById(accountId)
                .map(Account::getBalance);
    }

    @Override
    public Flux<Transaction> getTransactions(String accountId) {
        return transactionRepository.findByProductId(accountId);
    }

    public static Account accountDtoToEntity(AccountDto accountDto){
        Account account=new Account();
        BeanUtils.copyProperties(accountDto, account);
        return account;
    }

    private Mono<Transaction> recordTransaction(String productId, double amount, String type) {
        Transaction transaction = new Transaction();
        transaction.setProductId(productId);
        transaction.setAmount(amount);
        transaction.setType(type);
        transaction.setTransactionDate(LocalDateTime.parse(LocalDateTime.now().toString()));
        return transactionRepository.save(transaction);
    }

}
