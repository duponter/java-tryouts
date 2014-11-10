package edu.tryouts.java8;

import java.util.List;

import org.junit.Test;

import static edu.tryouts.java8.PeopleStats.getStats;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Class PeopleStatsTest.
 *
 * @author Erwin Dupont
 * @since 2014-11-10
 */
public class PeopleStatsTest {
	Person sara = new Person("Sara", 4);
	Person viktor = new Person("Viktor", 40);
	Person eva = new Person("Eva", 42);
	List<Person> collection = asList(sara, eva, viktor);

	@Test
	public void getStatsShouldReturnAverageAge() {
		assertThat(getStats(collection).getAverage())
				.isEqualTo((double) (4 + 40 + 42) / 3);
	}

	@Test
	public void getStatsShouldReturnNumberOfPeople() {
		assertThat(getStats(collection).getCount())
				.isEqualTo(3);
	}

	@Test
	public void getStatsShouldReturnMaximumAge() {
		assertThat(getStats(collection).getMax())
				.isEqualTo(42);
	}

	@Test
	public void getStatsShouldReturnMinimumAge() {
		assertThat(getStats(collection).getMin())
				.isEqualTo(4);
	}

	@Test
	public void getStatsShouldReturnSumOfAllAges() {
		assertThat(getStats(collection).getSum())
				.isEqualTo(40 + 42 + 4);
	}
}