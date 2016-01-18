package edu.tryouts.jodatime;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;

/**
 * Class Quarter.
 *
 * @author Erwin Dupont
 * @since 2015-01-26
 */
public enum Quarter {
	Q1, Q2, Q3, Q4;

	private MonthDay start = MonthDay.of(this.ordinal() * 3 + 1, 1);
	private MonthDay end;

	Quarter() {
		MonthDay temp = this.start.with(this.start.getMonth().plus(2));
		this.end = temp.withDayOfMonth(temp.getMonth().maxLength());
	}

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
		return Quarter.get(date.getMonthValue() / 4);
	}

	public static void main(String[] args) {
		for (Quarter quarter : Quarter.values()) {
			System.out.println("quarter = " + quarter);
		}

		LocalDate now = LocalDate.now();
		System.out.printf("Quarter.getFor(%s) = %s%n", now.format(DateTimeFormatter.ofPattern("MMM d")), Quarter.getFor(now));
		System.out.printf("Quarter.getFor(Aug 25) = %s%n", Quarter.getFor(LocalDate.of(1977, 8, 25)));
		System.out.printf("Quarter.getFor(Mar 31) = %s%n", Quarter.getFor(LocalDate.of(1977, 3, 31)));
		System.out.printf("Quarter.getFor(Apr 1) = %s%n", Quarter.getFor(LocalDate.of(1977, 4, 1)));
	}

	@Override
	public String toString() {
		return String.format("%s < %s (%s-%s) < %s", this.previous().name(), this.name(), this.start, this.end, this.next().name());
	}
}
