package edu.tryouts.javactic;

import com.github.javactic.Accumulation;
import com.github.javactic.Bad;
import com.github.javactic.Every;
import com.github.javactic.Good;
import com.github.javactic.One;
import com.github.javactic.Or;

import javaslang.collection.List;

public class Person {
	private final String name;
	private final int age;
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	@Override
	public String toString() {
		return "Person(" + name + "," + age + ")";
	}

	static Or<String, One<String>> parseName(String input) {
		String trimmed = input.trim();
		return (!trimmed.isEmpty())
				? Good.of(trimmed)
				: Bad.ofOneString("'{}' is not a valid name", input);
	}

	static Or<Integer, One<String>> parseAge(String input) {
		try {
			int age = Integer.parseInt(input.trim());
			return (age >= 0) ? Good.of(age) : Bad.ofOneString("'{}' is not a valid age", age);
		} catch (NumberFormatException e) {
			return Bad.ofOneString("'{}' is not a valid integer", input);
		}
	}

	static Or<Person, Every<String>> parsePerson(String inputName, String inputAge) {
		Or<String, One<String>> name = parseName(inputName);
		Or<Integer, One<String>> age = parseAge(inputAge);
		return Accumulation.withGood(name, age, (n, a) -> new Person(n, a));
	}

	public static void main(String[] args) {

		List<String> list = List.of("29", "30", "31");
		System.out.println(Accumulation.validatedBy(list, Person::parseAge, List.collector()));
		// Good(List(29, 30, 31))

		List<String> list2 = List.of("29", "-30", "31");
		System.out.println(Accumulation.validatedBy(list2, Person::parseAge, List.collector()));
		// Bad(One("-30" is not a valid age))

		List<String> list3 = List.of("29", "-30", "-31");
		System.out.println(Accumulation.validatedBy(list3, Person::parseAge, List.collector()));
// Bad(Many("-30" is not a valid age, "-31" is not a valid age))
	}
}
