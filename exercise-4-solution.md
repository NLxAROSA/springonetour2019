# Exercise 4 - solution 

## Steps

* Add maven dependency 

<dependency>
    <groupId>io.pivotal.spring.cloud</groupId>
    <artifactId>spring-cloud-services-starter-service-registry</artifactId>
</dependency>

* Introduce a `bootstrap.properties` or `bootstrap.yml`

```properties
spring.application.name=fortune-service
```

or 

```yml
spring:
  application:
    name: fortune-service   
```

* Bind the service registry instance with name `workshop-service-registry` to your application

manifest.yml:

```yml
services:
    - workshop-db
    - workshop-config-server
    - workshop-service-registry
```  

* Start a Service Registry with name `workshop-service-registry` on Cloud Foundry

```bash
cf create-service p-service-registry standard workshop-service-registry
```

* Build the project again

```bash
./mvnw clean package
```

* Push the updated application to Cloud Foundry

```bash
cf push 
```

* Prepare the UI application 
  * Clone repo: [https://github.com/dcaron/workshop-greeting-ui.git](https://github.com/dcaron/workshop-greeting-ui.git) 
  * Build the project `./mvnw clean package`

* Start a RabbitMQ service instance with name `workshop-cloud-bus`

The Cloud Bus is used by the UI application

```bash
cf create-service p.rabbitmq single-node-3.7 workshop-cloud-bus
```

* Push the UI to Cloud Foundry
  
```bash
cf push
```
