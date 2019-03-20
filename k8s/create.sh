#!/usr/bin/env bash

export TS_G_ENV=calabasas
export UAA_ADMIN_PASSWORD=$(cat ~/Downloads/${TS_G_ENV}.json | jq -r .pks_api.uaa_admin_password)
pks login -a api.pks.${TS_G_ENV}.cf-app.com -u admin -p ${UAA_ADMIN_PASSWORD} -k


pks create-cluster ${TS_G_ENV}-pks-cluster-1 --external-hostname cluster-1.${TS_G_ENV}.cf-app.com --plan medium

# connect loadbalancer backend

pks get-credentials calabasas-pks-cluster-1


kubectl create -f k8s/rbac-config.yml
helm init --service-account tiller

helm install -f values.yaml stable/prometheus --name prometheus --namespace monitoring
helm install -f values.yaml stable/grafana --name grafana --namespace monitoring