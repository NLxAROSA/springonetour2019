#!/bin/bash

USERSFILE=users.txt
DEFAULT_PASSWORD=`cat password.txt`

WORKSHOP_ORG=pcf-workshop

cf target -o ${WORKSHOP_ORG}

for user in `cat ${USERSFILE}`
do
    cf login -a https://api.sys.pushto.cf -u ${user} -p ${DEFAULT_PASSWORD}
    cf cs p.rabbitmq single-node-3.7 workshop-cloud-bus
    cf cs p.mysql db-small workshop-db
    cf cs p-config-server standard workshop-config-server -c '{"git": { "uri": "https://github.com/dcaron/workshop-fortune-service.git", "searchPaths": "configuration", "label": "2-persistent-database-config-server" } }'
    cf cs p-service-registry standard workshop-service-registry
done
