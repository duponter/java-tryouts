package edu.tryouts.javapoet;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

/**
 * Class Interfaces.
 *
 * @author Erwin Dupont
 * @since 2015-01-30
 */
public class Interfaces {
	/**
	 * Command line application execution.
	 *
	 * @param args Array of Strings referencing the command line arguments.
	 */
	public static void main(String[] args) {
		TypeSpec helloWorld = TypeSpec.interfaceBuilder("HelloWorld")
				.addModifiers(Modifier.PUBLIC)
				.addField(FieldSpec.builder(String.class, "ONLY_THING_THAT_IS_CONSTANT")
						.addModifiers(Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
						.initializer("$S", "change")
						.build())
				.addMethod(MethodSpec.methodBuilder("beep")
						.addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
						.build())
				.build();

		System.out.println(helloWorld);
	}
}
