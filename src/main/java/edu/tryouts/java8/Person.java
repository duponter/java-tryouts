package edu.tryouts.java8;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.Period;

/**
 * Class Person.
 *
 * @author Erwin Dupont
 * @since 2013-11-15
 */
public class Person implements Named {

    public enum Sex {
        MALE, FEMALE
    }

    private String name;
    private LocalDate birthday;
    private Sex gender;
    private String emailAddress;

    public String getName() {
        return name;
    }

    public Person named(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Person born(LocalDate birthday) {
        this.birthday = birthday;
        return this;
    }

    public Period getAge() {
        return Period.between(this.getBirthday(), LocalDate.now());
    }

    public Sex getGender() {
        return gender;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
    }

    public Person male() {
        this.setGender(Sex.MALE);
        return this;
    }

    public Person female() {
        this.setGender(Sex.FEMALE);
        return this;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void printPerson() {
        System.out.println(String.format("%s (%s - Aged: %s)", StringUtils.defaultString(this.name, "???"), this.gender, this.getAge()));
    }
}
