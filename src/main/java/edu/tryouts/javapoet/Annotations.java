package edu.tryouts.javapoet;

import java.util.logging.LogRecord;
import javax.lang.model.element.Modifier;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.MethodSpec;

/**
 * Class Annotations.
 *
 * @author Erwin Dupont
 * @since 2015-01-30
 */
public class Annotations {
	/**
	 * Command line application execution.
	 *
	 * @param args Array of Strings referencing the command line arguments.
	 */
	public static void main(String[] args) {
		MethodSpec toString = MethodSpec.methodBuilder("toString")
				.addAnnotation(Override.class)
				.returns(String.class)
				.addModifiers(Modifier.PUBLIC)
				.addStatement("return $S", "Hoverboard")
				.build();

		System.out.println(toString);

		MethodSpec logRecord = MethodSpec.methodBuilder("recordEvent")
				.addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
				.addAnnotation(AnnotationSpec.builder(Headers.class)
						.addMember("accept", "$S", "application/json; charset=utf-8")
						.addMember("userAgent", "$S", "Square Cash")
						.build())
				.addParameter(LogRecord.class, "logRecord")
				.returns(LogReceipt.class)
				.build();

		System.out.println(logRecord);

		logRecord = MethodSpec.methodBuilder("recordEvent")
				.addModifiers(Modifier.PUBLIC, Modifier.ABSTRACT)
				.addAnnotation(AnnotationSpec.builder(HeaderList.class)
						.addMember("value", "$L", AnnotationSpec.builder(Header.class)
								.addMember("name", "$S", "Accept")
								.addMember("value", "$S", "application/json; charset=utf-8")
								.build())
						.addMember("value", "$L", AnnotationSpec.builder(Header.class)
								.addMember("name", "$S", "User-Agent")
								.addMember("value", "$S", "Square Cash")
								.build())
						.build())
				.addParameter(LogRecord.class, "logRecord")
				.returns(LogReceipt.class)
				.build();
		System.out.println(logRecord);
	}
}
