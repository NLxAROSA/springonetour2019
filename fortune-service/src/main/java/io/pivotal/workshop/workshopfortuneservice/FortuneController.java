package io.pivotal.workshop.workshopfortuneservice;

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
        return repository.findRandomFortune().getText();
    }
}
