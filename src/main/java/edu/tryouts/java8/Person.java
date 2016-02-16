package edu.tryouts.java8;

import java.time.LocalDate;
import java.time.Month;
import java.util.function.Consumer;

import org.apache.commons.lang3.StringUtils;

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
    private int age;
    private String nationality;

    public Person() {
    }

    public Person(final String nameValue, final int ageValue) {
        name = nameValue;
        age = ageValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person named(String name) {
        this.setName(name);
        return this;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public Person born(LocalDate birthday) {
        this.birthday = birthday;
        return this;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void printPerson() {
        System.out.println(String.format("%s (%s - %s - Aged: %s)", StringUtils.defaultString(this.name, "???"), this.gender, this.getBirthday(), this.getAge()));
    }


		/**
		 * Command line application execution.
		 *
		 * @param args Array of Strings referencing the command line arguments.
		 */
		public static void main(String[] args) {
			Builder.of(Person::new)
					.with(Person::setAge, 5)
					.with(Person::setName, "Lucas")
					.with(Person::male)
					.build()
					.printPerson();

			Consumer<Object> objectConsumer = obj -> {};
			Builder.of(Person::new)
					.with(Person::female)
					.with(p -> p.named("Clara"))
					.with(p -> p.born(LocalDate.of(2014, Month.FEBRUARY, 13)))
					.with(objectConsumer)
					.build()
					.printPerson();
		}
}
