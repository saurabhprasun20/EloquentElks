import {getPOIDomain} from "../server/api/helpers/getPOIDomain";

describe('getPOIDomain test', () => {
  test('must match expected URL', async () => {
    process.env.ELOQUENTELKS_POI_API_PROTOCOL = "https";
    process.env.ELOQUENTELKS_POI_API_HOST = "example.tld";
    process.env.ELOQUENTELKS_POI_API_PORT = "13337";
    let domain = getPOIDomain();
    expect(domain).toBe("https://example.tld:13337");
  });
});
