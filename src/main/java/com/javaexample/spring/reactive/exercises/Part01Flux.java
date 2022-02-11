package com.javaexample.spring.reactive.exercises;


//generic imports to help with simpler IDEs (ie tech.io)
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.*;
import java.time.*;

import com.javaexample.spring.reactive.dto.PersonDTO;
import reactor.core.publisher.Flux;

/*
 Learning Flux
 link to the website https://tech.io/playgrounds/929/reactive-programming-with-reactor-3/
 */
public class Part01Flux {

//========================================================================================

    // Return an empty Flux
    public Flux<String> emptyFlux() {
        return Flux.empty();

    }

//========================================================================================

    // Return a Flux that contains 2 values "foo" and "bar" without using an array or a collection
    public Flux<String> fooBarFluxFromValues() {
        return Flux.just("foo","bar");
    }

//========================================================================================

    // Create a Flux from a List that contains 2 values "foo" and "bar"
    public Flux<String> fooBarFluxFromList() {
        List<String> list = new ArrayList<>(Arrays.asList("foot","bar"));
        return Flux.fromIterable(list);
    }
//========================================================================================

    // Create a Flux that emits an IllegalStateException
    public Flux<String> errorFlux() {
        return Flux.error(new IllegalStateException());
    }

//========================================================================================

    // Create a Flux that emits increasing values from 0 to 9 each 100ms
    public Flux<Long> counter() {
        Duration d = Duration.of(100L, ChronoUnit.MILLIS);
        return Flux.interval(d).take(10L);
    }

}
