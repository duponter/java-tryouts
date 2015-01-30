package edu.tryouts.javapoet;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

/**
 * Class Constructors.
 *
 * @author Erwin Dupont
 * @since 2015-01-30
 */
public class Constructors {
	/**
	 * Command line application execution.
	 *
	 * @param args Array of Strings referencing the command line arguments.
	 */
	public static void main(String[] args) {
		MethodSpec flux = MethodSpec.constructorBuilder()
				.addModifiers(Modifier.PUBLIC)
				.addParameter(String.class, "greeting")
				.addStatement("this.$N = $N", "greeting", "greeting")
				.build();

		TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
				.addModifiers(Modifier.PUBLIC)
				.addField(String.class, "greeting", Modifier.PRIVATE, Modifier.FINAL)
				.addMethod(flux)
				.build();

		System.out.println(helloWorld);
	}
}
