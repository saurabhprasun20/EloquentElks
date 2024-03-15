import {getPOIDensityDBDomain} from "../server/api/helpers/getPOIDensityDBDomain";

describe('getPOIDensityDBDomain test', () => {
  test('must match expected URL', async () => {
    process.env.ELOQUENTELKS_POI_DENSITY_DB_HOST = "example.tld";
    process.env.ELOQUENTELKS_POI_DENSITY_DB_PORT = "13337"
    let domain = getPOIDensityDBDomain();
    expect(domain).toBe("mongodb://example.tld:13337");
  });
});
