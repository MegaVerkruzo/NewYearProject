#!/bin/bash

./gradlew build

docker-compose up --build

read -p "Do you want to reinstall database? (y/n)" x
if [[ "$x" == "y" ]]
then
  read -p "Is it a server? (y/n) " x
  if [[ "$x" == "y" ]]
  then
    docker cp ./src/main/resources/schema.sql backend-PostgresSQL-1:/docker-entrypoint-initdb.d/schema.sql
    docker exec -u postgres backend-PostgresSQL-1 psql postgres postgres -f docker-entrypoint-initdb.d/schema.sql
  else
    docker cp ./src/main/resources/schema.sql backend_PostgresSQL_1:/docker-entrypoint-initdb.d/schema.sql
    docker exec -u postgres backend_PostgresSQL_1 psql postgres postgres -f docker-entrypoint-initdb.d/schema.sql
  fi
fi
