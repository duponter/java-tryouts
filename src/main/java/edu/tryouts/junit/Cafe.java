package edu.tryouts.junit;

/**
 * Class Cafe.
 *
 * @author Erwin Dupont
 * @since 2015-01-21
 */
public class Cafe {
	private int coffeesRemaining;

	public Cafe() {
		coffeesRemaining = 0;
	}

	public void setCoffeesRemaining(int coffeesRemaining) {
		this.coffeesRemaining = coffeesRemaining;
	}

	public void serveCoffee() {
		coffeesRemaining--;
	}

	public boolean canServeCoffee() {
		return coffeesRemaining > 0;
	}
}