package edu.tryouts.java8;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@FunctionalInterface
public interface PersonBuilder {
	Person build();

	static PersonBuilder newPerson() {
		PersonBuilder builder = Person::new;
		// ability set defaults
		return builder.with(person -> person.setNationality("Belgian"));
	}

	default PersonBuilder with(Consumer<? super Person> modifier) {
		return () -> {
			Person instance = this.build();
			modifier.accept(instance);
			return instance;
		};
	}

	default <U> PersonBuilder with(BiConsumer<? super Person, U> modifier, U value) {
		return this.with(instance -> modifier.accept(instance, value));
	}

	default PersonBuilder withName(String name) {
		return this.with(Person::setName, name);
	}

	default PersonBuilder withAge(int age) {
		return this.with(Person::setAge, age);
	}

	default PersonBuilder withDateOfBirth(LocalDate dateOfBirth) {
		return this.with(Person::born, dateOfBirth)
				.withAge(Period.between(dateOfBirth, LocalDate.now()).getYears());
	}

	default PersonBuilder male() {
		return this.with(Person::male);
	}

	default PersonBuilder female() {
		return this.with(Person::female);
	}
}
