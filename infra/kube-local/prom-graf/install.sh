#!/bin/bash

./configure-promgraf-helm-repos.sh

helm upgrade -i prometheus prometheus-community/prometheus \
    -f prometheus-values.yml \
    --namespace prometheus \
    --set alertmanager.persistentVolume.storageClass="standard" \
    --set server.persistentVolume.storageClass="standard" \
    --create-namespace

helm upgrade -i grafana grafana/grafana \
    --namespace grafana \
    --set persistence.storageClassName="standard" \
    --set persistence.enabled=true \
    --set adminPassword='EKS!sAWSome' \
    --values grafana.yaml \
    --set service.type=LoadBalancer \
    --create-namespace