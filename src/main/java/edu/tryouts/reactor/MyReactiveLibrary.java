package edu.tryouts.reactor;

import java.time.Duration;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MyReactiveLibrary {
    public Flux<String> alphabet5(char from) {
        return Flux.range((int) from, 5)
                .map(i -> String.format("%s", i.intValue())).take(Math.min(5, 'z' - from + 1));
    }

    public Mono<String> withDelay(String value, int delaySeconds) {
        return Mono.just(value)
                .delaySubscription(Duration.ofSeconds(delaySeconds));
    }
}
