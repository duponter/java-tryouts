package edu.tryouts.java8.libs;

import org.jooq.lambda.Seq;

import static org.jooq.lambda.tuple.Tuple.tuple;

/**
 * Class Jool.
 *
 * @author Erwin Dupont
 * @since 2014-10-13
 */
public class Jool {
	/**
	 * Command line application execution.
	 *
	 * @param args Array of Strings referencing the command line arguments.
	 */
	public static void main(String[] args) {
// (1, 2, 3, 4, 5, 6)
		Seq.of(1, 2, 3).concat(Seq.of(4, 5, 6)).forEach(System.out::println);

// (tuple(1, "a"), tuple(2, "b"), tuple(3, "c"))
		Seq.of(1, 2, 3).zip(Seq.of("a", "b", "c")).forEach(System.out::println);

// ("1:a", "2:b", "3:c")
		Seq.of(1, 2, 3).zip(Seq.of("a", "b", "c"), (x, y) -> x + ":" + y).forEach(System.out::println);

// tuple((1, 2, 3), (a, b, c))
		Seq.unzip(Seq.of(tuple(1, "a"), tuple(2, "b"), tuple(3, "c"))).forEach(System.out::println);

// (tuple("a", 0), tuple("b", 1), tuple("c", 2))
		Seq.of("a", "b", "c").zipWithIndex().forEach(System.out::println);

// (3, 4, 5)
		Seq.of(1, 2, 3, 4, 5).skipWhile(i -> i < 3).forEach(System.out::println);

// (3, 4, 5)
		Seq.of(1, 2, 3, 4, 5).skipUntil(i -> i == 3).forEach(System.out::println);

// (1, 2)
		Seq.of(1, 2, 3, 4, 5).limitWhile(i -> i < 3).forEach(System.out::println);

// (1, 2)
		Seq.of(1, 2, 3, 4, 5).limitUntil(i -> i == 3).forEach(System.out::println);

// (1, 0, 2, 0, 3, 0, 4)
//TODO 0.9.3		Seq.of(1, 2, 3, 4).intersperse(0);

// "abc"
		System.out.println(Seq.of("a", "b", "c").foldLeft("", (u, t) -> t + u));

// "cba"
		System.out.println(Seq.of("a", "b", "c").foldRight("", (t, u) -> t + u));

// tuple((1, 2, 3), (1, 2, 3))
		Seq.of(1, 2, 3).duplicate().forEach(System.out::println);

// tuple((1, 3, 5), (2, 4, 6))
		Seq.of(1, 2, 3, 4, 5, 6).partition(i -> i % 2 != 0).forEach(System.out::println);

// tuple((1, 2), (3, 4, 5))
		Seq.of(1, 2, 3, 4, 5).splitAt(2).forEach(System.out::println);

// tuple(1, (2, 3, 4, 5))
//TODO 0.9.3		Seq.of(1, 2, 3, 4, 5).splitAtHead();

// (2, 3)
		Seq.of(1, 2, 3, 4, 5).slice(1, 3).forEach(System.out::println);

//		Arrays.stream(dir.listFiles()).forEach(
//				Unchecked.consumer(file -> {
//					System.out.println(file.getCanonicalPath());
//				})
//		);
	}
}
