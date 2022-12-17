#!/bin/bash

./gradlew build

read -p "Do you want to reinstall database? (y/n)" x
if [[ "$x" == "y" ]]
then
  docker cp ./src/main/resources/schema.sql backend-PostgresSQL-1:/docker-entrypoint-initdb.d/schema.sql
  docker exec -u postgres backend-PostgresSQL-1 psql postgres postgres -f docker-entrypoint-initdb.d/schema.sql
fi

docker-compose up --build -d
