#!/bin/bash

mongoimport --host mongodb_poi --db eloquent-elks --collection feature --type json --file /poi.json --jsonArray && 
mongoimport --host mongodb_poi --db eloquent-elks --collection feature --type json --file /famous.json --jsonArray