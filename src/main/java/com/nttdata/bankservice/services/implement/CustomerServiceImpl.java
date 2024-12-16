package com.nttdata.bankservice.services.implement;

import com.nttdata.bankservice.dto.CustomerDto;
import com.nttdata.bankservice.entity.Customer;
import com.nttdata.bankservice.entity.response.ResponseRedis;
import com.nttdata.bankservice.repository.CustomerRepository;
import com.nttdata.bankservice.services.CustomerService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.kafka.receiver.KafkaReceiver;
import reactor.kafka.receiver.ReceiverRecord;
import org.springframework.beans.factory.annotation.Autowired;
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    @NonNull
    private CustomerRepository CustRepo;

    @Autowired
    private final KafkaReceiver<String, String> kafkaReceiver;

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    private static final String CB_INSTANCE = "customerServiceCircuitBreaker";
    /*
    public CustomerServiceImpl(KafkaReceiver<String, String> kafkaReceiver) {
        this.kafkaReceiver = kafkaReceiver;
    }*/

    @PostConstruct
    public void consumeMessages() {
        kafkaReceiver.receive()
                .doOnNext(this::processMessage)
                .doOnError(error -> System.err.println("Error consuming message: " + error.getMessage()))
                .subscribe();
    }

    private void processMessage(ReceiverRecord<String, String> record) {
        String transactionMessage = record.value();
        System.out.println("Received message: " + transactionMessage);

        notifyCustomerService(transactionMessage);

        record.receiverOffset().acknowledge();
    }

    private void notifyCustomerService(String transactionMessage) {

        System.out.println("Notifying customer with message: " + transactionMessage);
    }


    @Override
    @CircuitBreaker(name = CB_INSTANCE, fallbackMethod = "fallbackSaveOrg")
    public Mono<Customer> saveOrg(CustomerDto customerDto){
        if(Objects.nonNull(customerDto)){
            return CustRepo.save(customerDtoToEntity(customerDto));
        }else{
            return null;
        }
    }

    @Override
    @CircuitBreaker(name = CB_INSTANCE, fallbackMethod = "fallbackFindById")
    public Mono<Customer> findById(String id) {
        return CustRepo.findById(id);
    }

    @Override
    @Cacheable("findAll")
    public Flux<Customer> findAll() {
        //return CustRepo.findAll();
        logger.info("Consultando datos en la base de datos...");
        return CustRepo.findAll()
                .doOnNext(data -> logger.info("Datos obtenidos de la BD: {}", data));
    }


    @Override
    public Mono<Customer> update(CustomerDto customerDto) {
        return this.CustRepo.findById(customerDto.getId())
                .map(org -> customerDtoToEntity(customerDto))
                .flatMap(this.CustRepo::save);
    }

    @Override
    public Mono<Void> delete(String id) {
        return CustRepo.deleteById(id);
    }

    public static Customer customerDtoToEntity(CustomerDto customerDto){
        Customer customer=new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        return customer;
    }

    private Mono<Customer> fallbackSaveOrg(CustomerDto customerDto, Throwable t) {
        logger.error("Fallback for saveOrg due to error: {}", t.getMessage());
        return Mono.empty();
    }

    private Mono<Customer> fallbackFindById(String id, Throwable t) {
        logger.error("Fallback for findById due to error: {}", t.getMessage());
        return Mono.empty();
    }

    private Flux<Customer> fallbackFindAll(Throwable t) {
        logger.error("Fallback for findAll due to error: {}", t.getMessage());
        return Flux.empty();
    }
}
