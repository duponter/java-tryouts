package edu.tryouts.javapoet;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

/**
 * Class Enums.
 *
 * @author Erwin Dupont
 * @since 2015-01-30
 */
public class Enums {
	/**
	 * Command line application execution.
	 *
	 * @param args Array of Strings referencing the command line arguments.
	 */
	public static void main(String[] args) {
		TypeSpec helloWorld = TypeSpec.enumBuilder("Roshambo")
				.addModifiers(Modifier.PUBLIC)
				.addEnumConstant("ROCK")
				.addEnumConstant("SCISSORS")
				.addEnumConstant("PAPER")
				.build();

		System.out.println(helloWorld);

		helloWorld = TypeSpec.enumBuilder("Roshambo")
				.addModifiers(Modifier.PUBLIC)
				.addEnumConstant("ROCK", TypeSpec.anonymousClassBuilder("$S", "fist")
						.addMethod(MethodSpec.methodBuilder("toString")
								.addAnnotation(Override.class)
								.addModifiers(Modifier.PUBLIC)
								.addStatement("return $S", "avalanche!")
								.build())
						.build())
				.addEnumConstant("SCISSORS", TypeSpec.anonymousClassBuilder("$S", "peace")
						.build())
				.addEnumConstant("PAPER", TypeSpec.anonymousClassBuilder("$S", "flat")
						.build())
				.addField(String.class, "handsign", Modifier.PRIVATE, Modifier.FINAL)
				.addMethod(MethodSpec.constructorBuilder()
						.addParameter(String.class, "handsign")
						.addStatement("this.$N = $N", "handsign", "handsign")
						.build())
				.build();

		System.out.println(helloWorld);
	}
}
