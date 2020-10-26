#!/usr/bin/env bash

docker run \
 -d \
 -p 1521:1521 \
 --name oracle-xe-11g \
 -e ORACLE_ALLOW_REMOTE=true \
 -v $(pwd)/.github/workflows/pipeline-with-kafka/oracle-xe-11g/seed:/docker-entrypoint-initdb.d \
 wnameless/oracle-xe-11g-r2