package edu.tryouts.java8.monads;

import java.util.Objects;

/**
 * Class Tuple.
 *
 * @author Erwin Dupont
 * @since 2014-11-26
 */
public class StateTuple<A, S> {

	public final A value;
	public final S state;

	public StateTuple(A a, S s) {
		value = Objects.requireNonNull(a);
		state = Objects.requireNonNull(s);
	}
}