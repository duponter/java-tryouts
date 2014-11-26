package edu.tryouts.java8.monads;

import java.util.function.Function;

/**
 * Class Transition.
 *
 * @author Erwin Dupont
 * @since 2014-11-26
 */
@FunctionalInterface
public interface Transition<I, S> extends Function<StateTuple<I, S>, S> {
}
