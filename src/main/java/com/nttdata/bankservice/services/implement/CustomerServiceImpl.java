package com.nttdata.bankservice.services.implement;

import com.nttdata.bankservice.dto.CustomerDto;
import com.nttdata.bankservice.entity.Customer;
import com.nttdata.bankservice.repository.CustomerRepository;
import com.nttdata.bankservice.services.CustomerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    @NonNull
    private CustomerRepository CustRepo;


    @Override
    public Mono<Customer> saveOrg(CustomerDto customerDto){
        if(Objects.nonNull(customerDto)){
            return CustRepo.save(customerDtoToEntity(customerDto));
        }else{
            return null;
        }
    }

    @Override
    public Mono<Customer> findById(String id) {
        return CustRepo.findById(id);
    }

    @Override
    public Flux<Customer> findAll() {
        return CustRepo.findAll();
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
}
