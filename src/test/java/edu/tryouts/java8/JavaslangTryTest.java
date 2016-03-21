package edu.tryouts.java8;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.InputStream;
import java.net.URL;

import org.junit.Test;

import javaslang.control.Either;
import javaslang.control.Try;

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
		return Either.left(1);
	}

	private Either<Integer, String> compute2() {
		return Either.right("error2");
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
