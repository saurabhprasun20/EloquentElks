# EloquentElks - Point of Interest API
The Point of Interest (POI) API is responsible for providing the Points of Interest to the [frontend](../eloquent-elks-frontend/README.md) as well as other services (currently [POI density API](../eloquent-elks-poi-density-api/README.md)).

## Documentation

### Swagger UI
This API is documented with Swagger UI. To access it, run the service (i.e. as a docker container using the script provided in the root directory) and access http://localhost:1337/docs/ui/index.html. The documentation is regenerated every time the `build_docker_containers.sh` script is executed.

### Javadoc
For source code documentation, please refer to our [Javadocs](https://niels89.github.io/EloquentElks/eloquent-elks-poi-api/javadoc/index.html).

## How does it work?
The POI API connects to the POI MongoDB instance, which holds the POI data. The API itself allows to filter Points of Interest by type, which is something the POI Density API makes use of. Also, the API provides distance calculation to landmarks as well as finding PoIs inside a radius of 500 meters around a point. By default, the docker container exposes the application on HTTP port 1337.