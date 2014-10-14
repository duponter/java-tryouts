package edu.tryouts.junit.lambdabehave;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;
import org.junit.runner.RunWith;

import static com.insightfullogic.lambdabehave.Suite.describe;

/**
 * Class DataDrivenSpec.
 *
 * @author Erwin Dupont
 * @since 2014-10-14
 */
@RunWith(JunitSuiteRunner.class)
public class DataDrivenSpec {
	static {
		describe("a number", it -> {
			it.uses(2)
					.and(4)
					.toShow("%d is even if divisible by two", (expect, x) -> {
						expect.that(x % 2).is(0);
					});
		});
		describe("a pair of numbers", it -> {
			it.uses(2, 4)
					.and(4, 8)
					.toShow("%d / %d is two", (expect, x, y) -> {
						expect.that(y / x).is(2);
					});
		});
		describe("three numbers", it -> {
			it.uses(1, 2, 3)
					.and(4, 5, 6)
					.toShow("%d %d and %d are a sequence", (expect, x, y, z) -> {
						expect.that(x + 1).is(y)
								.and(y + 1).is(z);
					});
		});
	}}
