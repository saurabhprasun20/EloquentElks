
# EloquentElks - AirBnb Database Seed 
Every mongodb in the eloquent-elks application has its dedicated database seed. The purpose of this seed is to prefill data into the respective mongodb container. We found this procedure to be the most stable way of seeding a mongodb container.
## Instructions
First and foremost: **DO NOT CHANGE ANYTHING** in the dbseed folder. The contents of this folder are used to build a seeding docker image, which will be executed at the first startup of the application.

The dbseed folder contains the Dockerfile for building the docker image, as well as a JSON file with the seed data. The seeding process is defined in `init.sh` inside the scripts folder. It uses mongoimport to load the data into the specified mongodb container.

## Data Source
This data originates from [Kaggle](https://www.kaggle.com/dgomonov/new-york-city-airbnb-open-data).