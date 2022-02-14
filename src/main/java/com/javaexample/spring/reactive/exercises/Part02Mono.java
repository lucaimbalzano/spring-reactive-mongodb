package com.javaexample.spring.reactive.exercises;

//generic imports to help with simpler IDEs (ie tech.io)

import java.util.*;
import java.util.function.*;
import java.time.*;

import reactor.core.publisher.Mono;

/*
 Learning Flux
 link to the website https://tech.io/playgrounds/929/reactive-programming-with-reactor-3/
 */
public class Part02Mono {

//========================================================================================

    //  Return an empty Mono
    public Mono<String> emptyMono() {
        return Mono.empty();
    }

//========================================================================================

    // Return a Mono that never emits any signal
    public Mono<String> monoWithNoSignal() {
        return Mono.never();
    }

//========================================================================================

    // TODO Return a Mono that contains a "foo" value
    public Mono<String> fooMono() {
        return Mono.just("foo");
    }

//========================================================================================

    // TODO Create a Mono that emits an IllegalStateException
    public Mono<String> errorMono() {
        return Mono.error(new IllegalStateException());
    }
}
