# EloquentElks - Airbnb API

Airbnb API has functionality to provide all the properties of New York listed in our database.
This is used by our [frontend](../eloquent-elks-frontend/README.md) to fetch the listing and display it on the map.
The application is deployed on the port 1340.

## Documentation
To check the Swagger API documentation, please use the below link -

http://localhost:1340/docs/ui/index.html

All the endpoints are present here to test. Presently, you don't need any auth key to interact with our API.

### Javadoc
For source code documentation, please refer to our [Javadocs](https://niels89.github.io/EloquentElks/eloquent-elks-airbnb-api/javadoc/index.html).

## How does it work?
The API interacts with our MonogDB database in order to fetch all the listing. There are price filer which will enable
the users to fetch the listing with a certain price range and bounding box filter which fetches all the records with in a particular bounding box defined by longitude and latitude of area pointed out in the screen.
