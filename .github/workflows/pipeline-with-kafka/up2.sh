#!/usr/bin/env bash

docker volume create --name=v-kafka-zookeeper
docker volume create --name=v-kafka-broker-1

docker-compose up -d

