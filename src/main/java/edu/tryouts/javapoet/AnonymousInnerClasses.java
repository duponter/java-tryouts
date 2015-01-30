package edu.tryouts.javapoet;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.lang.model.element.Modifier;

import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

/**
 * Class AnonymousInnerClasses.
 *
 * @author Erwin Dupont
 * @since 2015-01-30
 */
public class AnonymousInnerClasses {
	/**
	 * Command line application execution.
	 *
	 * @param args Array of Strings referencing the command line arguments.
	 */
	public static void main(String[] args) {
		TypeSpec comparator = TypeSpec.anonymousClassBuilder("")
				.addSuperinterface(ParameterizedTypeName.get(Comparator.class, String.class))
				.addMethod(MethodSpec.methodBuilder("compare")
						.addAnnotation(Override.class)
						.addModifiers(Modifier.PUBLIC)
						.addParameter(String.class, "a")
						.addParameter(String.class, "b")
						.addStatement("return $N.length() - $N.length()", "a", "b")
						.build())
				.build();

		TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
				.addMethod(MethodSpec.methodBuilder("sortByLength")
						.addParameter(ParameterizedTypeName.get(List.class, String.class), "strings")
						.addStatement("$T.sort($N, $L)", Collections.class, "strings", comparator)
						.build())
				.build();

		System.out.println(helloWorld);
	}
}
