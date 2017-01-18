package edu.tryouts.pbt;

import javaslang.collection.Stream;

public class FizzBuzz {
    public static Stream<String> fizzBuzz() {
        return Stream.from(1).map(i -> {
            boolean divBy3 = i % 3 == 0;
            boolean divBy5 = i % 5 == 0;

            return divBy3 && divBy5 ? "FizzBuzz" :
                    divBy3 ? "Fizz" :
                            divBy5 ? "Buzz" : i.toString();
        });
    }
}
