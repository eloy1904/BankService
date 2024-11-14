package com.nttdata.bankservice.repository;


import com.nttdata.bankservice.entity.Account;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends ReactiveMongoRepository<Account, String>{
}
