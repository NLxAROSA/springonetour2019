# Spring One Tour 2019 Amsterdam Demo code

Demo code for the Spring One Tour 2019 presentation demo of 'Deploying Applications Using Pivotal Cloud Foundry'

[Getting started with Pivotal Cloud Foundry](https://pivotal.io/platform/pcf-tutorials/getting-started-with-pivotal-cloud-foundry)

## Prerequisites 

* A computer running Windows, Linux or MacOS
* A working internet connection
* An IDE or code editor of your choice

* [JDK8 or higher](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Cloud Foundry CLI](https://docs.run.pivotal.io/cf-cli/install-go-cli.html)

Create a free account on [Pivotal Web Services](https://run.pivotal.io/) (hosted PCF environment)

## Verify PCF CLI is working

```bash
cf -v
```

## Login to Cloud Foundry

```bash
cf login -a https://api.run.pivotal.io
Email> <your email>
Password> <your password>
```

## Build the project

```bash
./mvnw clean package
```

## Run the application locally

Before we push the application to Cloud Foundry run it locally first!

```bash
./mvnw spring-boot:run
```

Open in the browser: [http://localhost:8080/](http://localhost:8080/)

## Create services on Cloud Foundry

cf create-service cleardb spark workshop-db
cf create-service p-config-server trial workshop-config-server
cf create-service p-service-registry trial workshop-service-registry
