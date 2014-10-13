package edu.tryouts.java8.libs;

import fj.data.Array;
import fj.data.List;
import fj.data.Option;

import static fj.Show.arrayShow;
import static fj.Show.intShow;
import static fj.Show.listShow;
import static fj.Show.optionShow;
import static fj.data.Array.array;
import static fj.data.List.list;
import static fj.data.Option.none;
import static fj.data.Option.some;
import static fj.data.Stream.fromString;

/**
 * Class ListMapExample.
 *
 * @author Erwin Dupont
 * @since 2014-10-13
 */
public final class FunctionalJava {
	/**
	 * Command line application execution.
	 *
	 * @param args Array of Strings referencing the command line arguments.
	 */
	public static void main(String[] args) {
		array_exists();
		array_filter();
		array_foldleft();
		array_forall();
		array_map();
		list_map();
		option_bind();
		option_filter();
		option_map();
	}

	public static void array_exists() {
		final Array<String> a = array("Hello", "There", "what", "DAY", "iS", "iT");
		final boolean b = a.exists(s -> fromString(s).forall(Character::isLowerCase));
		System.out.println(b); // true ("what" provides the only example; try removing it)
	}


	public static void array_filter() {
		final Array<Integer> a = array(97, 44, 67, 3, 22, 90, 1, 77, 98, 1078, 6, 64, 6, 79, 42);
		final Array<Integer> b = a.filter(i -> i % 2 == 0);
		arrayShow(intShow).println(b); // {44,22,90,98,1078,6,64,6,42}
	}


	public static void array_foldleft() {
		final Array<Integer> a = array(97, 44, 67, 3, 22, 90, 1, 77, 98, 1078, 6, 64, 6, 79, 42);
		final int b = a.<Integer>foldLeft(i -> (j -> i + j), 0);
		System.out.println(b); // 1774
	}

	public static void array_forall() {
		final Array<String> a = array("hello", "There", "what", "day", "is", "it");
		final boolean b = a.forall(s -> fromString(s).forall(Character::isLowerCase));
		System.out.println(b); // false ("There" is a counter-example; try removing it)
	}

	public static void array_map() {
		final Array<Integer> a = array(1, 2, 3);
		final Array<Integer> b = a.map(i -> i + 42);
		arrayShow(intShow).println(b); // {43,44,45}
	}

	public static void list_map() {
		final List<Integer> a = list(1, 2, 3);
		final List<Integer> b = a.map(i -> i + 42);
		listShow(intShow).println(b); // [43,44,45]
	}

	public static void option_bind() {
		final Option<Integer> o1 = some(7);
		final Option<Integer> o2 = some(8);
		final Option<Integer> o3 = none();
		final Option<Integer> p1 = o1.bind(i -> i % 2 == 0 ? some(i * 3) : Option.<Integer>none());
		final Option<Integer> p2 = o2.bind(i -> i % 2 == 0 ? some(i * 3) : Option.<Integer>none());
		final Option<Integer> p3 = o3.bind(i -> i % 2 == 0 ? some(i * 3) : Option.<Integer>none());
		optionShow(intShow).println(p1); // None
		optionShow(intShow).println(p2); // Some(24)
		optionShow(intShow).println(p3); // None
	}

	public static void option_filter() {
		final Option<Integer> o1 = some(7);
		final Option<Integer> o2 = none();
		final Option<Integer> o3 = some(8);
		final Option<Integer> p1 = o1.filter(i -> i % 2 == 0);
		final Option<Integer> p2 = o2.filter(i -> i % 2 == 0);
		final Option<Integer> p3 = o3.filter(i -> i % 2 == 0);
		optionShow(intShow).println(p1); // None
		optionShow(intShow).println(p2); // None
		optionShow(intShow).println(p3); // Some(8)
	}

	public static void option_map() {
		final Option<Integer> o1 = some(7);
		final Option<Integer> o2 = none();
		final Option<Integer> p1 = o1.map(i -> i + 42);
		final Option<Integer> p2 = o2.map(i -> i + 42);
		optionShow(intShow).println(p1); // Some(49)
		optionShow(intShow).println(p2); // None
	}
}
