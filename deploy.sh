#!/bin/bash

sudo add-apt-repository --yes ppa:eugenesan/ppa
sudo apt-get update -y
sudo apt-get install jq -y

curl https://raw.githubusercontent.com/silinternational/ecs-deploy/master/ecs-deploy | sudo tee -a /usr/bin/ecs-deploy

sudo chmod +x /usr/bin/ecs-deploy

ecs-deploy -c $CLUSTER_NAME -n $SERVICE_NAME -i $AWS_REPO_URL:latest -r $AWS_REGION -t 240
