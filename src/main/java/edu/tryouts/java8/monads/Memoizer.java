package edu.tryouts.java8.monads;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/**
 * Class Memoizer.
 *
 * @author Erwin Dupont
 * @since 2014-11-26
 */
public class Memoizer {
	private static <T, U> Function<T, U> doMemoize(final Function<T, U> function) {
		Map<T, U> cache = new ConcurrentHashMap<>();
		return input -> cache.computeIfAbsent(input, function::apply);
	}

	public static <T, U> Function<T, U> memoize(final Function<T, U> function) {
		return doMemoize(function);
	}
}
