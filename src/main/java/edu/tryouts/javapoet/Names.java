package edu.tryouts.javapoet;

import com.squareup.javapoet.MethodSpec;

/**
 * Class Names.
 *
 * @author Erwin Dupont
 * @since 2015-01-30
 */
public class Names {
	/**
	 * Command line application execution.
	 *
	 * @param args Array of Strings referencing the command line arguments.
	 */
	public static void main(String[] args) {
		MethodSpec hexDigit = MethodSpec.methodBuilder("hexDigit")
				.addParameter(int.class, "i")
				.returns(char.class)
				.addStatement("return (char) (i < 10 ? i + '0' : i - 10 + 'a')")
				.build();

		MethodSpec byteToHex = MethodSpec.methodBuilder("byteToHex")
				.addParameter(int.class, "b")
				.returns(String.class)
				.addStatement("char[] result = new char[2]")
				.addStatement("result[0] = $N((b >>> 4) & 0xf)", hexDigit)
				.addStatement("result[1] = $N(b & 0xf)", hexDigit)
				.addStatement("return new String(result)")
				.build();

		System.out.println(byteToHex);
	}
}
