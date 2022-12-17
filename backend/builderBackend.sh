#!/bin/bash

printDone() {
  echo -e "\033[42mDone: $1\033[0m"
}

text="Delete previous process (y/n)"
read -p "$text" x
if [[ "$x" == "y" ]]
then
	pids=$(top -b -n 1 | grep "java" | awk '{print $1}' | tr "\n" " ")
	echo "$pids"
	read -p "Is it a server? (y/n)" isServer
	if [[ "$isServer" != "y" ]]
	then
		pids=$(echo "$pids" | tr " " "\n" | sed '1d' | tr "\n" " ")
		echo $pids
	fi
# 	kill $(top -b -n 1 | grep "java" | awk '{print $1}' | tr "\n" " ")
	printDone "$text"
fi

text="Launch PostgreSQL (y/n)"
read -p "$text" x
if [[ "$x" == "y" ]]
then
	docker-compose down
	docker-compose up -d
	printDone "$text"
fi

text="Clear DataBase (y/n)"
read -p "$text" x
if [[ "$x" == "y" ]]
then
	read -p "Is it server?" isServer
	if [[ "$isServer" == "y" ]]
	then
		docker cp ./src/main/resources/schema.sql backend-postgres-1:/docker-entrypoint-initdb.d/schema.sql
		docker exec -u postgres backend-postgres-1 psql postgres postgres -f docker-entrypoint-initdb.d/schema.sql
	else
		docker cp ./src/main/resources/schema.sql backend_postgres_1:/docker-entrypoint-initdb.d/schema.sql
		docker exec -u postgres backend_postgres_1 psql postgres postgres -f docker-entrypoint-initdb.d/schema.sql
	fi
	printDone "$text"
fi

text="Build project (y/n)"
read -p "$text" x
if [[ "$x" == "y" ]]
then
	./gradlew build --stacktrace
	printDone "$text"
fi

text="Launch server (y/n)"
read -p "$text" x
if [[ "$x" == "y" ]]
then
	java -jar ./build/libs/backend-0.0.1-SNAPSHOT.jar &
fi
