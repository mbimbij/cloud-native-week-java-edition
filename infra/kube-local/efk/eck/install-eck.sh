#!/bin/bash

shopt -s expand_aliases
source /home/joseph/.bashrc

k apply -f ../common-efk.yml
k apply -f ../../metrics-server.yml

helm repo add elastic https://helm.elastic.co
helm repo update
helm upgrade -i elastic-operator elastic/eck-operator -n elastic-system --create-namespace

k -n logging apply -f elasticsearch-eck.yml
k -n logging apply -f kibana-eck.yml
sleep 10
elasticpassword=$(k -n logging  get secret quickstart-es-elastic-user -o=jsonpath='{.data.elastic}' | base64 --decode; echo)
echo "elasticpassword: $elasticpassword"
helm -n logging upgrade -i fluent-bit fluent/fluent-bit -f fluentbit-values-eck.yml --set elasticpassword=$elasticpassword
