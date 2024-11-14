package com.nttdata.bankservice.services.implement;


import com.nttdata.bankservice.entity.CreditProduct;
import com.nttdata.bankservice.repository.CreditProductRepository;
import com.nttdata.bankservice.services.CreditProductService;
import com.nttdata.bankservice.dto.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreditProductServiceImpl implements CreditProductService{

    private final CreditProductRepository creditProductRepository;

    @Autowired
    public CreditProductServiceImpl(CreditProductRepository creditProductRepository) {
        this.creditProductRepository = creditProductRepository;
    }

    @Override
    public Mono<CreditProduct> payCredit(String creditId, TransactionDto payment) {
        return creditProductRepository.findById(creditId)
                .flatMap(credit -> {
                    credit.setBalance(credit.getBalance() - payment.getAmount());
                    return creditProductRepository.save(credit);
                });
    }

    @Override
    public Mono<Double> getCreditBalance(String creditId) {
        return creditProductRepository.findById(creditId)
                .map(CreditProduct::getBalance);
    }
}
