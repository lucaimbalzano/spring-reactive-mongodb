package com.javaexample.spring.reactive.exercises;

//generic imports to help with simpler IDEs (ie tech.io)

import java.util.*;
import java.util.function.*;
import java.time.*;

import com.javaexample.spring.reactive.exercises.pojo.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
 Learning Flux
 link to the website https://tech.io/playgrounds/929/reactive-programming-with-reactor-3/
 */
public class Part04Transform {

//========================================================================================

    // Capitalize the user username, firstname and lastname
    Mono<User> capitalizeOne(Mono<User> mono) {
        return mono.map(m ->
                new User(
                        m.getFirstname().toUpperCase(),
                        m.getLastname().toUpperCase(),
                        m.getUsername().toUpperCase()
                ));
    }

//========================================================================================

    //  Capitalize the users username, firstName and lastName
    Flux<User> capitalizeMany(Flux<User> flux) {
        return flux.map(m ->
                new User(
                        m.getFirstname().toUpperCase(),
                        m.getLastname().toUpperCase(),
                        m.getUsername().toUpperCase()
                ));
    }

//========================================================================================

    //  Capitalize the users username, firstName and lastName using #asyncCapitalizeUser
    Flux<User> asyncCapitalizeMany(Flux<User> flux) {

        //TODO return flux.map( u -> this::asyncCapitalizeUser(u));
        return null;
    }

    Mono<User> asyncCapitalizeUser(User u) {
        return Mono.just(new User(u.getUsername().toUpperCase(), u.getFirstname().toUpperCase(), u.getLastname().toUpperCase()));
    }

}
