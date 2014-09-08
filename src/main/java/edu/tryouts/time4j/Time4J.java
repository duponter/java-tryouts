package edu.tryouts.time4j;


import net.time4j.Moment;
import net.time4j.Month;
import net.time4j.PatternType;
import net.time4j.PlainDate;
import net.time4j.PlainTime;
import net.time4j.PlainTimestamp;
import net.time4j.SI;
import net.time4j.SystemClock;
import net.time4j.tz.olson.ASIA;
import net.time4j.tz.olson.EUROPE;

import static net.time4j.CalendarUnit.MONTHS;
import static net.time4j.PlainDate.DAY_OF_MONTH;
import static net.time4j.PlainDate.DAY_OF_WEEK;
import static net.time4j.PlainTime.MINUTE_OF_HOUR;
import static net.time4j.Weekday.WEDNESDAY;

/**
 * Class Time4J.
 *
 * @author Erwin Dupont
 * @since 2014-09-08
 */
public class Time4J {
	/**
	 * Command line application execution.
	 *
	 * @param args Array of Strings referencing the command line arguments.
	 */
	public static void main(String[] args) {
		// What is the last day of overnext month?
		System.out.println(
				SystemClock.inLocalView().today().plus(2, MONTHS).with(DAY_OF_MONTH.maximized())
		);

		// When is next wednesday?
		PlainDate today = SystemClock.inLocalView().today();
		PlainDate nextWednesday = today.with(DAY_OF_WEEK.setToNext(WEDNESDAY));
		System.out.println(nextWednesday);

		// What is the current wall time rounded down to multiples of 5 minutes?
		PlainTimestamp currentLocalTimestamp = SystemClock.inZonalView(EUROPE.BERLIN).now();
		PlainTime roundedTime =
				currentLocalTimestamp.getWallTime() // T22:06:52,688
						.with(MINUTE_OF_HOUR.atFloor()) // T22:06
						.with(MINUTE_OF_HOUR.roundedDown(5)); // T22:05
		System.out.println("Rounded wall time: " + roundedTime);

		// How does last UTC-leapsecond look like in Japan?
		Moment leapsecondUTC =
				PlainDate.of(2012, Month.JUNE, 30)
						.at(PlainTime.midnightAtEndOfDay()) // 2012-06-30T24 => 2012-07-01T00
						.atUTC().minus(1, SI.SECONDS);
		System.out.println(leapsecondUTC); // 2012-06-30T23:59:60,000000000Z

		System.out.println(
				"Japan-Time: "
						+ Moment.localFormatter("uuuu-MM-dd'T'HH:mm:ssXX", PatternType.CLDR)
						.withTimezone(ASIA.TOKYO)
						.format(leapsecondUTC)
		); // Japan-Time: 2012-07-01T08:59:60+0900
	}
}
