# Exercise 4 - start 

## Goal

* Register you Fortune Service at a Service Registry (Eureka) that is running on Cloud Foundry
* Deploy the Fortune UI (we provide you this application) on Cloud Foundry
* Test the Fortune UI that will call the Fortune Service in the server
* Scale out the Fortune Service

## Steps

* Introduce:
  * Maven dependency: `spring-cloud-services-starter-service-registry`
  * `bootstrap.yml` or `bootstrap.properties` and configure the name `fortune-service` of the application used to register at the Service Registry  
* Start a Service Registry with name `workshop-service-registry`

```bash
cf create-service p-service-registry trial workshop-service-registry
```

* Bind the Service Registry service instance to your application
* Build the application again
* Deploy the updated application to Cloud Foundry

```bash
cf push 
``` 

* Now it's time to deploy a UI in front of the Fortune Service. 
  * Clone repo: [https://github.com/dcaron/workshop-greeting-ui.git](https://github.com/dcaron/workshop-greeting-ui.git)
  * Build the project

* Start a RabbitMQ service instance with name `workshop-cloud-bus`

The Cloud bus is used by the UI

```bash
cf create-service p.rabbitmq single-node-3.7 workshop-cloud-bus
```

* Push the UI to Cloud Foundry

If you have time left:

* Check the service registry Dashboard in the browser and see that both applications are registered
* Play around to (auto) scale the number of instances of your Fortune Service
* Completely shut down all instances of your Fortune Service and see how the UI application behaves

## Hints

* When running the application on your local machine use H2, no Config Server and no Service Registry
* Spring Cloud Service Registry dependency to add `pom.xml`

```xml
<dependency>
    <groupId>io.pivotal.spring.cloud</groupId>
    <artifactId>spring-cloud-services-starter-service-registry</artifactId>
</dependency>
```

