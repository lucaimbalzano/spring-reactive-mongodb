package com.javaexample.spring.reactive.controller;


import com.javaexample.spring.reactive.dto.PersonDTO;
import com.javaexample.spring.reactive.service.AddressService;
import com.javaexample.spring.reactive.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/person")
public class ReactivePersonController {

    @Autowired
    private PersonService personService;

    @Autowired
    private AddressService addressService;

    @GetMapping(value = "/allpeople", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<PersonDTO> getAllPeople() {
        return personService.getPeople();
    }

    @GetMapping(value = "/getperson/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PersonDTO> getPersonById(@PathVariable String id) {
        return personService.getPersonById(id);
    }

    @GetMapping(value = "/getpersonsortname/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<PersonDTO> getAllPeopleSortByName(@PathVariable String name) {
        return personService.getAllPeopleSortByName(name);
    }

    @GetMapping(value = "/getpeoplebyoddage", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<PersonDTO> getPeopleByOddAge() {
        return personService.getPeopleByOddAge();
    }

    @GetMapping(value = "/getpeoplebyevenage", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<PersonDTO> getPeopleByEvenAge() {
        return personService.getPeopleByEvenAge();
    }

    @GetMapping(value = "/getallpeoplesortbyage/{age}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<PersonDTO> getAllPeopleSortByAge(@PathVariable Integer age) {
        return personService.getAllPeopleSortByAge(age);
    }

    @PostMapping(value = "/addperson", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PersonDTO> addPerson(@RequestBody Mono<PersonDTO> personDTOMono) {
        return personService.addPeople(personDTOMono);
    }

    @PutMapping(value = "/updateperson/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PersonDTO> updatePerson(@RequestBody Mono<PersonDTO> personDTOMono, @PathVariable String id) {
        return personService.updatePeople(personDTOMono, id);
    }

    @DeleteMapping(value = "/deleteperson/{id}")
    public  Mono<String> deletePerson(@PathVariable String id) {
        return personService.deletePerson(id);
    }

    @DeleteMapping(value = "/deleteAll")
    public  Mono<String> deletePeople() {
        return personService.deletePeople();
    }

    @PostMapping(value = "/initDatabase")
    public String initDatabase() {
        String result = "";
        try {
            result = personService.initDb(addressService);
        } catch (Exception e) {
            log.error(" -- Error Occured: " + e.getMessage());
            e.printStackTrace();
        }
        return (result == null ? "Error While Initializing Database: result NULL" : result);
    }
}
