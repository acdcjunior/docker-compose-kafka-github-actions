#!/usr/bin/env bash

docker run --rm \
 -v $(pwd):/data \
 -e KAFKA_BOOTSTRAP_SERVERS='kafka-broker-1:19092' \
 -e KAFKA_SASL_JAAS_CONFIG='org.apache.kafka.common.security.plain.PlainLoginModule required username="kafka_user_informer" password="kafka-pwd-informer";' \
 -e KAFKA_SASL_MECHANISM='PLAIN' \
 -e KAFKA_SECURITY_PROTOCOL='SASL_PLAINTEXT' \
 --network docker_default \
 devshawn/kafka-gitops:0.2.12 kafka-gitops -v -f /data/state.yaml plan
