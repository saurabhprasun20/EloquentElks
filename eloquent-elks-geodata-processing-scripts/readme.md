# EloquentElks – Geodata processing scripts
This repository is not exposed as an API but rather includes an R Markdown file with which we pre-processed the POI-Data 
from [OpenStreetMap](https://www.openstreetmap.org/).

## Documentation
As OpenStreetMap is a crowdsourced project, the Data often needs to be processed before it can be use.
The "GeoData_Processing.Rmd"-File shows the steps we took to process the data such that it can be consumed by or poi-api
and presented in the frontend. 
We transformed the data to a more meaningful Coordinate Reference System (CRS), Combined some amenity types (e.g. arts_center and art_center) 
and calculated the centroids of polygons to only have point data (Which reduces the data size).
