package edu.tryouts.concurrent.rxjava;

import java.util.Arrays;
import java.util.Collections;

import org.apache.commons.lang3.StringUtils;

import rx.Observable;

/**
 * Class FunctionalReactiveProgramming.
 *
 * @author Erwin Dupont
 * @since 2015-03-27
 */
public class FunctionalReactiveProgramming {
	/**
	 * Command line application execution.
	 *
	 * @param args Array of Strings referencing the command line arguments.
	 */
	public static void main(String[] args) {
		Observable<String> sentenceObservable = Observable.from(Arrays.asList("this", "is", "a", "sentence"));
		sentenceObservable.subscribe(System.out::println);

		sentenceObservable = Observable.from(Arrays.asList("this", "is", "a", "sentence"));
		sentenceObservable.map(s -> String.format("%s ", StringUtils.upperCase(s)))
				.toList().map(l -> {
			Collections.reverse(l);
			return l.toString();
		}).subscribe(System.out::println);

		Observable.just("this", "is", "a", "sentence")
				.map(s -> s.toUpperCase() + " ").toList()
				.map(strings -> {
					Collections.reverse(strings);
					return strings.toString();
				}).subscribe(System.out::println);
	}
}
