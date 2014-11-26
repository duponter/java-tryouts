package edu.tryouts.java8.monads;

/**
 * Class Input.
 *
 * @author Erwin Dupont
 * @since 2014-11-26
 */
public interface Input {
	boolean isDeposit();

	boolean isWithdraw();

	int getAmount();
}