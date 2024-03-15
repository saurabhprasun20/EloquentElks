import * as turf from '@turf/turf';
import {getPOIDensityfromDB, writePOIDensityToDB} from "./poiDensityDB.service";

export class PoiDensityService {
  constructor(getPois) {
    this.getPois = getPois;
  }

  // Function from https://github.com/turf-junkyard/turf-count/blob/master/index.js
  // The junkyard from turf should not be use anymore we copied the function from there and take responsibility.
  countPointInPolygons(polyFC, ptFC, outField) {
    for (let i = 0; i < polyFC.features.length; i++) {
      let poly = polyFC.features[i];
      if (!poly.properties) poly.properties = {};
      let values = 0;
      for (let j = 0; j < ptFC.features.length; j++) {
        let pt = ptFC.features[j];
        if (turf.booleanPointInPolygon(pt, poly)) {
          values++;
        }
      }
      poly.properties[outField] = values;
      poly.properties['id'] = i;
    }
    polyFC.features = polyFC.features.filter(
      (polygon) => polygon.properties[outField] > 0
    );
    return polyFC;
  }

  createPointList(pois) {
    return pois.map((poi) => {
      return turf.point([poi.longitude, poi.latitude]);
    });
  }

  mapPoisToFeatureCollection(pois) {
    let pointList = this.createPointList(pois);
    return turf.featureCollection(pointList);
  }

  calculateDensity(pois, gridLayer) {
    let pointLayer = this.mapPoisToFeatureCollection(pois);
    return this.countPointInPolygons(gridLayer, pointLayer, 'poiCount');
  }

  async byType(attractionType) {
    let density = await getPOIDensityfromDB(attractionType)
    if (density !== null) {
      return density;
    }

    let pois = await this.getPois(attractionType);

    let bbox = [-74.6, 40.41, -73.71, 40.95];
    let cellSide = 0.25;
    let options = { units: 'kilometers' };
    let gridLayer = turf.hexGrid(bbox, cellSide, options);

    let calcDensity = this.calculateDensity(pois, gridLayer);
    calcDensity.attractionType = attractionType;
    await writePOIDensityToDB(calcDensity);
    return calcDensity;
  }
}
