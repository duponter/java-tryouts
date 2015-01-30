package edu.tryouts.javapoet;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;

/**
 * Class Parameters.
 *
 * @author Erwin Dupont
 * @since 2015-01-30
 */
public class Parameters {
	/**
	 * Command line application execution.
	 *
	 * @param args Array of Strings referencing the command line arguments.
	 */
	public static void main(String[] args) {
		ParameterSpec android = ParameterSpec.builder(String.class, "android")
				.addModifiers(Modifier.FINAL)
				.build();

		MethodSpec welcomeOverlords = MethodSpec.methodBuilder("welcomeOverlords")
				.addParameter(android)
				.addParameter(String.class, "robot", Modifier.FINAL)
				.build();

		System.out.println(welcomeOverlords);
	}
}
