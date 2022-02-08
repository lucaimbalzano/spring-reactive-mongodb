package com.javaexample.spring.reactive.exercises;

//generic imports to help with simpler IDEs (ie tech.io)
import java.util.*;
import java.util.function.*;
import java.time.*;

import java.util.concurrent.CompletableFuture;
//import io.reactivex.rxjava3.core.Flowable;
//import io.reactivex.rxjava3.core.Observable;
//import io.reactivex.rxjava3.core.Single;
//import io.reactivex.rxjava3.core.Observable;
//import io.reactivex.rxjava3.core.Single;
import com.javaexample.spring.reactive.exercises.pojo.User;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Part09Adapt {

//========================================================================================

    // TODO Adapt Flux to RxJava Flowable
//    Flowable<User> fromFluxToFlowable(Flux<User> flux) {
//        return null;
//    }
//
//    // TODO Adapt RxJava Flowable to Flux
//    Flux<User> fromFlowableToFlux(Flowable<User> flowable) {
//        return null;
//    }

//========================================================================================

//    // TODO Adapt Flux to RxJava Observable
//    Observable<User> fromFluxToObservable(Flux<User> flux) {
//        return null;
//    }
//
//    // TODO Adapt RxJava Observable to Flux
//    Flux<User> fromObservableToFlux(Observable<User> observable) {
//        return null;
//    }

//========================================================================================

    // TODO Adapt Mono to RxJava Single
//    Single<User> fromMonoToSingle(Mono<User> mono) {
//        return null;
//    }

    // TODO Adapt RxJava Single to Mono
//    Mono<User> fromSingleToMono(Single<User> single) {
//        return null;
//    }

//========================================================================================

    // TODO Adapt Mono to Java 8+ CompletableFuture
    CompletableFuture<User> fromMonoToCompletableFuture(Mono<User> mono) {
        return null;
    }

    // TODO Adapt Java 8+ CompletableFuture to Mono
    Mono<User> fromCompletableFutureToMono(CompletableFuture<User> future) {
        return null;
    }

}

