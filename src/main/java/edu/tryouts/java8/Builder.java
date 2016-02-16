package edu.tryouts.java8;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface Builder<T> {
	T build();

	static <T> Builder<T> of(Supplier<? extends T> supplier) {
		return supplier::get;
	}

	default Builder<T> with(Consumer<T> modifier) {
		return () -> {
			T instance = this.build();
			modifier.accept(instance);
			return instance;
		};
	}

	default <U> Builder<T> with(BiConsumer<T, U> modifier, U value) {
		return this.with(instance -> modifier.accept(instance, value));
	}
}
