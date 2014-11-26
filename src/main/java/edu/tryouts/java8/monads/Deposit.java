package edu.tryouts.java8.monads;

/**
 * Class Deposit.
 *
 * @author Erwin Dupont
 * @since 2014-11-26
 */
public class Deposit implements Input {

	private final int amount;

	public Deposit(int amount) {
		super();
		this.amount = amount;
	}

	@Override
	public boolean isDeposit() {
		return true;
	}

	@Override
	public boolean isWithdraw() {
		return false;
	}

	@Override
	public int getAmount() {
		return this.amount;
	}
}