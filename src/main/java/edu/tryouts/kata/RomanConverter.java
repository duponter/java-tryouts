package edu.tryouts.kata;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * Class RomanConverter.
 *
 * @author Erwin Dupont
 * @since 2015-05-20
 */
public class RomanConverter {
	public static Map<Integer, String> arabicsToRomans = new LinkedHashMap<>();

	static {
		arabicsToRomans.put(1000, "M");
		arabicsToRomans.put(900, "CM");
		arabicsToRomans.put(500, "D");
		arabicsToRomans.put(400, "CD");
		arabicsToRomans.put(100, "C");
		arabicsToRomans.put(90, "XC");
		arabicsToRomans.put(50, "L");
		arabicsToRomans.put(40, "XL");
		arabicsToRomans.put(10, "X");
		arabicsToRomans.put(9, "IX");
		arabicsToRomans.put(5, "V");
		arabicsToRomans.put(4, "IV");
		arabicsToRomans.put(1, "I");
	}

	;

	public String convert(Integer number) {
		String result = StringUtils.EMPTY;

		for (Map.Entry<Integer, String> entry : arabicsToRomans.entrySet()) {
			Integer arabicNumeral = entry.getKey();
			String romanNumeral = entry.getValue();

			while (number >= arabicNumeral) {
				result += romanNumeral;
				number -= arabicNumeral;
			}
		}

		return result;
	}
}
