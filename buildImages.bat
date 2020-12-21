REM build data generator image
cd ./services/data-generator
call docker build -f src/main/docker/Dockerfile.jvm -t "data-generator:latest"  .
cd ../..
