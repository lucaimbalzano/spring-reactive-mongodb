package com.javaexample.spring.reactive.exercises;

//generic imports to help with simpler IDEs (ie tech.io)

import com.javaexample.spring.reactive.exercises.pojo.User;
import reactor.core.Exceptions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
 Learning Flux
 link to the website https://tech.io/playgrounds/929/reactive-programming-with-reactor-3/
 */
public class Part07Errors {

//========================================================================================

    //  Return a Mono<User> containing User.SAUL when an error occurs in the input Mono,
    //  else do not change the input Mono.
    Mono<User> betterCallSaulForBogusMono(Mono<User> mono) {
        return mono.onErrorReturn(IllegalStateException.class,User.SAUL);
    }

//========================================================================================

    // Return a Flux<User> containing User.SAUL and User.JESSE
    // when an error occurs in the input Flux, else do not change the input Flux.
    Flux<User> betterCallSaulAndJesseForBogusFlux(Flux<User> flux) {
        return flux.onErrorResume(error -> Flux.just(User.JESSE, User.SAUL));
    }

//========================================================================================

    // Implement a method that capitalizes each user of the incoming flux using the
    // #capitalizeUser method and emits an error containing a GetOutOfHereException error
    Flux<User> capitalizeMany(Flux<User> flux) {
        return flux.map(user -> {
            try{
               user = capitalizeUser(user);
            }catch(GetOutOfHereException goohe){
                goohe.printStackTrace();
            };
            return user;
        });
    }

    User capitalizeUser(User user) throws GetOutOfHereException {
        if (user.equals(User.SAUL)) {
            throw new GetOutOfHereException();
        }
        return new User(user.getUsername(), user.getFirstname(), user.getLastname());
    }

    protected final class GetOutOfHereException extends Exception {
        private static final long serialVersionUID = 0L;
    }

}
