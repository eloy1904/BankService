package com.nttdata.bankservice.controller;

import com.nttdata.bankservice.dto.TransactionDto;
import com.nttdata.bankservice.entity.CreditProduct;
import com.nttdata.bankservice.services.CreditProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/v1/credit-products")
@RequiredArgsConstructor
@Slf4j
public class CreditProductController {

    private final CreditProductService creditProductService;

    @PostMapping("/{creditId}/pay")
    @ResponseStatus(HttpStatus.OK)
    public Mono<CreditProduct> payCredit(@PathVariable String creditId, @RequestBody TransactionDto payment) {
        return creditProductService.payCredit(creditId, payment);
    }


    @GetMapping("/{creditId}/balance")
    @ResponseStatus(HttpStatus.OK)
    public Mono<Double> getCreditBalance(@PathVariable String creditId) {
        return creditProductService.getCreditBalance(creditId);
    }
}
