package edu.tryouts.java8;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface Builder<T> {
	void accept(T instance);

	static <T> Builder<T> of(Class<T> type) {
		return instance -> {};
	}

	default Builder<T> with(Consumer<T> modifier) {
		return instance -> {
			this.accept(instance);
			modifier.accept(instance);
		};
	}

	default <U> Builder<T> with(BiConsumer<T, U> modifier, U value) {
		return this.with(instance -> modifier.accept(instance, value));
	}

	default T build(Supplier<? extends T> supplier) {
		T instance = supplier.get();
		this.accept(instance);
		return instance;
	}
}
