package edu.tryouts.javapoet;

import java.io.IOException;
import java.util.Date;
import javax.lang.model.element.Modifier;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

/**
 * Class Types.
 *
 * @author Erwin Dupont
 * @since 2015-01-30
 */
public class Types {
	/**
	 * Command line application execution.
	 *
	 * @param args Array of Strings referencing the command line arguments.
	 */
	public static void main(String[] args) throws IOException {
		MethodSpec today = MethodSpec.methodBuilder("today")
				.returns(Date.class)
				.addStatement("return new $T()", Date.class)
				.build();

		TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
				.addModifiers(Modifier.PUBLIC, Modifier.FINAL)
				.addMethod(today)
				.build();

		JavaFile javaFile = JavaFile.builder("com.example.helloworld", helloWorld)
				.build();

		javaFile.writeTo(System.out);


		ClassName hoverboard = ClassName.get("com.mattel", "Hoverboard");

		today = MethodSpec.methodBuilder("tomorrow")
				.returns(hoverboard)
				.addStatement("return new $T()", hoverboard)
				.build();
		System.out.println(today);


		ClassName list = ClassName.get("java.util", "List");
		ClassName arrayList = ClassName.get("java.util", "ArrayList");
		TypeName listOfHoverboards = ParameterizedTypeName.get(list, hoverboard);

		today = MethodSpec.methodBuilder("beyond")
				.returns(listOfHoverboards)
				.addStatement("$T result = new $T<>()", listOfHoverboards, arrayList)
				.addStatement("result.add(new $T())", hoverboard)
				.addStatement("result.add(new $T())", hoverboard)
				.addStatement("result.add(new $T())", hoverboard)
				.addStatement("return result")
				.build();
		System.out.println(today);
	}
}
