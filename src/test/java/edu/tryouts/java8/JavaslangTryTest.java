package edu.tryouts.java8;

import java.io.InputStream;
import java.net.URL;

import javaslang.control.Either;
import javaslang.control.Left;
import javaslang.control.Right;
import javaslang.control.Try;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Class JavaslangTryTest.
 *
 * @author Erwin Dupont
 * @since 2015-05-20
 */
public class JavaslangTryTest {
	@Test
	public void tryTest() {

	}

	@Test
	public void eitherTest() {
		assertThat(this.compute().get()).as("compute").isEqualTo(1);
		assertThat(this.compute2().get()).as("compute2").isEqualTo("error2");
	}

	private Either<Integer, String> compute() {
		return new Left<>(1);
	}

	private Either<Integer, String> compute2() {
		return new Right<>("error2");
	}

	;

	private Try<String> getContent(String location) {
		return Try
				.of(() -> new URL(location))
				.filter(url -> "http".equals(url.getProtocol()))
				.flatMap(url -> Try.of(url::openConnection))
				.flatMap(con -> Try.of(con::getInputStream))
				.map(this::readAndClose);
	}

	private String readAndClose(InputStream inputStream) {
		return null;
	}

	;
}
