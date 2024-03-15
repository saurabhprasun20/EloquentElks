function createDomainString (protocol, host, port) {
    return `${protocol}://${host}:${port}`;
}


/**
 * This helper function return the domain of the POI-API.
 * @returns {string}
 */
export const getPOIDomain = () => {
    const protocol = process.env.REACT_APP_ELOQUENTELKS_POI_API_PROTOCOL;
    const host = process.env.REACT_APP_ELOQUENTELKS_POI_API_HOST;
    const port = process.env.REACT_APP_ELOQUENTELKS_POI_API_PORT;

    return createDomainString(protocol, host, port);
};

/**
 * This helper function return the domain of the AIRBNB-API.
 * @returns {string}
 */
export const getAirBnBDomain = () => {
    const protocol = process.env.REACT_APP_ELOQUENTELKS_AIRBNB_API_PROTOCOL;
    const host = process.env.REACT_APP_ELOQUENTELKS_AIRBNB_API_HOST;
    const port = process.env.REACT_APP_ELOQUENTELKS_AIRBNB_API_PORT;

    return createDomainString(protocol, host, port);
};

/**
 * This helper function return the domain of the AIRBNB-API.
 * @returns {string}
 */
export const getRecommenderDomain = () => {
    const protocol = process.env.REACT_APP_ELOQUENTELKS_RECOMMENDER_API_PROTOCOL;
    const host = process.env.REACT_APP_ELOQUENTELKS_RECOMMENDER_API_HOST;
    const port = process.env.REACT_APP_ELOQUENTELKS_RECOMMENDER_API_PORT;

    return createDomainString(protocol, host, port);
};