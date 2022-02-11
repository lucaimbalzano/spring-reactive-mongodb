package com.javaexample.spring.reactive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private String id;
    private String firstname;
    public String surname;
    public Date birth;
    private Integer age;
    private AddressDTO addressDTO;

}
