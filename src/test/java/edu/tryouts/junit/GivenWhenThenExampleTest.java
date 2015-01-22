package edu.tryouts.junit;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * Class GivenWhenThenExampleTest.
 *
 * @author Erwin Dupont
 * @since 2015-01-21
 */
public class GivenWhenThenExampleTest {
	Cafe cafe;

	@Before
	public void setup() {
		cafe = new Cafe();
	}

	@Test
	public void cafeShouldNeverServeCoffeeItDoesntHave() {
		Given:
		cafe.setCoffeesRemaining(1);
		When:
		cafe.serveCoffee();
		Then:
		assertFalse(cafe.canServeCoffee());
	}
}