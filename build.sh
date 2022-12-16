#!/bin/bash


fun() {
	echo -e "\033[32m$1\033[0m"
}

# apt install nginx 
apt install npm
curl -sL https://deb.nodesource.com/setup_19.x | sudo -E bash -
sudo apt install nodejs
fun "Installed nginx and npm with nodejs"

./sertBolt.sh	
fun "Installed sertBolt"

./AddFirewall.sh
fun "AddFireWall"

# front

cd front

npm i

npm run build

cd ..
fun "Done frontend"

# backend

cd backend

./builderBackend.sh

cd ..
fun "Done backend"

rm /etc/nginx/sites-available/default
cp ./default_my /etc/nginx/sites-available/default
fun "Copy nginx"



