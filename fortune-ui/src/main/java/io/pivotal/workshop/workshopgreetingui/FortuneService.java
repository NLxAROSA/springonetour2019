package io.pivotal.workshop.workshopgreetingui;

import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.vavr.CheckedFunction0;
import io.vavr.control.Try;

@Component
public class FortuneService {

  Logger log = LoggerFactory.getLogger(FortuneService.class);

  private final RestTemplate restTemplate;
  private final CircuitBreaker circuitBreaker;

  public FortuneService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
    this.circuitBreaker = createCircuitBreaker();
  }

  private CircuitBreaker createCircuitBreaker() {
    CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom().failureRateThreshold(50)
        .waitDurationInOpenState(Duration.ofMillis(20000)).build();
    CircuitBreaker circuitBreaker = CircuitBreaker.of("resilience-provider", circuitBreakerConfig);
    circuitBreaker.getEventPublisher().onSuccess(event -> log.info("Call success via circuit breaker"))
        .onCallNotPermitted(event -> log.info("Call denied by circuit breaker"))
        .onError(event -> log.info("Call failed via circuit breaker"));
    return circuitBreaker;
  }

  public String getFortune() {
    CheckedFunction0<String> someServiceCall = CircuitBreaker.decorateCheckedSupplier(circuitBreaker,
        () -> this.remoteFortune());
    Try<String> result = Try.of(someServiceCall).recover((throwable) -> this.defaultFortune());
    return result.get();
  }

  private String remoteFortune() {
    return restTemplate.getForObject("http://workshop-fortune-service.apps.internal:8080/", String.class);
  }

  private String defaultFortune() {
    return "Your future is unclear, please try later.";
  }

}
