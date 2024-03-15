import {getAirBnBDomain} from "../helpers/getDomain";
import {getVersion} from "../helpers/getVersion";

/**
 * This is a get request on the airbnb service to get the airbnbs
 * @returns {list}
 */
export async function getAirbnbs(bbox, priceRange) {
    // bbox = {north, east, south, west}
    const min = priceRange[0]
    const max = priceRange[1]
    let params = {min, max};
    let requestUrl
    if(bbox) {
        requestUrl = `${getAirBnBDomain()}/api/${getVersion()}/airbnb?` + new URLSearchParams(params).toString() + `&` + new URLSearchParams(bbox).toString()
    }
    else {
        requestUrl = `${getAirBnBDomain()}/api/${getVersion()}/airbnb?` + new URLSearchParams(params).toString()
    }
    let response = await fetch(requestUrl,
        {method: "GET"})
    if (response.ok) {
        let data = await response.json()
        return data
    } else {
        console.log(response.error)
        alert('Something went wrong. Contact the developers')
    }
}
