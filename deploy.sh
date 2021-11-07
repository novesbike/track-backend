#!/bin/bash

sudo add-apt-repository ppa:eugenesan/ppa
sudo apt-get update
sudo apt-get install jq -y

curl https://raw.githubusercontent.com/silinternational/ecs-deploy/master/ecs-deploy | sudo tee -a /usr/bin/ecs-deploy

sudo chmod +x /usr/bin/ecs-deploy

ecs-deploy -c $CLUSTER_NAME -n $SERVICE_NAME -i $AWS_REPO_URL:$TRAVIS_JOB_ID -r $AWS_REGION -t 240
