package edu.tryouts.kata;

import org.apache.commons.lang3.StringUtils;

/**
 * Class RomanConverter.
 *
 * @author Erwin Dupont
 * @since 2015-05-20
 */
public class RomanConverter {
	public String convert(Integer number) {
		String result = StringUtils.EMPTY;

		for (RomanNumeral roman : RomanNumeral.values()) {
			Integer arabicNumeral = roman.toArabicNumeral();

			while (number >= arabicNumeral) {
				result += roman.name();
				number -= arabicNumeral;
			}
		}

		return result;
	}
}
