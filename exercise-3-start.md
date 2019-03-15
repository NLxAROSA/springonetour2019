# Exercise 3 - start 

## Goal
 
* Load the random Fortune Cookie text from a persistent MySQL database when running on Cloud Foundry
* Load the configuration for the Fortune Cookie service from a Spring Cloud Config Server when running on Cloud Foundry

## Steps

This exercise is a bit harder so we have have some [hints](#hints) for you

* Start a Free MySQL Database Service instance (p.mysql) on Cloud Foundry. `db-small` is the name of free service plan

Syntax to start a service:

```
cf create-service <service-name> <service-plan> <service-instance-name>
```

```bash
cf create-service p.mysql db-small workshop-db
```

* Introduce:
  * Dependency management for `spring-cloud-services-dependencies` and `spring-cloud-dependencies` (see hints section)
  * Maven dependencies: `spring-boot-starter-security` and `spring-cloud-services-starter-config-client` 
* Introduce a `application.yml` in directory called `configuration` in the root of this project. This a bit of a short cut but we use this file to be served by the config server
* Start a `Config Server` in Cloud Foundry and expose the configuration from this project

```
cf create-service p-config-server standard workshop-config-server -c '{"git": { "uri": "https://github.com/dcaron/workshop-fortune-service.git", "searchPaths": "configuration", "label": "2-persistent-database-config-server" } }'
```

* Disable security for now by adding a `SecurityConfig` and expose the actuator management endpoints (don't do this in production, have proper endpoint auth there unless your API is public altogether)
* Configure JPA `spring.jpa` to use the `org.hibernate.dialect.MySQL55Dialect` and make sure the table to store the fortune texts in is created at startup of the application 
* Bind the services to your application by adding services instances `workshop-db` and `workshop-config-server' to the `manifest.yml`. 
* Run your application locally (Note for local development we still run H2 and don't use the config server)
* If the application is working fine push the updated version of your application to Cloud Foundry

```bash
cf push 
``` 

* Test the application in the browser

Nice work you now deployed your Fortune Service to PCF with a persistent database and load the configuration from the config server!

## Hints

* Keep H2 for local development
* We don't create a Spring Cloud Config Server Spring Boot project our self we just start a service instance from the Cloud Foundry Market Place
* For local development we don't run a Spring Cloud Config Server
* Required dependency management section 

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>io.pivotal.spring.cloud</groupId>
            <artifactId>spring-cloud-services-dependencies</artifactId>
            <version>2.1.0.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>Greenwich.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

* Cloud dependencies for Cloud Foundry to connect to the config server:

```xml
<dependency>
  <groupId>io.pivotal.spring.cloud</groupId>
  <artifactId>spring-cloud-services-starter-config-client</artifactId>
</dependency>
```