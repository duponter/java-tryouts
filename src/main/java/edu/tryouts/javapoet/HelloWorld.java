package edu.tryouts.javapoet;

import java.io.IOException;
import javax.lang.model.element.Modifier;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

/**
 * Class HelloWorld.
 *
 * @author Erwin Dupont
 * @since 2015-01-30
 */
public class HelloWorld {
	/**
	 * Command line application execution.
	 *
	 * @param args Array of Strings referencing the command line arguments.
	 */
	public static void main(String[] args) throws IOException {
		MethodSpec main = MethodSpec.methodBuilder("main")
				.addModifiers(Modifier.PUBLIC, Modifier.STATIC)
				.returns(void.class)
				.addParameter(String[].class, "args")
				.addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet!")
				.build();

		TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
				.addModifiers(Modifier.PUBLIC, Modifier.FINAL)
				.addMethod(main)
				.build();

		JavaFile javaFile = JavaFile.builder("com.example.helloworld", helloWorld)
				.build();

		javaFile.writeTo(System.out);
	}
}
