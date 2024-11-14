package com.nttdata.bankservice.repository;

import com.nttdata.bankservice.entity.CreditProduct;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditProductRepository extends ReactiveMongoRepository<CreditProduct, String>{
}
