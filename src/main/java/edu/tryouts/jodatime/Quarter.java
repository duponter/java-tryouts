package edu.tryouts.jodatime;

import org.joda.time.LocalDate;
import org.joda.time.MonthDay;

/**
 * Class Quarter.
 *
 * @author Erwin Dupont
 * @since 2015-01-26
 */
public enum Quarter {
	Q1, Q2, Q3, Q4;

	private MonthDay start = new MonthDay(this.ordinal() * 3 + 1, 1);
	private MonthDay end = this.start.plusMonths(2).withDayOfMonth(this.start.plusMonths(2).dayOfMonth().getMaximumValue());

	public Quarter previous() {
		return get(this.ordinal() - 1);
	}

	public Quarter next() {
		return get(this.ordinal() + 1);
	}

	private static Quarter get(int ordinal) {
		return Quarter.values()[(Quarter.values().length + ordinal) % Quarter.values().length];
	}

	public static Quarter getFor(LocalDate date) {
		return Quarter.get(date.getMonthOfYear() / 4);
	}

	public static void main(String[] args) {
		for (Quarter quarter : Quarter.values()) {
			System.out.println("quarter = " + quarter);
		}

		System.out.println("Quarter.getFor(LocalDate.now()) = " + Quarter.getFor(LocalDate.now()));
		System.out.println("Quarter.getFor(Aug 25) = " + Quarter.getFor(new LocalDate(1977, 8, 25)));
		System.out.println("Quarter.getFor(Mar 31) = " + Quarter.getFor(new LocalDate(1977, 3, 31)));
		System.out.println("Quarter.getFor(Apr 1) = " + Quarter.getFor(new LocalDate(1977, 4, 1)));
	}

	@Override
	public String toString() {
		return String.format("%s < %s (%s-%s) < %s", this.previous().name(), this.name(), this.start, this.end, this.next().name());
	}
}
