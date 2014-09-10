package edu.tryouts.java8;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

/**
 * Class PersonTest.
 *
 * @author Erwin Dupont
 * @since 2013-11-15
 */
public class PersonTest {
    @Test
    public void streams() {
        List<Person> persons = Arrays.asList(
                new Person().named("Erwin").born(LocalDate.of(1977, Month.AUGUST, 25)).male(),
                new Person().named("Pascale").born(LocalDate.of(1984, Month.JANUARY, 31)).female(),
                new Person().named("Lucas").born(LocalDate.of(2010, Month.JUNE, 10)).male(),
                new Person().born(LocalDate.of(2014, Month.FEBRUARY, 25)).female()
        );

        System.out.println("All persons");
        persons.stream().forEach(Person::printPerson);


        System.out.println("\nAll names");
        List<String> list = persons.stream().map(Named::getName).collect(Collectors.toList());

        System.out.println("\nUnborn persons");
        persons.stream()
                .filter(person -> person.getBirthday().isAfter(LocalDate.now()))
                .forEach(Person::printPerson);

        System.out.println("\nGrouped by Gender");
        persons.stream()
                .collect(Collectors.groupingBy(Person::getGender))
                .forEach((gender, children) -> {
                    String personString = Arrays.toString(children.stream().map(Person::getName).collect(Collectors.toList()).toArray());
                    System.out.println(String.format("%s %sS: %s", children.size(), gender, personString));
                });
    }

    @Test
    public void conditionalOperators() {
        BooleanSupplier supplier1 = () -> {
            System.out.println("Supplier 1 called");
            return true;
        };
        BooleanSupplier supplier2 = () -> {
            System.out.println("Supplier 2 called");
            return false;
        };

        System.out.println("true && false = " + (supplier1.getAsBoolean() && supplier2.getAsBoolean()));
        System.out.println("false && true = " + (supplier2.getAsBoolean() && supplier1.getAsBoolean()));
        System.out.println("true & false = " + (supplier1.getAsBoolean() & supplier2.getAsBoolean()));
        System.out.println("false & true = " + (supplier2.getAsBoolean() & supplier1.getAsBoolean()));

        System.out.println("true || false = " + (supplier1.getAsBoolean() || supplier2.getAsBoolean()));
        System.out.println("false || true = " + (supplier2.getAsBoolean() || supplier1.getAsBoolean()));
        System.out.println("true | false = " + (supplier1.getAsBoolean() | supplier2.getAsBoolean()));
        System.out.println("false | true = " + (supplier2.getAsBoolean() | supplier1.getAsBoolean()));
    }
}
