#! /bin/bash
helm upgrade -i elasticsearch stable/elasticsearch
helm upgrade -i fluentd stable/fluentd
#helm upgrade -i fluentd bitnami/fluentd
helm upgrade -i kibana stable/kibana -f kibana-values.yaml