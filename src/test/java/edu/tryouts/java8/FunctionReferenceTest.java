package edu.tryouts.java8;

import java.util.Arrays;
import java.util.List;

/**
 * Class FunctionReferenceTest.
 *
 * @author Erwin Dupont
 * @since 2013-12-05
 */
public class FunctionReferenceTest {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList(
            "Short", "Longer", "Very Long", "The Longest String of all"
        );

        int sum = strings.stream().mapToInt(CharSequence::length).sum();
        System.out.println("sum of string lengths = " + sum);

        List<? extends CharSequence> sequences = strings;

        sum = sequences.stream().mapToInt(CharSequence::length).sum();
        System.out.println("sum of sequence lengths = " + sum);

    }
}
