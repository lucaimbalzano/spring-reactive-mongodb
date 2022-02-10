package com.javaexample.spring.reactive;

import com.javaexample.spring.reactive.dto.PersonDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Slf4j
public class MonoFluxTest {
    @Test
    public void testMono(){
//        List<PersonDTO> list = fillRandomList();
//        Mono<List<PersonDTO>> monoPerson = Mono.just(list).log();
//        monoPerson.subscribe(r -> Arrays.toString(r.toArray()));
       //monoPerson.subscribe(System.out::println);
    }

    @Test
    public void testMonoException(){

        Mono<?> monoPerson = Mono.just(new PersonDTO())
               // .then(Mono.error(new RuntimeException("Exception occurred TEST")))
                .log();
        monoPerson.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));
    }

    @Test
    public void testFlux(){
        Flux<String> fluxString = Flux.just("123","ABC","abc","OneTwoTree")
                .concatWithValues("ConcatValues")
           //     .concatWith(Flux.error(new StackOverflowError("Unbounded Loop")))
                .log();
        fluxString.subscribe(System.out::println, (e) -> System.out.println(e.getMessage()));
    }

    @Test
    public void monoSubscriberConsumerSubscription(){
        Mono<String> nameMono = Mono.just("RandomName")
                                    .log()
                                    .map(String::toUpperCase);
        nameMono.subscribe(s -> log.info(" ++ INTO SUBSCRIBE, item transformed with upperCase(): {}",s),
                                Throwable::printStackTrace,
                                () -> log.info(" ++ BEFORE SUBSCIPTION REQUEST"),
                                subscription -> subscription.request(100));
        log.info(" ------------------------ ");

        StepVerifier.create(nameMono)
                    .expectNext(nameMono.block())
                    .verifyComplete();
    }

    @Test
    public void monoOnDoMethod(){
        Mono<String> nameMono = Mono.just("RandomName");
        nameMono
                .log()
                .map(String::toUpperCase)
                .doOnSubscribe(subscription -> log.info(" ++ doOnSubscribe: {}",subscription))
                .doOnRequest(request -> log.info(" ++ Request received .. doOnRequest(), rquest: {} ", request))
                .doOnNext(dataEmit -> log.info(" ++ Data emit succesfully .. doOnNext(), data: {}", dataEmit))
                .doOnSuccess(success -> log.info(" ++ doOnsuccess() executed,  {}", success));
    }

//    @Test
//    public void rangeFlux(){
//        StepVerifier.create(Flux.range(1,10)
//                                .log()
//                                .map(i -> {
//                                    if(i==11)
//                                        throw new IndexOutOfBoundsException("Index Error Occured");
//                                    return i;
//                                })
//                            )
//                    .expectNext(1,2,3,4,5,6,7,8,9,10)
//                   .expectError(IndexOutOfBoundsException.class)
//                    .verifyComplete();
//    }
//
////    private List<PersonDTO> fillRandomList(){
//        List<PersonDTO> list = new ArrayList<>();
//        for(int i = 0; i <= 1000; i++){
//            list.add(new PersonDTO(i,"randomName"+i, Math.abs(new Random().nextInt()%100)));
//        }
//        return list;
//    }
}
