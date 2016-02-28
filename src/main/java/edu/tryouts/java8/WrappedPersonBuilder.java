package edu.tryouts.java8;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.function.Consumer;

public class WrappedPersonBuilder {
	private Builder<Person> builder = Builder.of(Person::new);

	public WrappedPersonBuilder withName(String name) {
		builder = builder.with(Person::setName, name);
		return this;
	}

	public WrappedPersonBuilder withAge(int age) {
		builder = builder.with(Person::setAge, age);
		return this;
	}

	public WrappedPersonBuilder withDateOfBirth(LocalDate dateOfBirth) {
		builder = builder.with(Person::born, dateOfBirth);
		return this.withAge(Period.between(dateOfBirth, LocalDate.now()).getYears());
	}

	public WrappedPersonBuilder male() {
		builder = builder.with(Person::male);
		return this;
	}

	public WrappedPersonBuilder female() {
		builder = builder.with(Person::female);
		return this;
	}

	public Person build() {
		return builder.build();
	}

	/**
	 * Command line application execution.
	 *
	 * @param args Array of Strings referencing the command line arguments.
	 */
	public static void main(String[] args) {
		System.out.println("Printing persons from standalone builder");
		standalone();

		System.out.println("Printing persons from wrapped builder");
		wrapped();

		System.out.println("Printing persons from extended builder");
		extended();
	}

	private static void standalone() {
		Person lucas = Builder.of(Person::new)
				.with(Person::setAge, 5)
				.with(Person::setName, "Lucas")
				.with(Person::male)
				.build();

		lucas.printPerson();

		Consumer<Object> objectConsumer = obj -> {
		};
		Builder.of(Person::new)
				.with(Person::female)
				.with(p -> p.named("Clara"))
				.with(p -> p.born(LocalDate.of(2014, Month.FEBRUARY, 13)))
				.with(objectConsumer)
				.build()
				.printPerson();

	}

	private static void wrapped() {
		Person lucas = new WrappedPersonBuilder()
				.withAge(5)
				.withName("Lucas")
				.male()
				.build();
		lucas.printPerson();

		new WrappedPersonBuilder()
				.female()
				.withName("Clara")
				.withDateOfBirth(LocalDate.of(2014, Month.FEBRUARY, 13))
				.build()
				.printPerson();
	}

	private static void extended() {
		Person lucas = PersonBuilder.newPerson()
				.withAge(5)
				.withName("Lucas")
				.male()
				.build();
		lucas.printPerson();

		PersonBuilder.newPerson()
				.female()
				.withName("Clara")
				.withDateOfBirth(LocalDate.of(2014, Month.FEBRUARY, 13))
				.build()
				.printPerson();
	}
}
