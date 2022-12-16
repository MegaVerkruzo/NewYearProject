#!/bin/bash

printDone() {
  echo -e "\033[42m$1\033[0m"
}

text="Delete previous process"
echo "$text"
pid=$(top -b -n 1 | grep "java" | awk '{print $1}')
kill "$pid"
printDone "$text"

text="Launch PostgreSQL"
echo "$text"
docker-compose up -d
printDone "$text"

# echo "Init tables"
docker cp ./src/main/resources/schema.sql backend-postgres-1:/docker-entrypoint-initdb.d/schema.sql
docker exec -u postgres backend-postgres-1 psql postgres postgres -f docker-entrypoint-initdb.d/schema.sql
# echo "Done: Init tables"

text="Build project"
echo "$text"
./gradlew build
printDone "$text"

echo "Launch server"
java -jar ./build/libs/backend-0.0.1-SNAPSHOT.jar &
# java -jar ./build/libs/backend-0.0.1-SNAPSHOT.jar --server.port=8081
