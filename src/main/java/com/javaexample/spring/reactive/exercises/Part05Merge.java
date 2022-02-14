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
public class Part05Merge {

//========================================================================================

    //  Merge flux1 and flux2 values with interleave
    Flux<User> mergeFluxWithInterleave(Flux<User> flux1, Flux<User> flux2) {
        return flux1.mergeWith(flux2);
    }

//========================================================================================

    //  Merge flux1 and flux2 values with no interleave (flux1 values and then flux2 values)
    Flux<User> mergeFluxWithNoInterleave(Flux<User> flux1, Flux<User> flux2) {
        return flux1.concatWith(flux2);
    }

//========================================================================================

    //  Create a Flux containing the value of mono1 then the value of mono2
    Flux<User> createFluxFromMultipleMono(Mono<User> mono1, Mono<User> mono2) {
        return mono1.concatWith(mono2);
    }

}
