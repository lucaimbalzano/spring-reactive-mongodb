package com.javaexample.spring.reactive;

import com.javaexample.spring.reactive.exercises.Part01Flux;
import com.javaexample.spring.reactive.exercises.Part02Mono;
import com.javaexample.spring.reactive.exercises.Part03StepVerifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringReactiveMongodbApplication {

    public static void main(String[] args) {
        Part01Flux testFlux = new Part01Flux();
        Part02Mono testMono = new Part02Mono();
        Part03StepVerifier testStepVerifier = new Part03StepVerifier();

        SpringApplication.run(SpringReactiveMongodbApplication.class, args);
    }

    static Flux<Integer> getFluxTest(){
        return Flux.just(1,123,1234,13223);
    }
}
