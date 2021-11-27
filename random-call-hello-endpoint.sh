#!/bin/bash

calc() { awk "BEGIN{print $*}"; }

for i in {1..10} ; do
    next_batch_size=$(rand -M 20)
    echo "next_batch_size: $next_batch_size"
    for j in $(seq 1 "$next_batch_size"); do
      curl localhost:8080/hello -w"\n"
    done
    sleep_time=$(calc 0.1* $(rand -M 20))
    echo "sleep_time: $sleep_time"
    sleep $sleep_time
done