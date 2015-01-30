package edu.tryouts.javapoet;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

/**
 * Class Strings.
 *
 * @author Erwin Dupont
 * @since 2015-01-30
 */
public class Strings {
	public static void main(String[] args) throws Exception {
		TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
				.addModifiers(Modifier.PUBLIC, Modifier.FINAL)
				.addMethod(whatsMyName("slimShady"))
				.addMethod(whatsMyName("eminem"))
				.addMethod(whatsMyName("marshallMathers"))
				.build();

		JavaFile javaFile = JavaFile.builder("com.example.helloworld", helloWorld)
				.build();

		javaFile.writeTo(System.out);
	}

	private static MethodSpec whatsMyName(String name) {
		return MethodSpec.methodBuilder(name)
				.returns(String.class)
				.addStatement("return $S", name)
				.build();
	}
}
