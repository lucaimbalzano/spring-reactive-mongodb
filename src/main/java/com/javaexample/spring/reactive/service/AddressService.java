package com.javaexample.spring.reactive.service;

import com.javaexample.spring.reactive.dto.AddressDTO;
import com.javaexample.spring.reactive.dto.PersonDTO;
import com.javaexample.spring.reactive.entity.Address;
import com.javaexample.spring.reactive.repository.AddressRepository;
import com.javaexample.spring.reactive.transformer.AddressTransformer;
import com.javaexample.spring.reactive.transformer.PersonTransformer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public Flux<AddressDTO> getAllAddress(){
        log.info(" +++ @Service getAllAddress() called");
            return addressRepository.findAll().map(AddressTransformer::entityToDTO);
    }

    public Mono<AddressDTO> getAddressById(String id){
        log.info(" +++ @Service getAddressById(String s) called");
        return addressRepository.findById(id).map(AddressTransformer::entityToDTO);
    }
//TODO TOFIX
    public  Flux<AddressDTO> getAllAddressesId(){
        log.info(" +++ @Service getAllAddressesId() called");
        List<String> dtoList = null;
        Flux<AddressDTO> dtoFlux = null;
        Mono<List<Address>> monoListAddress = addressRepository.findAll().collectList();
        dtoFlux.just(monoListAddress).merge();
        return dtoFlux;

    }

    public List<AddressDTO> getAddressConvertedFromFluxToArrayListAddressDTO(){
        log.info(" +++ @Service getAddressConvertedInArrayList() called");
        List<AddressDTO> addressDTOList = (List<AddressDTO>) addressRepository.findAll()
                .flatMap(address -> {  return Mono.just(AddressTransformer.entityToDTO(address));});
        return addressDTOList;
    }
}
