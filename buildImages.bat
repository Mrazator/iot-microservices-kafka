REM build data generator image
cd ./services/data-generator
call mvn clean install -DskipTests
call docker build -f src/main/docker/Dockerfile.jvm -t "data-generator:latest"  .
cd ../..
echo "Generator Image Built!"

REM build data archiver image
cd ./services/data-archiver
call mvn clean install -DskipTests
call docker build -f src/main/docker/Dockerfile.jvm -t "data-archiver:latest"  .
cd ../..
echo "Archiver Image Built!"
