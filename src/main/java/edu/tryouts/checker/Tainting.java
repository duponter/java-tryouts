package edu.tryouts.checker;

import org.checkerframework.checker.tainting.qual.PolyTainted;
import org.checkerframework.checker.tainting.qual.Tainted;
import org.checkerframework.checker.tainting.qual.Untainted;

/**
 * Class Tainting.
 *
 * @author Erwin Dupont
 * @since 2015-02-02
 */
public class Tainting {
	public static void main(String[] args) {
		process(parse(read())); // <-- doesn't compile, as process cannot accept tainted data
//		process(parse(sanitize(read())));
	}

	static @Tainted String read() {
		return "12345"; // pretend we've got this from the user
	}

	@SuppressWarnings("tainting")
	static @Untainted String sanitize(@Tainted String s) {
		if (s.length() > 10)
			throw new IllegalArgumentException("I don't wanna do that!");
		return (@Untainted String) s;
	}

	// doesn't change the tainted qualifier of the data
	@SuppressWarnings("tainting")
	static @PolyTainted int parse(@PolyTainted String s) {
		return (@PolyTainted int) Integer.parseInt(s); // apparently the JDK libraries aren't annotated with @PolyTainted
	}

	static void process(@Untainted int data) {
		System.out.println("--> " + data);
	}
}
