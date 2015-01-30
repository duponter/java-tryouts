package edu.tryouts.javapoet;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

/**
 * Class Methods.
 *
 * @author Erwin Dupont
 * @since 2015-01-30
 */
public class Methods {
	/**
	 * Command line application execution.
	 *
	 * @param args Array of Strings referencing the command line arguments.
	 */
	public static void main(String[] args) {
		MethodSpec flux = MethodSpec.methodBuilder("flux")
				.addModifiers(Modifier.ABSTRACT, Modifier.PROTECTED)
				.build();

		TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
				.addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
				.addMethod(flux)
				.build();

		System.out.println(helloWorld);
	}
}
