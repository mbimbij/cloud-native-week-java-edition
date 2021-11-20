#! /bin/bash

k apply -f ../common-efk.yml
k apply -f ../../metrics-server.yml

kubectl apply -f common-efk.yml

helm -n logging upgrade -i elasticsearch bitnami/elasticsearch -f elasticsearch-values-bitnami.yml
helm -n logging upgrade -i fluent-bit fluent/fluent-bit -f fluentbit-values.yml
helm -n logging upgrade -i kibana -f kibana-values.yml  bitnami/kibana
