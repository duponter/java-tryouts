package edu.tryouts.concurrent;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BinaryOperator;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

/**
 * Class CompletableFutureDemo.
 *
 * @author Erwin Dupont
 * @since 2015-01-22
 */
public class CompletableFutureDemo {

	public static final Logger LOGGER = Logger.getLogger(CompletableFutureDemo.class.getName());

	/**
	 * Command line application execution.
	 *
	 * @param args Array of Strings referencing the command line arguments.
	 */
	public static void main(String[] args) {
		LOGGER.info("Start");
		//		ExecutorService executor = Executors.newFixedThreadPool(2, new BasicThreadFactory.Builder().namingPattern("playerthread-%d").build());
		ExecutorService darkExecutor = Executors.newSingleThreadExecutor(new BasicThreadFactory.Builder().namingPattern("Dark Player").build());
		ExecutorService lightExecutor = Executors.newSingleThreadExecutor(new BasicThreadFactory.Builder().namingPattern("Light Player").build()) ;

		List<String> darkChoices = IntStream.rangeClosed(1, 5).mapToObj(i -> String.format("Dark Choice %d", i)).collect(Collectors.toList());
		List<String> lightChoices = IntStream.rangeClosed(1, 3).mapToObj(i -> String.format("Light Choice %d", i)).collect(Collectors.toList());

		BinaryOperator<String> consumer = (id, next) -> {
			throw new IllegalStateException(String.format("Unexpected accumulation of '%s' and '%s'", id, next));
		};
//		System.out.println("darkChoices.stream().reduce(consumer) = " + darkChoices.stream().reduce(consumer).get());
		try {
			String found = lightChoices.stream().reduce(consumer).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			String found = lightChoices.stream().filter(s -> s.endsWith("20")).reduce(consumer).get();
		} catch (Exception e) {
			e.printStackTrace();
		}

		LOGGER.info("Preparation complete");
		CompletableFuture<String> darkRequest = CompletableFuture.supplyAsync(() -> darkChoices.stream().filter(s -> s.endsWith("4")).findFirst().get(), darkExecutor);
		LOGGER.info("Dark Request sent");

		CompletableFuture<String> lightRequest = CompletableFuture.supplyAsync(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			LOGGER.info("Choosing option with " + Thread.currentThread().getName());
			return lightChoices.stream().filter(s -> s.endsWith("2")).reduce(consumer).get();}, lightExecutor);
//				return lightChoices.stream().reduce(consumer).get();}, lightExecutor);
		lightRequest.exceptionally(throwable -> {
			throwable.printStackTrace();
			return null;
		});
		LOGGER.info("Light Request sent");

		darkRequest.thenAcceptBothAsync(lightRequest, (d, l) -> LOGGER.info(String.format("Duelling dark %s against light %s%n", d, l)));

		LOGGER.info("End");
		darkExecutor.shutdown();
		lightExecutor.shutdown();
	}
}
