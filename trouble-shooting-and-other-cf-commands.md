# Trouble shooting and other useful PCF CLI commands 

## Show Market place services

```bash
cf marketplace
```

## Show services instances

```bash
cf services
name          service   plan    bound apps   last operation
workshop-db   p.mysql   db-small             create succeeded
```

## Show info about config server

```bash
cf service workshop-config-server
```

## Show info about Database Service instance  

```bash
cf service workshop-db
```

## Show info about Config Server Service instance  

```bash
cf service workshop-config-server
```

## Unbind app from service 

```bash
cf unbind-service APP_NAME SERVICE_INSTANCE
```

```bash
cf unbind-service workshop-fortune-service workshop-config-server
```

## Remove a service

```bash
cf delete-service SERVICE_INSTANCE
```

```bash
cf delete-service workshop-config-server
```

## Show applications

```bash
cf apps
```

## Delete application

```bash
cf delete APP_NAME
```

## Scale up a service to 2 instances

```bash
cf scale APP_NAME [-i INSTANCES]
```

## Documentation

[Using the Cloud Foundry Command Line Interface (cf CLI)](https://docs.cloudfoundry.org/cf-cli/)
[Cloud Foundry CLI Reference Guide](http://cli.cloudfoundry.org/en-US/cf/)
[Managing Service Instances with the cf CLI](https://docs.cloudfoundry.org/devguide/services/managing-services.html)