# Exercise 2 - solution

Solution branch: `1-add-database`

* Added dependencies

```xml
<dependencies>
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
</dependencies>
```

* Introduce Fortune entity 

Make sure your IDE can handle Lombok.
You might need to install a plugin like [IntelliJ Lombok Plugin](https://plugins.jetbrains.com/plugin/6317-lombok-plugin) 

```java
package io.pivotal.workshop.workshopfortuneservice;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Fortune {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String text;
}
```

* Introduce a FortuneRepository to load a random Fortune from the database

```java
package io.pivotal.workshop.workshopfortuneservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FortuneRepository extends JpaRepository<Fortune, Long> {

    // This query is not efficient, but ok for such s small DB
    @Query(value="select * from fortune order by rand() limit 1", nativeQuery = true)
    Fortune findRandomFortune();

}
```

* Introduce a command line running to insert random Fortune texts in the databae

```java
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
```

* Introduce a `manifest.yml`

This avoid us to specify the name, random route and path to the jar when pushing to application to Cloud Foundry
from the command line:

```yml
  name: workshop-fortune-service
  random-route: true
  path: target/workshop-fortune-service-0.0.1-SNAPSHOT.jar
```

* Build project again

```bash
./mvnw clean package
```

* Push the application to Cloud Foundry

```bash
cf push
```