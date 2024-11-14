package com.nttdata.bankservice.controller;


import com.nttdata.bankservice.dto.CustomerDto;
import com.nttdata.bankservice.entity.Customer;
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
@RequestMapping(value = "/api/v1/custom")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {
    private final CustomerService custService;

    @PostMapping("/save")
    public ResponseEntity<Mono<Customer>> save(@RequestBody CustomerDto customerDto) throws ExecutionException, InterruptedException {
        Mono<Customer> custSave = custService.saveOrg(customerDto);
        return new ResponseEntity<Mono<Customer>>(custSave, HttpStatus.OK);
    }

    @GetMapping("/{customId}")
    public ResponseEntity<Mono<Customer>> findById(@PathVariable("customId") String id) {
        Mono<Customer> findId = custService.findById(id);
        return new ResponseEntity<Mono<Customer>>(findId, HttpStatus.OK);
    }


    @GetMapping("/all")
    public Flux<Customer> findAll() {
        Flux<Customer> orgAll = custService.findAll();
        return orgAll;
    }

    @PutMapping("/update")
    public Mono<Customer> update(@RequestBody CustomerDto customerDto) {
        return custService.update(customerDto);
    }

    @DeleteMapping("/{customId}")
    public void delete(@PathVariable("customId") String id) {
        custService.delete(id).subscribe();
    }

}
