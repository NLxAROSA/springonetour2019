# Pivotal Cloud Foundry Workshop - Fortune Cookie Service

This workshop get you up to speed with Pivotal Cloud Foundry.
In this workshop you are going to develop a Spring Boot 2 micro service and deploy it to Cloud Foundry.

[Getting started with Pivotal Cloud Foundry](https://pivotal.io/platform/pcf-tutorials/getting-started-with-pivotal-cloud-foundry)

## Prerequisites 

We expect you have installed:

* [Java 8 (or later)](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Cloud Foundry CLI](https://docs.run.pivotal.io/cf-cli/install-go-cli.html)

And you have Pivotal Cloud Foundry (trial) account to login.
In case you don't have an account yet [Create a Pivotal Cloud Foundry account](https://run.pivotal.io/).

## Verify PCF CLI is working

```bash
cf -v
```

Login to Cloud Foundry:

```bash
cf login -a https://api.run.pivotal.io
```

## Build the project

For every exercise we need to build the project: 

```bash
mvn clean package
```

In case you don't have Maven installed run: 

```bash
./mvnw clean package
```

## Run the application locally

Before we push the application to Cloud Foundry run it locally first!

```bash
./mvnw spring-boot:run
```

Open in the browser: [http://localhost:8080/](http://localhost:8080/)

## Exercises

* Exercise 1: [start](exercise-1-start.md), [solution](exercise-1-solution.md)

