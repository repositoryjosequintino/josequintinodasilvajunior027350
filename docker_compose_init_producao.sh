#!/bin/bash

cd api 

./mvnw clean package -DskipTests

cd ..

docker compose up -d --build