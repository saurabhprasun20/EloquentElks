#!/bin/bash

docker-compose -f docker-compose.yml down
./clean_up_databases.sh

#POI API
rm -rf .eloquent-elks-poi-api/build/libs/*
./eloquent-elks-poi-api/gradlew -p ./eloquent-elks-poi-api enunciate
./eloquent-elks-poi-api/gradlew -p ./eloquent-elks-poi-api build

#Recommender API
rm -rf .eloquent-elks-recommender-api/build/libs/*
./eloquent-elks-recommender-api/gradlew -p ./eloquent-elks-recommender-api enunciate
./eloquent-elks-recommender-api/gradlew -p ./eloquent-elks-recommender-api build

#Airbnb API
rm -rf .eloquent-elks-airbnb-api/build/libs/*
./eloquent-elks-airbnb-api/gradlew -p ./eloquent-elks-airbnb-api enunciate
./eloquent-elks-airbnb-api/gradlew -p ./eloquent-elks-airbnb-api build

# POI Density API
rm -rf ./eloquent-elks-poi-density-api/dist/*
npm install --prefix ./eloquent-elks-poi-density-api
npm run compile --prefix ./eloquent-elks-poi-density-api

docker-compose -f docker-compose-dev.yml pull
docker-compose -f docker-compose-dev.yml build
docker-compose -f docker-compose-dev.yml up -d --force-recreate