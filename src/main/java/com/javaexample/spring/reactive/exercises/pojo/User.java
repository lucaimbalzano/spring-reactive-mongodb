package com.javaexample.spring.reactive.exercises.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class User {
    //Final istances aimed to exercises
    public static final User SKYLER = new User("swhite", "Skyler", "White");
    public static final User JESSE = new User("jpinkman", "Jesse", "Pinkman");
    public static final User WALTER = new User("wwhite", "Walter", "White");
    public static final User SAUL = new User("sgoodman", "Saul", "Goodman");

    String surename;
    String firstname;
    String lastname;

    public User(String surename, String firstname, String lastname) {
        this.surename = surename;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public User(String u) {
        this.surename = u;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "surename='" + surename + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (!surename.equals(user.surename)) {
            return false;
        }
        if (!firstname.equals(user.firstname)) {
            return false;
        }
        return lastname.equals(user.lastname);

    }

    @Override
    public int hashCode() {
        int result = surename.hashCode();
        result = 31 * result + firstname.hashCode();
        result = 31 * result + lastname.hashCode();
        return result;
    }


}
