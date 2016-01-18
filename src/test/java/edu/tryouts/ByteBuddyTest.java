package edu.tryouts;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

public class ByteBuddyTest {
	private static final String TO_STRING = "Hello World from Byte Buddy!";

	@Test
	public void helloWorld() throws IllegalAccessException, InstantiationException {
		Class<?> dynamicType = new ByteBuddy()
				.subclass(Object.class)
				.method(ElementMatchers.named("toString"))
				.intercept(FixedValue.value(TO_STRING))
				.make()
				.load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
				.getLoaded();
		assertThat(dynamicType.newInstance().toString()).isEqualTo(TO_STRING);
	}
}
