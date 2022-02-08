package com.javaexample.spring.reactive.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "test")
public class Person {
    @Id
    private String id;
    private String name;
    private Integer age;

    public Person(Integer id, String name, Integer age) {
        this.id = id.toString();
        this.name = name;
        this.age = age;
    }

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
