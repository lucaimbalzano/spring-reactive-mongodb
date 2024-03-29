package com.javaexample.spring.reactive.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {
    private String id;
    private String street;
    private String streetNumber;
    private Integer postalCode;
    private String city;
    private String countryCode;

    public AddressDTO(String idPassed) {
        id = idPassed;
    }
}
