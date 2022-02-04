package com.javaexample.spring.reactive;

import com.javaexample.spring.reactive.dto.PersonDTO;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MonoFluxTest {
    @Test
    public void testMono(){
        List<PersonDTO> list = fillRandomList();
        Mono<List<PersonDTO>> monoPerson = Mono.just(list).log();
        monoPerson.subscribe(r -> Arrays.toString(r.toArray()));
       //monoPerson.subscribe(System.out::println);
    }

    @Test
    public void testMonoException(){
        Mono<?> monoPerson = Mono.just(new PersonDTO(0,"lucas",33))
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


    private List<PersonDTO> fillRandomList(){
        List<PersonDTO> list = new ArrayList<>();
        for(int i = 0; i <= 1000; i++){
            list.add(new PersonDTO(i,"randomName"+i, Math.abs(new Random().nextInt()%100)));
        }
        return list;
    }
}
