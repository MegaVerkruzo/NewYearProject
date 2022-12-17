#!/bin/bash

sudo apt-get install ufw
sudo ufw default deny incoming
sudo ufw default allow outgoing
sudo ufw allow ssh
sudo ufw allow 22141

sudo ufw enable

# http

sudo ufw allow http
sudo ufw allow https
sudo ufw allow 4153/tcp
sudo ufw allow 4153/udp
sudo ufw allow 8080/tcp
sudo ufw allow 8080/udp

