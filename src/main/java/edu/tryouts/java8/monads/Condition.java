package edu.tryouts.java8.monads;

import java.util.function.Predicate;

/**
 * Class Condition.
 *
 * @author Erwin Dupont
 * @since 2014-11-26
 */
@FunctionalInterface
public interface Condition<I, S> extends Predicate<StateTuple<I, S>> {
}