package com.javaexample.spring.reactive.controller;


import com.javaexample.spring.reactive.dto.AddressDTO;
import com.javaexample.spring.reactive.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/address")
@Slf4j
public class ReactiveAddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping( value = "/getaddress/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<AddressDTO> getAddressById(@PathVariable String id){
        return addressService.getAddressById(id);
    }

    @GetMapping("/getall")//( value = "/getall")//, produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<AddressDTO> getAll(){
        return addressService.getAllAddress();
    }

    @GetMapping("/getallid")// ,produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<AddressDTO> getallid(){
        return addressService.getAllAddressesId();
    }

}
