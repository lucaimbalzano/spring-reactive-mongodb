package com.javaexample.spring.reactive.exercises;

//generic imports to help with simpler IDEs (ie tech.io)
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.*;
import java.time.*;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
//import reactor.test.StepVerifier; **IntelliJ give me some errors
import reactor.core.publisher.Flux;

//import static org.assertj.core.api.Assertions.*;

/*
 Learning Flux
 link to the website https://tech.io/playgrounds/929/reactive-programming-with-reactor-3/
 */
@Slf4j
public class Part03StepVerifier {

//========================================================================================

    // Use StepVerifier to check that the flux parameter emits "foo" and "bar" elements then completes successfully.
    public void expectFooBarComplete(Flux<String> flux) {
//                StepVerifier.create(flux)
//                .expectNext("foo")
//                .expectNext("bar")
//                .expectComplete()
//                .verify();
    }

//========================================================================================

    // Use StepVerifier to check that the flux parameter emits "foo" and "bar" elements then a RuntimeException error.
    public void expectFooBarError(Flux<String> flux) {
//        StepVerifier.create(flux)
//                .expectNext("foo")
//                .expectNext("bar")
//                .verifyError(RuntimeException.class);
    }

//========================================================================================
    //                                                          User passed in input (Flux<User)
    // Use StepVerifier to check that the flux parameter emits a User with "swhite"username
    // and another one with "jpinkman" then completes successfully.

    public void expectSkylerJesseComplete(Flux<Object> flux) {
//        StepVerifier.create(flux)
//            .assertNext(t -> (Assertions.assertThat(t.getUsername()).isEqualTo("jpinkman")) || (Assertions.assertThat(t.getUsername()).isEqualTo("jpinkman")) )
//            .expectComplete()
//            .verify();
    }

//========================================================================================

    // Expect 10 elements then complete and notice how long the test takes.
   public void expect10Elements(Flux<Long> flux) {
        //StepVerifier.create(flux).expectNextCount(10).expectComplete().verify();
        getTimeFlux(flux).log().subscribe();

    }
    private <T>  Flux<T> getTimeFlux(Flux<T> flux) {
        return Mono.fromSupplier(System::nanoTime)
                .flatMapMany(time -> flux.doFinally(sig -> log.info("Time taken : " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - time) + " milliseconds.")));
    }


//========================================================================================

    // Expect 3600 elements at intervals of 1 second, and verify quicker than 3600s
    // by manipulating virtual time thanks to StepVerifier#withVirtualTime, notice how long the test takes
    public void expect3600Elements(Supplier<Flux<Long>> supplier) {
//        StepVerifier.withVirtualTime(() -> Mono.just(supplier).delay(Duration.ofMillis(3600)))
//                .expectSubscription()
//                .expectNoEvent(Duration.ofHours(2))
//                .thenAwait(Duration.ofHours(1))
//                .expectNextCount(3600)
//                .expectComplete()
//                .verify();
    }



    private void fail() {
        throw new AssertionError("workshop not implemented");
    }



}

