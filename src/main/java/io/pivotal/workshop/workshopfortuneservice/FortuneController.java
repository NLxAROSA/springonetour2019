package io.pivotal.workshop.workshopfortuneservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FortuneController {

    @GetMapping
    public String fortune() {
        return "No fortune";
    }
}
