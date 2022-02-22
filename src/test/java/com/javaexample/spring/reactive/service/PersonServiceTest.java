package com.javaexample.spring.reactive.service;

import com.javaexample.spring.reactive.dto.AddressDTO;
import com.javaexample.spring.reactive.dto.PersonDTO;
import com.javaexample.spring.reactive.entity.Person;
import com.javaexample.spring.reactive.repository.PersonRepository;
import com.javaexample.spring.reactive.transformer.PersonTransformer;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static reactor.core.publisher.Mono.when;



@Slf4j
@DataMongoTest
class PersonServiceTest {

    private final static  String PERSON_ID = "62069b5ab29fda005033e3fe";
    private final static  Integer AGE_TO_SORT = 25;
    private final static  String NAME_TO_SORT = "Mark";

    @Autowired
    private PersonRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getPeople() {
        Flux<Person> people = repository.findAll().log();
        assertEquals(true,people!=null);
        log.info("[TEST] - "+people.collectList());
//
//        PersonDTO personDTO = new PersonDTO();
//        personDTO.setAddressDTO(new AddressDTO());
//        personDTO.setFirstname("name");
//        personDTO.setSurname("surname");
//        when(personRepository.findAll()).thenReturn(Flux.just(personDTO));
//        StepVerifier.create(personRepository.findAll()
//                .filter(person -> person != null)
//                .map(PersonTransformer::entityToDTO)
//                .log())
//                 .expectNext()
//                .expectComplete();

    }

    @DisplayName("[TEST] getPersonById()")
    @Test
    void getPersonById() {
        Mono<Person> personMono = repository.findById(PERSON_ID);
        assertAll("personMono", ()->{
            assertNotNull(personMono);
        });
                StepVerifier.create(personMono.log())
                 .expectNext()
                .expectComplete();
    }

    @Test
    void getAllPeopleSortByAge() {
        Flux<PersonDTO> personDTOFluxAgeSorted = repository.findAll()
                .map(PersonTransformer::entityToDTO)
                .filter(p -> p.getAge() == AGE_TO_SORT)
                .log();


        StepVerifier.create(personDTOFluxAgeSorted)
        .thenConsumeWhile(p ->{
            if(p!=null){ log.info("PERSON SORTED BY AGE : "+p.toString()); return true;}
            return false;

        })
        .verifyComplete();
    }

    @Test
    void getAllPeopleSortByName() {
        Flux<PersonDTO> personDTOFluxSortedByName = repository.findAll()
                .map(PersonTransformer::entityToDTO)
                .filter(p -> p.getFirstname().equalsIgnoreCase(NAME_TO_SORT));

        StepVerifier.create(personDTOFluxSortedByName)
                .thenConsumeWhile(p ->{
                    if(p!=null){ log.info("PERSON SORTED BY NAME: "+p.toString()); return true;}
                    return false;

                })
                .verifyComplete();
    }


}
