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


