#!/bin/bash

sudo snap install core; sudo snap refresh core

sudo apt-get remove certbot
sudo dnf remove certbot
sudo yum remove certbot

sudo snap install --classic certbot

sudo ln -s /snap/bin/certbot /usr/bin/certbot

sudo certbot --nginx

sudo certbot certonly --nginx

sudo certbot renew --dry-run


