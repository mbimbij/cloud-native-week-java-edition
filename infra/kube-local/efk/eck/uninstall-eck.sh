#!/bin/bash

shopt -s expand_aliases
source /home/joseph/.bashrc

helm -n logging uninstall fluent-bit
kubectl get namespaces --no-headers -o custom-columns=:metadata.name \
  | xargs -n1 kubectl delete elastic --all -n
kubectl delete -f https://download.elastic.co/downloads/eck/1.8.0/operator.yaml
kubectl delete -f https://download.elastic.co/downloads/eck/1.8.0/crds.yaml