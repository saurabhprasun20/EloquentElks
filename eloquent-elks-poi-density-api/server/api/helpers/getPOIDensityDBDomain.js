/**
 * This helper function return the current domain of the Density DB.
 * @returns {string}
 */
export const getPOIDensityDBDomain = () => {
    const host = process.env.ELOQUENTELKS_POI_DENSITY_DB_HOST;
    const port = process.env.ELOQUENTELKS_POI_DENSITY_DB_PORT

    return `mongodb://${host}:${port}`;
};

