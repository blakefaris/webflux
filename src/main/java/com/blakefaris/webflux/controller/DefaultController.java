package com.blakefaris.webflux.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class DefaultController {

    private final WebClient webClient = WebClient.create("http://localhost:8081");

    @GetMapping("/")
    public ResponseEntity<String> get() {
        return new ResponseEntity<>("Did you mean /blocking or /nonblocking?", HttpStatus.NOT_FOUND);
    }

    /**
     * Blocking endpoint.
     * If we run using Netty and webflux we will get:
     * java.lang.IllegalStateException: block()/blockFirst()/blockLast() are blocking, which is not supported in thread reactor-http-nio-2
     * <p>
     * Instead switched to Tomcat with limited threads for demonstration purposes by bringing in dependency spring-boot-starter-web
     * <p>
     * If you do not want this endpoint, a blocking endpoint keep spring-boot-starter-webflux and remove spring-boot-starter-web from pom.xml
     *
     * @return {@link String} from the external service
     */
    @GetMapping("/blocking")
    public String getBlocking() {
        return webClient.get().retrieve().bodyToMono(String.class).block();
    }

    @GetMapping("/nonblocking")
    public Mono<String> getNonblocking() {
        return webClient.get().retrieve().bodyToMono(String.class);
    }

}
