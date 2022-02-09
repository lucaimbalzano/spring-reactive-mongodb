package com.javaexample.spring.reactive.repository;

import com.javaexample.spring.reactive.entity.Address;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface AddressRepository extends ReactiveMongoRepository<Address,String> {

    Flux<Address> findAddressByCity(String city);
}
