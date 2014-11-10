package edu.tryouts.junit;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Class TheoriesExample.
 *
 * @author Erwin Dupont
 * @since 2014-10-14
 */
@RunWith(Theories.class)
public class TheoriesExample {
	/*
	* 	Our test	data are	stored in	an array	of Oligarch	objects.
	* 	Since this	is a	method like	any other, you	can generate	data
	*	dynamically or	fetch it	from an	external source, if needed.
	*/
	@DataPoints
	public static Oligarch[] data() {
		return new Oligarch[]{
				new Oligarch("Monty Burns"),
				new Oligarch("Don Geiss"),
				new Oligarch("Arthur Jensen")
		};
	}

	/*
	*	This theory	confirms that	all oligarchs	have yachts	.
	*/
	@Theory
	public void theoryOligarchsHaveYachts(Oligarch suit) {
		assertThat(suit.getVehicles(), hasItem("yacht"));
	}

	/*
	*	This theory	confirms that	all oligarchs	have hearts	of darkest	black.
	*/
	@Theory
	public void theoryOligarchsAreEvil(Oligarch suit) {
		assertThat(suit.getSoul().getOwner().getName(), is("The Devil"));
	}
}