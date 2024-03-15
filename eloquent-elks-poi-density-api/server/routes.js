import poiDensityRouter from './api/controllers/poiDensityRouter';

export default function routes(app) {
  app.use('/api/v1/poiDensity', poiDensityRouter);
}
