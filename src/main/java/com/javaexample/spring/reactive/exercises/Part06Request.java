package com.javaexample.spring.reactive.exercises;

//generic imports to help with simpler IDEs (ie tech.io)
import java.util.*;
import java.util.function.*;
import java.time.*;


import com.javaexample.spring.reactive.exercises.pojo.User;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

/*
 Learning Flux
 link to the website https://tech.io/playgrounds/929/reactive-programming-with-reactor-3/
 */
public class Part06Request {
    //Like a repository
    User repository = new User();

//========================================================================================

    //  Create a StepVerifier that initially requests all values and expect 4 values to be received
    StepVerifier requestAllExpectFour(Flux<User> flux) {
        return StepVerifier.create(flux)
                            .expectSubscription()
                            .thenRequest(Long.MAX_VALUE)
                            .expectNextCount(4)
                            .expectComplete();
    }

//========================================================================================

    //  Create a StepVerifier that initially requests 1 value
    //  and expects User.SKYLER then requests another value and expects User.JESSE
    //  then stops verifying by cancelling the source
    StepVerifier requestOneExpectSkylerThenRequestOneExpectJesse(Flux<User> flux) {
        return StepVerifier.create(flux)
                .expectNext(User.SKYLER)
                .expectNext(User.JESSE)
                .thenCancel();
    }

//========================================================================================

    // Return a Flux with all users stored in the repository that prints automatically logs for all Reactive Streams signals
//    Flux<User> fluxWithLog() {
//        return repository.findAll().log().subscribe();
//    }

//========================================================================================

    //  Return a Flux with all users stored in the repository that prints "Starring:" on subscribe, "firstname lastname" for all values and "The end!" on complete
//    Flux<User> fluxWithDoOnPrintln() {
//            return repository
//                    .findAll()
//                    .doOnSubscribe(s -> System.out.println("Starring"))
//                    .doOnNext(u -> System.out.println(u.getFirstname() + " " + u.getLastname()))
//                    .doOnComplete(() -> System.out.println("The end!"));
//        }
//    }

}

