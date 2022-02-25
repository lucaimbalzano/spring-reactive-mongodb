package com.javaexample.spring.reactive.repository;


import com.javaexample.spring.reactive.dto.AddressDTO;
import com.javaexample.spring.reactive.entity.Person;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

public interface PersonRepository extends ReactiveMongoRepository<Person, String> {

    Flux<Person> findByAgeBetween(Range<Integer> ageRange);

}
