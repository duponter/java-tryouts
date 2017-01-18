package edu.tryouts.pbt;

import javaslang.CheckedFunction1;
import javaslang.test.Arbitrary;
import javaslang.test.CheckResult;
import javaslang.test.Property;

import org.junit.Test;

public class FizzBuzzTest {
    @Test
    public void every_third_element_starts_with_Fizz() {
        Arbitrary<Integer> multiplesOf3 = Arbitrary.integer()
                .filter(i -> i > 0)
                .filter(i -> i % 3 == 0);

        CheckedFunction1<Integer, Boolean> mustStartWithFizz = i ->
                FizzBuzz.fizzBuzz().get(i - 1).startsWith("Fizz");

        CheckResult result = Property
                .def("Every third element must start with Fizz")
                .forAll(multiplesOf3)
                .suchThat(mustStartWithFizz)
                .check(10_000, 1_000);

        result.assertIsSatisfied();
    }

    @Test
    public void every_fifth_element_ends_with_Buzz() {
        Arbitrary<Integer> multiplesOf5 = Arbitrary.integer()
                .filter(i -> i > 0)
                .filter(i -> i % 5 == 0);

        CheckedFunction1<Integer, Boolean> mustEndWithBuzz = i ->
                FizzBuzz.fizzBuzz().get(i - 1).endsWith("Buzz");

        Property.def("Every fifth element must end with Buzz")
                .forAll(multiplesOf5)
                .suchThat(mustEndWithBuzz)
                .check(10_000, 1_000)
                .assertIsSatisfied();
    }

    @Test
    public void every_non_fifth_and_non_third_element_is_a_number() {
        Arbitrary<Integer> nonFifthNonThird = Arbitrary.integer()
                .filter(i -> i > 0)
                .filter(i -> i % 5 != 0)
                .filter(i -> i % 3 != 0);

        CheckedFunction1<Integer, Boolean> mustBeANumber = i ->
                FizzBuzz.fizzBuzz().get(i - 1).equals(i.toString());

        Property.def("Non-fifth and non-third element must be a number")
                .forAll(nonFifthNonThird)
                .suchThat(mustBeANumber)
                .check(10_000, 1_000)
                .assertIsSatisfied();
    }
}