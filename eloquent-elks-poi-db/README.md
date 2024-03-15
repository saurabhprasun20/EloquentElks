
# EloquentElks - POI Database Seed 
Every mongodb in the eloquent-elks application has its dedicated database seed. The purpose of this seed is to prefill data into the respective mongodb container. We found this procedure to be the most stable way of seeding a mongodb container.
## Instructions
First and foremost: **DO NOT CHANGE ANYTHING** in the dbseed folder. The contents of this folder are used to build a seeding docker image, which will be executed at the first startup of the application.

The dbseed folder contains the Dockerfile for building the docker image, as well as a JSON file with the seed data. The seeding process is defined in `init.sh` inside the scripts folder. It uses mongoimport to load the data into the specified mongodb container.

## Specialities of the POI database
The POI database has an additional step that it needs for initialization. In order to be able to run the geospatial queries of the POI API, it is necessary to create a spherical index on the database. This is done in by copying the `init.js` file inside the `mongodb` folder into the docker image at build time. By doing that, the database will automatically execute the script and create the needed index.

## Data Source
This data originates from [OpenStreetMap](https://www.openstreetmap.org/) and was extracted with [Overpass Turbo](https://overpass-turbo.eu/)