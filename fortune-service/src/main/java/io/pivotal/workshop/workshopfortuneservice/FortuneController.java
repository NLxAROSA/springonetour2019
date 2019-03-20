package io.pivotal.workshop.workshopfortuneservice;

import io.micrometer.core.instrument.Metrics;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FortuneController {

    private final FortuneRepository repository;

    public FortuneController(FortuneRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String fortune() {
        Metrics.counter("fortune_teller_fortunes_served").increment(1.0);
        return repository.findRandomFortune().getText();
    }
}
