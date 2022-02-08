package com.javaexample.spring.reactive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collation = "test")
public class PersonDTO {

    private String id;
    private String name;
    private Integer age;

    public PersonDTO(Integer id, String name, Integer age) {
        this.id = id.toString();
        this.name = name;
        this.age = age;
    }

    public PersonDTO(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
