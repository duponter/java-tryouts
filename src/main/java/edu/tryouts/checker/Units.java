package edu.tryouts.checker;

import org.checkerframework.checker.units.qual.m;
import org.checkerframework.checker.units.qual.mPERs;
import org.checkerframework.checker.units.qual.s;

/**
 * Class Units.
 *
 * @author Erwin Dupont
 * @since 2015-02-02
 */
public class Units {
	@SuppressWarnings("unsafe")
	private static final	@m	int m = (@m int) 1; // define 1 meter
	@SuppressWarnings("unsafe")
	private static final	@s	int s = (@s int) 1; // define 1 second

	public static void main(String[] args) {
		@m double meters = 5.0 * m;
		@s double seconds = 2.0 * s;
//		@kmPERh double speed = meters / seconds; // <-- doesn't compile
		@mPERs double speed = meters / seconds;

		System.out.println("Speed: " + speed);
	}
}
