#!/bin/bash

printDone() {
  echo -e "\033[42m$1\033[0m"
}

text="Download image of forestAdmin"
echo "$text"
docker pull forestadmin/toolbelt
printDone "$text"

text="Login to forestAdmin"
echo "$text"
docker run --rm --init -it -v ~/.forest.d:/usr/src/cli/.forest.d -e TOKEN_PATH="/usr/src/cli" forestadmin/toolbelt login
printDone "$text"

# May it isn't correct
text="Connect"
echo "$text"
docker run --rm --init -it -v "$PWD":/usr/src/app -v ~/.forest.d:/usr/src/cli/.forest.d -e TOKEN_PATH="/usr/src/cli" --network host forestadmin/toolbelt projects:create "NewYear" --databaseConnectionURL "postgres://postgres:postgres@localhost:3030/postgres" --applicationHost "localhost" --applicationPort "3310" --databaseSchema "public"
printDone "$text"

text="Launch"
echo "$text"
cd "NewYear"
docker-compose up --build
printDone "$text"