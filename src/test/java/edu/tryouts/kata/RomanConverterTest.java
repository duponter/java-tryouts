package edu.tryouts.kata;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Given a positive integer number (eg. 42) determine its Roman numeral representation as a String (eg "XLII"). You cannot write numerals like IM for 999.
 * <p>
 * http://codurance.com/2015/05/18/applying-transformation-priority-premise-to-roman-numerals-kata/
 *
 * @author Erwin Dupont
 * @since 2015-05-20
 */
@RunWith(Parameterized.class)
public class RomanConverterTest {
	private Integer number;
	private String expected;

	public RomanConverterTest(Integer number, String expected) {
		this.number = number;
		this.expected = expected;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][]{
				{1, "I"},
				{2, "II"},
				{3, "III"},
				{4, "IV"},
				{5, "V"},
				{6, "VI"},
				{7, "VII"},
				{8, "VIII"},
				{9, "IX"},
				{10, "X"},
				{40, "XL"},
				{50, "L"},
				{90, "XC"},
				{100, "C"},
				{400, "CD"},
				{500, "D"},
				{900, "CM"},
				{1000, "M"},
				{846, "DCCCXLVI"},
				{1999, "MCMXCIX"},
				{2008, "MMVIII"}
		});
	}

	@Test
	public void convertNumberToRoman() {
		String romanNumeral = new RomanConverter().convert(number);
		System.out.printf("Converting %d to %s%n", number, romanNumeral);
		assertThat(romanNumeral).as(String.format("Converting %d to %s%n", number, romanNumeral)).isEqualTo(expected);
	}
}