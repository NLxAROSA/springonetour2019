package io.pivotal.workshop.workshopfortuneservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class WorkshopFortuneServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorkshopFortuneServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner loadDatabase(FortuneRepository fortuneRepo) {
		return args -> {
			log.debug("loading database..");
			fortuneRepo.save(new Fortune("Do what works."));
			fortuneRepo.save(new Fortune("Do the right thing."));
			fortuneRepo.save(new Fortune("Always be kind."));
			fortuneRepo.save(new Fortune("You learn from your mistakes... You will learn a lot today."));
			fortuneRepo.save(new Fortune("You can always find happiness at work on Friday."));
			fortuneRepo.save(new Fortune("You will be hungry again in one hour."));
			fortuneRepo.save(new Fortune("Today will be an awesome day!"));
			log.info("Fortune Repo record count: {}", fortuneRepo.count());
			fortuneRepo.findAll().forEach(x -> log.debug(x.toString()));
		};

	}
}
