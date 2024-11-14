package com.nttdata.bankservice.services;

import com.nttdata.bankservice.dto.CustomerDto;
import com.nttdata.bankservice.entity.Customer;
import com.nttdata.bankservice.repository.CustomerRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
public interface CustomerService {

    Mono<Customer> saveOrg(CustomerDto customerDto);

    Mono<Customer> findById(String id);

    Flux<Customer> findAll();

    Mono<Customer> update(CustomerDto customerDtoDto);

    Mono<Void> delete(String id);
}
