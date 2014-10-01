#!/bin/bash

# This is a set-up script for running Gatling on a BBC CentOS build.

echo "Goto HOME"
cd

echo "installing wget"
sudo yum install -y wget

echo "download SBT 0.13.6 RPM"
wget "https://dl.bintray.com/sbt/rpm/sbt-0.13.6.rpm"

echo "Installing SBT RPM"
sudo yum install -y "sbt-0.13.6.rpm"

echo "Linux Tuning"
sudo sh -c "printf '*       soft    nofile  65535 \n*       hard    nofile  65535' >> /etc/security/limits.conf"
sudo sh -c "printf 'UseLogin yes' >> /etc/ssh/sshd_config" 
# more ports for testing
sudo sysctl -w net.ipv4.ip_local_port_range="1025 65535"
# increase the maximum number of possible open file descriptors:
echo 300000 | sudo tee /proc/sys/fs/nr_open
echo 300000 | sudo tee /proc/sys/fs/file-max

exit
