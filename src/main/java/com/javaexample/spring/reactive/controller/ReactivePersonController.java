package com.javaexample.spring.reactive.controller;


import com.javaexample.spring.reactive.dto.PersonDTO;
import com.javaexample.spring.reactive.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

   @GetMapping( value= "/allpeople", produces = MediaType.APPLICATION_JSON_VALUE)
   public Flux<PersonDTO> getAllPeople(){ return personService.getPeople(); }

    @PostMapping( value = "/addperson" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PersonDTO> addPerson(@RequestBody Mono<PersonDTO> personDTOMono){
       return personService.addPeople(personDTOMono);
    }

    @PutMapping( value = "/updateperson/{id}" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<PersonDTO> updatePerson(@RequestBody Mono<PersonDTO> personDTOMono, @PathVariable String id){
       return personService.updatePeople(personDTOMono,id);
    }

    @DeleteMapping( value = "/deleteperson/{id}")
    public String deletePerson(@PathVariable String id){
       return personService.deletePerson(id);
    }

    @PostMapping( value = "/initDatabase")
    public String initDatabase(){
       String result = "";
       try{
           result = personService.initDb();
       }catch (Exception e){
           log.error(" -- Error Occured: "+e.getMessage());
           e.printStackTrace();
       }
       return  (result==null? "Error While Initializing Database: result NULL": result);
    }
}