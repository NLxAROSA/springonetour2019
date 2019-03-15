#!/bin/bash

USERSFILE='users.txt'
DEFAULT_PASSWORD=`cat password.txt`

WORKSHOP_ORG=pcf-workshop

cf target -o ${WORKSHOP_ORG}

for user in `cat ${USERSFILE}`
do
    cf login -a https://api.sys.pushto.cf -u ${user} -p ${DEFAULT_PASSWORD}
    cd ../
    cf push
    cd ../workshop-greeting-ui
    cf push
    cd ../workshop-fortune-service/provisioning
done
