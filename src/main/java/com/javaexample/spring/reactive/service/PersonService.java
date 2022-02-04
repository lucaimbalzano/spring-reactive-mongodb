package com.javaexample.spring.reactive.service;


import com.javaexample.spring.reactive.dto.PersonDTO;
import com.javaexample.spring.reactive.entity.Person;
import com.javaexample.spring.reactive.repository.PersonRepository;
import com.javaexample.spring.reactive.transformer.PersonTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Flux<PersonDTO> getPeople() {
        return personRepository.findAll().map(PersonTransformer::entityToDTO);
    }

    public Mono<PersonDTO> addPeople(Mono<PersonDTO> personDTOMono) {
        log.info(" +++ @Service addPeople(Mono<PersonDTO> p) called");
        return personDTOMono.map(PersonTransformer::dtoToEntity)
                .flatMap(personRepository::insert)
                .map(PersonTransformer::entityToDTO);
    }

    public Mono<PersonDTO> updatePeople(Mono<PersonDTO> personDTOMono, String id) {
        log.info(" +++ @Service updatePeople(Mono<PersonDTO> p, Integer id) called");
        return personRepository.findById(id)
                .flatMap(person -> personDTOMono.map(PersonTransformer::dtoToEntity)
                        .doOnNext(p -> p.setId(id)))
                .flatMap(personRepository::save)
                .map(PersonTransformer::entityToDTO);
    }

    public String deletePerson(String id) {
        log.info(" ++ @Service deletePeople(String id) called");
        personRepository.deleteById(id);
        return "Person deleted with success";
    }

    public String initDb() throws Exception{
        log.info(" ++ @Service Initializating Database - START");

        Mono<Void> deleteAllPeople = personRepository.deleteAll();
        deleteAllPeople.subscribe();

        Flux.fromIterable( fillRandomList()).flatMap( person -> Mono.just(PersonTransformer.dtoToEntity(person)))
                .flatMap(personEntity -> personRepository.save(personEntity))
                .subscribe();
        log.info(" ++ @Service Initializating Database - END");
        return "Database Initialized";
    }
    private List<PersonDTO> fillRandomList(){
        List<PersonDTO> list = new ArrayList<>();
        for(int i = 0; i <= 1000; i++){
            list.add(new PersonDTO(i,"randomName"+i,Math.abs(new Random().nextInt()%1000)));
        }
        return list;
    }
}