# Exercise 3 - solution 

Solution branch: `2-persistent-database-config-server`

* Add dependency management

Add to your `pom.xml`:

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>io.pivotal.spring.cloud</groupId>
            <artifactId>spring-cloud-services-dependencies</artifactId>
            <version>2.0.1.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>Finchley.SR1</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

* Add dependencies 

Add to your `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>io.pivotal.spring.cloud</groupId>
        <artifactId>spring-cloud-services-starter-config-client</artifactId>
    </dependency>
</dependencies>
```

* Disable security

```java
package io.pivotal.workshop.workshopfortuneservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll();
    }
}
```

configuration/application.ymml

```yml
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

* Configure the JPA to use the MySQL55Dialect and generate the table at startup

```yml
spring:
  jpa:
    generate-ddl: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL55Dialect
```

* Start a config server in Cloud Foundry

```bash
cf create-service p-config-server standard workshop-config-server -c '{"git": { "uri": "https://github.com/dcaron/workshop-fortune-service.git", "searchPaths": "configuration", "label": "2-persistent-database-config-server" } }'
```

* Start a database in Cloud Foundry

```bash
cf create-service p.mysql db-small workshop-db
```

* Bind the database and config server services to the application

introduce service instance names in manifest.yml:

```yml
  services:
    - workshop-db
    - workshop-config-server
```

* Build project again

```bash
./mvnw clean package
```

* Push the new version of your application to Cloud Foundry

```bash
cf push 
``` 