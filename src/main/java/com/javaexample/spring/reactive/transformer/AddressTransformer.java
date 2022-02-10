package com.javaexample.spring.reactive.transformer;

import com.javaexample.spring.reactive.dto.AddressDTO;
import com.javaexample.spring.reactive.entity.Address;
import org.springframework.beans.BeanUtils;

public class AddressTransformer {

    public static Address dtoToEntity(AddressDTO addressDTO){
        Address address = new Address();
        if(addressDTO!=null){
            BeanUtils.copyProperties(addressDTO,address);
        }
        return address;
    }

    public static AddressDTO entityToDTO(Address address){
        AddressDTO addressDTO = new AddressDTO();
        if(address!=null){
            BeanUtils.copyProperties(address,addressDTO);
        }
        return addressDTO;
    }
}
