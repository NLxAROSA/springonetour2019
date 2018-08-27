#!/bin/bash

USERSFILE=users.txt
WORKSHOP_ORG=ing-workshop

for user in `cat ${USERSFILE}`
do
    SPACENAME=`echo ${user} | awk -F"@" '{print $1}'`
    echo "Removing user: ${user}"
    cf delete-user ${user} -f
    echo "Removing space: ${SPACENAME}"
    cf delete-space ${SPACENAME} -f
done
