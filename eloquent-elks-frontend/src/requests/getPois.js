import {getPOIDomain} from "../helpers/getDomain";
import {getVersion} from "../helpers/getVersion";

/**
 * This is a get request on the POI service to get the pois
 * @returns {list}
 */
export async function getPois(lat, lon) {
    let response = await fetch(`${getPOIDomain()}/api/${getVersion()}/poi/radius?longitude=${lon}&latitude=${lat}`, {method: "GET"})
    if (response.ok) {
        let data = await response.json()
        return data
    } else {
        console.log(response.error)
        alert('Something went wrong. Contact the developers')
    }
}