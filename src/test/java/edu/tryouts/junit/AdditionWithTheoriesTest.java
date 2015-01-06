package edu.tryouts.junit;

import org.junit.Assume;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

/**
 * Class AdditionWithTheoriesTest.
 *
 * @author Erwin Dupont
 * @since 2015-01-06
 */
@RunWith(Theories.class)
public class AdditionWithTheoriesTest {
	@DataPoints
	public static int[] integers() {
		return new int[]{
				-1, -10, -1234567, 1, 10, 1234567};
	}

	@Theory
	public void a_plus_b_is_greater_than_a_and_greater_than_b(Integer a, Integer b) {
		System.out.printf("Before Assume: a_plus_b_is_greater_than_a_and_greater_than_b with a=%d and b=%d%n", a, b);
		Assume.assumeTrue(a > 0 && b > 0);
		System.out.printf("After Assume: a_plus_b_is_greater_than_a_and_greater_than_b with a=%d and b=%d%n", a, b);
		assertTrue(a + b > a);
		assertTrue(a + b > b);
	}

	@Theory
	public void addition_is_commutative(Integer a, Integer b) {
		System.out.printf("addition_is_commutative with a=%d and b=%d%n", a, b);
		assertTrue(a + b == b + a);
	}
}
