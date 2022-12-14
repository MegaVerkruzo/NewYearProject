#!/bin/bash

echo "Launch PostgreSQL"
docker-compose up -d
echo "Done: Launch PostgreSQL"

echo "Init tables"
docker cp ./src/main/resources/schema.sql backend_postgres_1:/docker-entrypoint-initdb.d/schema.sql
docker exec -u postgres backend_postgres_1 psql postgres postgres -f docker-entrypoint-initdb.d/schema.sql
echo "Done: Init tables"

echo "Build project"
./gradlew build
echo "Done: Build project"

echo "Launch server"
java -jar ./build/libs/backend-0.0.1-SNAPSHOT.jar


