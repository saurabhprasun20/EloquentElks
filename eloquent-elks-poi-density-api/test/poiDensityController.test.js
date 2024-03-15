import { POIDensityController } from '../server/api/controllers/poiDensityController';
import httpMocks from 'node-mocks-http';

let poiDensityServiceMock = {
  async byType(attractionType) {
    if (attractionType) {
      return new Promise((resolve) => {
        process.nextTick(() => {
          resolve({
            type: 'FeatureCollection',
            features: [
              {
                type: 'Feature',
                properties: {
                  poiCount: 0,
                  id: 0,
                },
                geometry: {
                  type: 'Polygon',
                  coordinates: [[[-74.57080229744054, 40.41846628754955]]],
                },
              },
            ],
          });
        });
      });
    } else {
      return null;
    }
  },
};

describe('poiDensityController test', () => {
  test('should 418', async () => {
    const req = httpMocks.createRequest({});
    const res = httpMocks.createResponse();
    const mockDensityController = new POIDensityController(
      poiDensityServiceMock
    );
    await mockDensityController.byType(req, res);
    expect(res.statusCode).toBe(418);
  });
  test('should 200', async () => {
    const req = httpMocks.createRequest({ query: { attractionType: 'TestAttraction' } });
    const res = httpMocks.createResponse();
    const mockDensityController = new POIDensityController(
      poiDensityServiceMock
    );
    await mockDensityController.byType(req, res);
    expect(res.statusCode).toBe(200);
  });
});
