#!/usr/bin/env bash

docker-compose down

#docker volume rm servidor_v-zookeeper-log
#docker volume rm servidor_v-kafka-1-log
#docker volume rm v-zookeeper
#docker volume rm v-kafka-1

docker volume rm $(docker volume ls -q --filter dangling=true | grep v-kafka)

source up2.sh