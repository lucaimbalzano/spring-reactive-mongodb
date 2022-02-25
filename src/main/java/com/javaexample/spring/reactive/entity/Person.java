package com.javaexample.spring.reactive.entity;

import com.javaexample.spring.reactive.dto.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "person")
public class Person {
    @Id
    private String id;
    private String firstname;
    public String surename;
    public Date birth;
    private Integer age;
    private AddressDTO addressDTO;
}
