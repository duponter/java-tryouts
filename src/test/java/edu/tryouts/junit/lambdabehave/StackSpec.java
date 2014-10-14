package edu.tryouts.junit.lambdabehave;

import java.util.Stack;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;
import org.junit.runner.RunWith;

import static com.insightfullogic.lambdabehave.Suite.describe;

/**
 * Class LambdaBehave.
 *
 * @author Erwin Dupont
 * @since 2014-10-14
 */
@RunWith(JunitSuiteRunner.class)
public class StackSpec {
	static {
		Stack<Integer> stack = new Stack<>();
		describe("a stack", it -> {
			it.isSetupWith(stack::clear);
			it.isConcludedWith(stack::clear);
			it.should("be empty when created", expect -> {
				expect.that(stack).isEmpty();
			});
			it.should("push new elements onto the top of the stack", expect -> {
				expect.that(stack).isEmpty();
				stack.push(1);
				expect.that(stack.get(0)).isEqualTo(1);
			});
			it.should("pop the last element pushed onto the stack", expect -> {
				expect.that(stack).isEmpty();
				stack.push(2);
				stack.push(1);
				expect.that(stack.pop()).isEqualTo(1);
			});
		});
	}
}