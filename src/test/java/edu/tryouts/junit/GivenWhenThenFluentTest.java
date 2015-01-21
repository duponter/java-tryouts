package edu.tryouts.junit;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;
import org.junit.runner.RunWith;

import static com.insightfullogic.lambdabehave.Suite.describe;

/**
 * Class GivenWhenThenFluentTest.
 *
 * @author Erwin Dupont
 * @since 2015-01-21
 */
@RunWith(JunitSuiteRunner.class)
public class GivenWhenThenFluentTest {
	static {
		describe("a cafe", it -> {
			Cafe cafe = new Cafe();
			it.should("never serve coffee it doesn't have", expect -> {
				Given:
				cafe.setCoffeesRemaining(1);
				When:
				cafe.serveCoffee();
				Then:
				expect.that(cafe.canServeCoffee()).is(false);
			});
		});
	}
}
