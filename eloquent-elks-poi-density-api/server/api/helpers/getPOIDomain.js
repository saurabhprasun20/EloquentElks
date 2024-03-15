/**
 * This helper function return the domain of the POI-API.
 * @returns {string}
 */
export const getPOIDomain = () => {
  const protocol = process.env.ELOQUENTELKS_POI_API_PROTOCOL;
  const host = process.env.ELOQUENTELKS_POI_API_HOST;
  const port = process.env.ELOQUENTELKS_POI_API_PORT;

  return `${protocol}://${host}:${port}`;
};

