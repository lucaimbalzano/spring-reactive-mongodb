package com.javaexample.spring.reactive.transformer;


import com.javaexample.spring.reactive.dto.PersonDTO;
import com.javaexample.spring.reactive.entity.Person;
import org.springframework.beans.BeanUtils;

public class PersonTransformer {
    public static PersonDTO entityToDTO(Person person) {
        PersonDTO personDTO = new PersonDTO();
        BeanUtils.copyProperties(person, personDTO);
        return personDTO;
    }

    public static Person dtoToEntity(PersonDTO personDTO) {
        Person person = new Person();
        BeanUtils.copyProperties(personDTO, person);
        return person;
    }
}
