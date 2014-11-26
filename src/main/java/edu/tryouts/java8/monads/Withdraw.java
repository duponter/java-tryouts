package edu.tryouts.java8.monads;

/**
 * Class Withdraw.
 *
 * @author Erwin Dupont
 * @since 2014-11-26
 */
public class Withdraw implements Input {

	private final int amount;

	public Withdraw(int amount) {
		super();
		this.amount = amount;
	}

	@Override
	public boolean isDeposit() {
		return false;
	}

	@Override
	public boolean isWithdraw() {
		return true;
	}

	@Override
	public int getAmount() {
		return this.amount;
	}
}
