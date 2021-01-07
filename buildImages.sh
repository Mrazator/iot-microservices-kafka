#!/bin/sh

# build data generator image
cd ./services/data-generator
mvn clean install -DskipTests 
docker build -f src/main/docker/Dockerfile.jvm -t "data-generator:latest" .
cd ../..
echo "Generator Image Built!"

# build data collector image
cd ./services/data-collector
mvn clean install -DskipTests 
docker build -f src/main/docker/Dockerfile.jvm -t "data-collector:latest" .
cd ../..
echo "Collector Image Built!"

# build data archiver image
cd ./services/data-archiver
mvn clean install -DskipTests 
docker build -f src/main/docker/Dockerfile.jvm -t "data-archiver:latest" .
cd ../..
echo "Archiver Image Built!"

cd ./services/notification-service
# build notification service (all)
docker build -t "notification-service:latest" --target notification-service-all .
# build notification service (cli client)
docker build -t "notification-service-client:latest" --target notification-service-client .
# build notification service (just a server)
docker build -t "notification-service-server:latest" --target notification-service-server .
cd ../..
echo "Notification Images Built!"


