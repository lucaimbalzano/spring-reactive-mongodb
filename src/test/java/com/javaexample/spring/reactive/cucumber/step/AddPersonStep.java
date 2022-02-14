package com.javaexample.spring.reactive.cucumber.step;

import com.javaexample.spring.reactive.entity.Person;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Slf4j
@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
public class AddPersonStep {

    @LocalServerPort
    private int port;
    private RestTemplate restTemplate = new RestTemplate();
    private String postUrl = "http://localhost";
    private String baseUri = "/api/person";
    private String personId = "";

    @Given("i can create new person")
    public void i_can_create_new_person() {
        String url = postUrl + ":" + port + baseUri + "/allpeople";
        List<Person> allPeople = restTemplate.getForObject(url, List.class);
        log.info(" ### THEN GIVEN (1):  " + allPeople.toString());
        assertTrue(!allPeople.isEmpty());
    }

    @Given("i sending person with values {string} and {string} not null, the {int} checks")
    public void i_sending_person_with_values_and_not_null_the_checks(String username, String surname, int age) {
        String url = postUrl + ":" + port + baseUri + "/addperson";
        Person person = new Person();
        person.setFirstname(username);
        Person personTemplate = restTemplate.postForObject(url, person, Person.class);
        personId = person.getId();
        log.info(" ### THEN GIVEN (2):  " + person.toString());
        assertNotNull(person);
    }

    @Then("I should to se new person")
    public void i_should_to_se_new_person() {
        String url = postUrl + ":" + port + baseUri + "/getperson/" + personId;
        Person person = restTemplate.getForObject(url, Person.class);
        log.info(" ### THEN STEP:  " + person.toString());
        assertNotNull(person);
    }
}