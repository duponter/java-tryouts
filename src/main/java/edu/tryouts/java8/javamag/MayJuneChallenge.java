package edu.tryouts.java8.javamag;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Class MayJuneChallenge.
 *
 * @author Erwin Dupont
 * @since 2014-09-19
 */
public class MayJuneChallenge {
	/**
	 * Command line application execution.
	 *
	 * @param args Array of Strings referencing the command line arguments.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		BufferedReader reader = new BufferedReader(new FileReader(MayJuneChallenge.class.getResource("may-june.txt").getFile()));
//		int longest = reader.lines().mapToInt(String::length).max().getAsInt();
//		System.out.println("longest line # chars = " + longest);

		String
//				longestLine = reader.lines().max(String::longest)				.orElse("");
				longestLine = reader.lines().reduce((a, b) -> a.length() > b.length() ? a : b).orElse("");
//				longestLine = reader.lines().reduce("", (a, b)				-> a.length() > b.length());
//				longestLine = reader.lines().max((a, b) -> b.length()-a.length()).orElse("");
//				longestLine = reader.lines().sorted((a, b) -> a.length());

		System.out.println("longestLine = " + longestLine);
	}


}
