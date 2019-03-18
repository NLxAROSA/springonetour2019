package io.pivotal.workshop.workshopgreetingui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FortuneService {

  Logger logger = LoggerFactory
          .getLogger(FortuneService.class);

  private final RestTemplate restTemplate;

  public FortuneService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public String getFortune() {
    String fortune = restTemplate.getForObject("http://workshop-fortune-service.apps.internal:8080/", String.class);
    return fortune;
  }

  public String defaultFortune(){
    logger.debug("Default fortune used.");
    return "This fortune is no good. Try another.";
  }

}
