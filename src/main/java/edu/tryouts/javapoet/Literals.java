package edu.tryouts.javapoet;

import com.squareup.javapoet.MethodSpec;

/**
 * Class Literals.
 *
 * @author Erwin Dupont
 * @since 2015-01-30
 */
public class Literals {
	/**
	 * Command line application execution.
	 *
	 * @param args Array of Strings referencing the command line arguments.
	 */
	public static void main(String[] args) {
		System.out.println(computeRange("multiply10to20", 10, 20, "*"));
	}

	private static MethodSpec computeRange(String name, int from, int to, String op) {
		return MethodSpec.methodBuilder(name)
				.returns(int.class)
				.addStatement("int result = 0")
				.beginControlFlow("for (int i = $L; i < $L; i++)", from, to)
				.addStatement("result = result $L i", op)
				.endControlFlow()
				.addStatement("return result")
				.build();
	}
}
