package edu.tryouts.junit;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import org.junit.Rule;
import org.junit.experimental.theories.DataPoint;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;
import static org.junit.Assume.assumeTrue;

/**
 * Class DistanceCalculatorTest.
 *
 * @author Erwin Dupont
 * @since 2014-10-14
 */
@RunWith(Theories.class)
public class DistanceCalculatorTest {

	@DataPoint
	static public LatLng es_1 = new LatLng(49.0060285, 8.4006111);
	@DataPoint
	static public LatLng es_2 = new LatLng(49.0060285, 8.4005789);
	@DataPoint
	static public LatLng es_3 = new LatLng(49.0060056, 8.4005611);

	@DataPoint
	public static LatLng nullPoint = null;


	@DataPoint
	static public LatLng north_pole_1 = new LatLng(90, 10);
	@DataPoint
	static public LatLng north_pole_2 = new LatLng(90, 20);

	@Rule
	public ExpectedException distanceCalculatorException = ExpectedException.none();

	@Theory(nullsAccepted = false)
	public void distanceIsCommutative(LatLng p1, LatLng p2) {
		assertThat(LatLngTool.distanceInRadians(p1, p2), is(LatLngTool.distanceInRadians(p2, p1)));
	}

	@Theory(nullsAccepted = false)
	public void distanceIsPositiveSemidefinite(LatLng p1, LatLng p2) {
		assertThat(LatLngTool.distanceInRadians(p1, p2), is(greaterThanOrEqualTo(0.)));
	}

	@Theory(nullsAccepted = false)
	public void distanceFulfillsTriangleEquality(LatLng p1, LatLng p2, LatLng p3) {
		assertThat(LatLngTool.distanceInRadians(p1, p2) + LatLngTool.distanceInRadians(p2, p3),
				is(greaterThanOrEqualTo(LatLngTool.distanceInRadians(p1, p3))));
	}

	@Theory
	public void distanceToNullNotDefined(LatLng p1, LatLng p2) throws Exception {
		assumeTrue(p1 == null || p2 == null);
		distanceCalculatorException.expect(NullPointerException.class);
		LatLngTool.distanceInRadians(p1, p2);
	}

	@Theory(nullsAccepted = false)
	public void runningTowardsThePole(LatLng pt) {

		// this test does not work at the poles
		assumeThat(pt.getLatitude(), is(allOf(greaterThan(-89.), lessThan(89.))));
	}
}
