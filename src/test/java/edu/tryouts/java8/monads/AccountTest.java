package edu.tryouts.java8.monads;

import org.junit.Test;

/**
 * Class AccountTest.
 *
 * @author Erwin Dupont
 * @since 2014-11-26
 */
public class AccountTest {

	@Test
	public void testCreateMachine() {
		List<Input> inputs = List.apply(
				new Deposit(100),
				new Withdraw(50),
				new Withdraw(150),
				new Deposit(200),
				new Withdraw(150));

		StateMonad<Outcome, Outcome> state = Account.createMachine().process(inputs);
		Outcome outcome = state.eval(new Outcome(0, List.empty()));
		System.out.println("outcome = " + outcome.toString());
	}
}