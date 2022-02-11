package com.javaexample.spring.reactive.service;


import com.javaexample.spring.reactive.controller.ReactiveAddressController;
import com.javaexample.spring.reactive.dto.AddressDTO;
import com.javaexample.spring.reactive.dto.PersonDTO;
import com.javaexample.spring.reactive.repository.PersonRepository;
import com.javaexample.spring.reactive.transformer.AddressTransformer;
import com.javaexample.spring.reactive.transformer.PersonTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    public Flux<PersonDTO> getPeople() {
        log.info(" +++ @Service getPeople() called");
        return personRepository.findAll().map(PersonTransformer::entityToDTO);
    }

    public Mono<PersonDTO> getPersonById(String id) {
        log.info(" +++ @Service getPeopleById(String s) called");
          return personRepository.findById(id).map(PersonTransformer::entityToDTO).switchIfEmpty(Mono.just(new PersonDTO()));
    }

    public Flux<PersonDTO> getAllPeopleSortByName(String nameToSort){
        log.info(" +++ @Service getAllPeopleSortByName(String s) called");
        Flux<PersonDTO> personDTOFlux = personRepository.findAll()
                                .map(PersonTransformer::entityToDTO)
                                .filter(p -> p.getFirstname().equalsIgnoreCase(nameToSort))
                                .log();
        personDTOFlux.subscribe();
        return personDTOFlux;
    }

    public Flux<PersonDTO> getAllPeopleSortByAge(Integer ageToSort){
        log.info(" +++ @Service getAllPeopleSortByAge(Integer i) called");
        return personRepository.findAll()
                .map(PersonTransformer::entityToDTO)
                .filter(p -> p.getAge()  == ageToSort)
                .log();
    }

    public Flux<PersonDTO> getPeopleByOddAge(){
        log.info(" +++ @Service getPeopleByOddAge() called");
        return personRepository.findAll()
                                .map(PersonTransformer::entityToDTO)
                                .filter(personDTO -> personDTO.getAge()%2 != 0)
                                .log();
    }

    public Flux<PersonDTO> getPeopleByEvenAge(){
        log.info(" +++ @Service getPeopleByEvenAge() called");
        return personRepository.findAll()
                .map(PersonTransformer::entityToDTO)
                .filter(personDTO -> personDTO.getAge()%2 == 0)
                .log();
    }

    public List<PersonDTO> getPeopleConvertedFromFluxToArrayListPersonDTO(){
        log.info(" +++ @Service getPeopleConvertedInArrayList() called");
            List<PersonDTO> personDTOList = (List<PersonDTO>) personRepository.findAll()
                    .flatMap(person -> {  return Mono.just(PersonTransformer.entityToDTO(person));});
        return personDTOList;
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

    public String initDb(AddressService addressService) throws Exception{
        log.info(" ++ @Service Initializating Database - START");
        Mono<Void> deleteAllPeople = personRepository.deleteAll();
        deleteAllPeople.subscribe();
        List<PersonDTO> list = fillRandomList(addressService);
        Flux.fromIterable(list).flatMap( person -> Mono.just(PersonTransformer.dtoToEntity(person)))
                .flatMap(personEntity -> personRepository.save(personEntity))
                .subscribe();
        log.info(" ++ Total people: " +personRepository.count());
        log.info(" ++ @Service Initializating Database - END");
        return "Database Initialized";
    }
    private List<PersonDTO> fillRandomList(AddressService addressService){
        List<PersonDTO> list = new ArrayList<>();
        ReactiveAddressController addressController = new ReactiveAddressController();
        int i=0;
        Date date = parseDate("1974-02-14");
        List<AddressDTO> addressDTOlist =  addressService.getAddressConvertedFromFluxToArrayListAddressDTO();
                for(AddressDTO ad: addressDTOlist){
                    list.add(new PersonDTO(null,"firstname_"+ad.getId(),"surname_"+ad.getId(),date,Math.abs(new Random().nextInt()%1000),ad));
                }
        return list;
    }

    private String getRandomString(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 24;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
}