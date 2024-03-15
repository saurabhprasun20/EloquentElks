import * as express from 'express';
import { POIDensityController } from './poiDensityController';
import { PoiDensityService } from '../services/poiDensity.service';
import { getPois } from '../services/getPois.service';

export default express.Router().get('/', function (req, res) {
  new POIDensityController(new PoiDensityService(getPois)).byType(
    req,
    res
  );
});
