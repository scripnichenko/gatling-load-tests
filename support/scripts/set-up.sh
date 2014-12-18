#!/bin/bash

# This is a set-up script for running Gatling on a BBC CentOS build.
cd $(dirname $0)

sh "linux-tuning.sh"
sh "bash-config.sh"
sh "vim-install-config.sh"

echo "install netcat"
sudo yum install -y nc.x86_64

echo "Configure git"
git config --global alias.st status
git config --global color.ui true

echo "GNU Screen"
sudo yum install -y screen

echo "REBOOT NOWSKI!"
exit 0
