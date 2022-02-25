package com.javaexample.spring.reactive.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private String id;
    private String firstname;
    public String surename;
    public Date birth;
    private Integer age;
    private AddressDTO addressDTO;

}
