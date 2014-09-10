package edu.tryouts.java8;

import java.math.BigInteger;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Class Fibonacci.
 *
 * @author Erwin Dupont
 * @since 2014-09-10
 */
public class Fibonacci {

	public static void main(String[] args) {

		Pair<BigInteger, BigInteger> seed = Pair.of(BigInteger.ONE, BigInteger.ONE);

		UnaryOperator<Pair<BigInteger, BigInteger>> f = pair -> Pair.of(pair.getRight(), pair.getLeft().add(pair.getRight()));

		long number = 1000;
		Stream<BigInteger> fiboStream = Stream.iterate(seed, f).map(Pair::getLeft).limit(number);

		String result = fiboStream.map(BigInteger::toString).collect(Collectors.joining(", "));
		System.out.println(result);
	}
}
