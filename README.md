# Pivotal Cloud Foundry Workshop - Fortune Cookie Service

This workshop get you up to speed with Pivotal Cloud Foundry.
In this workshop you are going to develop a Spring Boot 2 micro service and deploy it to Cloud Foundry.

[Getting started with Pivotal Cloud Foundry](https://pivotal.io/platform/pcf-tutorials/getting-started-with-pivotal-cloud-foundry)

## Prerequisites 

We expect you to bring:

* A laptop with Windows, Linux or MacOS
* A working internet connection
* An IDE or code editor of your choice

We expect you have installed:

* [JDK8 or higher](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Cloud Foundry CLI](https://docs.run.pivotal.io/cf-cli/install-go-cli.html)

Login credentials and access to the environment will be provided by the instructor(s).

## Verify PCF CLI is working

```bash
cf -v
```

Login to Cloud Foundry:

```bash
cf login -a https://api.sys.pushto.cf
Email> <provided user>
Password> <provided password>
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
* Exercise 2: [start](exercise-2-start.md), [solution](exercise-2-solution.md)
* Exercise 3: [start](exercise-3-start.md), [solution](exercise-3-solution.md)
* Exercise 4: [start](exercise-4-start.md), [solution](exercise-4-solution.md)