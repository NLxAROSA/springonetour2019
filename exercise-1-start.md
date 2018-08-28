# Exercise 1

## Goal

* Build A Spring Boot 2 micro service that Expose a REST API on the root and return a 
Fortune Cookie message and deploy it to Cloud Foundry.

## Steps 

* Go to [https://start.spring.io](https://start.spring.io)
* Create a (Maven) Spring Boot 2 project with starters `Web` and `Actuator` (io.pivotal.workshop.workshopfortuneservice, workshop-fortune-service)
* Download the project and open it in your favourite IDE
* Introduce a ‘Fortune Cookie’ controller (@RestController, @GetMapping) that returns a simple message
* Build the project using Maven and run it locally first
* Call the REST endpoint from the browser or curl to load the static Fortune text
* If the application is working fine push the application to Cloud Foundry (login required)
* Call the REST endpoint of the deployed application from the browser or curl to load the static Fortune text