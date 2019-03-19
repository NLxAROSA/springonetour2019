package io.pivotal.workshop.workshopfortuneservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WorkshopFortuneServiceApplication {

	private static final Logger log = LoggerFactory.getLogger(WorkshopFortuneServiceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WorkshopFortuneServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner loadDatabase(FortuneRepository fortuneRepo) {
		return args -> {
			log.debug("loading database..");
			fortuneRepo.save(new Fortune("Do what works!"));
			fortuneRepo.save(new Fortune("Do the right thing!"));
			fortuneRepo.save(new Fortune("Always be kind!"));
			fortuneRepo.save(new Fortune("Today is a good day to dye!"));
			fortuneRepo.save(new Fortune("There's a 5 in the clock somewhere."));
			fortuneRepo.save(new Fortune("You will be hungry in an hour."));
			fortuneRepo.save(new Fortune("Today will be an awesome day!"));
			log.info("Fortune Repo record count: {}", fortuneRepo.count());
			fortuneRepo.findAll().forEach(x -> log.debug(x.toString()));
		};

	}
}
