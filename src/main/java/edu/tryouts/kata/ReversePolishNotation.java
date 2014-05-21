package edu.tryouts.kata;

import java.util.Arrays;
import java.util.Stack;
import java.util.function.BiFunction;

/**
 * Class ReversePolishNotation.
 *
 * @author Erwin Dupont
 * @since 2014-05-21
 */
public class ReversePolishNotation {
    public static Double calc(String input) {
        Stack<Double> numbers = new Stack<>();
        Arrays.asList(input.split(" ")).stream().forEach(number -> {
            switch (number) {
                case "+":
                    calcSign(numbers, (n1, n2) -> n2 + n1);
                    break;
                case "-":
                    calcSign(numbers, (n1, n2) -> n2 - n1);
                    break;
                case "*":
                    calcSign(numbers, (n1, n2) -> n2 * n1);
                    break;
                case "/":
                    calcSign(numbers, (n1, n2) -> n2 / n1);
                    break;
                default:
                    numbers.push(Double.parseDouble(number));
            }
        });
        return numbers.pop();
    }

    protected static Stack<Double> calcSign(Stack<Double> numbers, BiFunction<Double, Double, Double> operation) {
        numbers.push(operation.apply(numbers.pop(), numbers.pop()));
        return numbers;
    }
}
