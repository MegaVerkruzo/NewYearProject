#!/bin/bash

./gradlew build

docker-compose up --build -d
