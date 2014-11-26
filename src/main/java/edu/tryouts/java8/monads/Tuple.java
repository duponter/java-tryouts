package edu.tryouts.java8.monads;

/**
 * Class Tuple.
 *
 * @author Erwin Dupont
 * @since 2014-11-26
 */
public class Tuple<A, B> {

	public final A _1;
	public final B _2;

	public Tuple(A a, B b) {
		_1 = a;
		_2 = b;
	}
}
