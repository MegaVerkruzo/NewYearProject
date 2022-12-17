#!/bin/bash


funDone() {
	echo -e "\033[42m$1\033[0m"
}

text="install nginx (y/n)"
read -p "$text" x
if [[ "$x" == "y" ]]
then
	apt install nginx 
	funDone "installed Nginx"
fi

text="install npm (y/n)"
read -p "$text" x
if [[ "$x" == "y" ]]
then
	apt install npm 
	funDone "Installed npm"
fi

text="install nodejs (y/n)"
read -p "$text" x
if [[ "$x" == "y" ]]
then
	curl -sL https://deb.nodesource.com/setup_19.x | sudo -E bash -
	sudo apt install nodejs
 	funDone "Installed nginx and npm with nodejs"
fi


text="install sertBolt (y/n)"
read -p "$text" x
if [[ "$x" == "y" ]]
then
	./sertBolt.sh
	funDone "Done: sertBolt"
fi

text="customize FireWall (y/n)"
read -p "$text" x
if [[ "$x" == "y" ]]
then
	./AddFirewall.sh
	funDone "Done: firewal"
fi

text="frontend install (y/n)"
read -p "$text" x
if [[ "$x" == "y" ]]
then
	cd front
	npm i
	npm run build
	funDone "Done: frontend"
	cd ..
fi

text="backend install (y/n)"
read -p "$text" x
if [[ "$x" == "y" ]]
then
	cd backend
	./buildBackend.sh
	funDone "Done: backend"
	cd ..
fi

text="cp nginx (y/n)"
read -p "$text" x
if [[ "$x" == "y" ]]
then
	rm /etc/nginx/sites-available/default
	cp ./default /etc/nginx/sites-available/default
	fun "Done: copy nginx"
	./reLaunch.sh
	fun "Restart nginx"
fi


