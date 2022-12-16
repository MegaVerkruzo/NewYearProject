#!/bin/bash

sudo apt-get install ufw
sudo ufw default deny incoming
sudo ufw default allow outgoing
sudo ufw allow ssh
sudo ufw allow 22141

sudo ufw enable

# http

sudo ufw allow http
sudo ufw allow 80
sudo ufw allow https
sufo ufw allow 443
sudo ufw allow 4152/tcp
sudo ufw allow 4152/udp
sudo ufw allow 8080/tcp
sudo ufw allow 8080/udp

