package com.nttdata.bankservice.repository;


import com.nttdata.bankservice.entity.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer,String>{
}
