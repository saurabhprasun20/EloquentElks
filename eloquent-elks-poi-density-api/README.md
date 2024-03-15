# eloquent-elks-poi-density-api

API that computes the POI density of POIs of a certain type

## Get Started

Get started developing...

```shell
# install deps
npm install

# run in development mode
npm run dev

# run tests
npm run test
```

## Documentation

### Swagger UI

This API is documented with Swagger UI. To access it, run the service (i.e. as a docker container using the script
provided in the root directory) and access http://localhost:1339/swagger. The documentation is in the api.yml file.

## How does it work?

The poi-density api connects to poi-api to get Points of Interest of a certain attraction type (e.g. restaurant). It
then uses the turf.js (https://turfjs.org) library to create a hexagonal grid over New York city (bounding
box: [-74.6, 40.41, -73.71, 40.95]) with a cell side length of 250m. Then we adapted a function from the turf.js
junk-yard to count the amount of points per cell of the hexgrid. The api then removes all the cells that contained no
points and returns the poiCount per cell.

## Install Dependencies

Install all package dependencies (one time operation)

```shell
npm install
```

## Run It

#### Run in *development* mode:

Runs the application is development mode. Should not be used in production

```shell
npm run dev
```

or debug it

```shell
npm run dev:debug
```

#### Run in *production* mode:

Compiles the application and starts it in production production mode.

```shell
npm run compile
npm start
```

## Test It

Run the Mocha unit tests

```shell
npm test
```

or debug them

```shell
npm run test:debug
```

## Try It

* Open your browser to [http://localhost:3001](http://localhost:3001)
* Invoke the `/examples` endpoint
  ```shell
  curl http://localhost:3000/api/v1/examples
  ```

## Debug It

#### Debug the server:

```
npm run dev:debug
```

#### Debug Tests

```
npm run test:debug
```

#### Debug with VSCode

Add these [contents](https://github.com/cdimascio/generator-express-no-stress/blob/next/assets/.vscode/launch.json) to
your `.vscode/launch.json` file

## Lint It

View prettier linter output

```
npm run lint
```

Fix all prettier linter errors

```
npm run lint
```

## Deploy It

Deploy to CloudFoundry

```shell
cf push eloquent-elks-poi-density-api
```

## References

We used https://github.com/cdimascio/generator-express-no-stress to bootstrap the nodejs express scaffolding.


   
