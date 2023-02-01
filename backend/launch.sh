#!/bin/bash

docker-compose down

./gradlew build

read -p "is it background process? (y/n) " x
if [[ "$x" == "y" ]]
then
	docker-compose up --build -d
else
	docker-compose up --build
fi
