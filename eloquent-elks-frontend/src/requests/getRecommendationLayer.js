import {getRecommenderDomain} from "../helpers/getDomain";
import {getVersion} from "../helpers/getVersion";

class PromiseWithCancel extends Promise {
    cancel() {
        //Cancel will be implemented by abort controller
    }
}


function isAbortError(error) {
    return error && error.name === "AbortError";
}


/**
 * This is a get request on the POI service to get the pois
 * @returns {list}
 */
export async function getRecommendationLayer(attractionTypes, signal) {
    return new PromiseWithCancel(async resolve => {
        try {
            const response = await fetch(`${getRecommenderDomain()}/api/${getVersion()}/recommendation/area`,
                {
                    method: 'POST',
                    body: JSON.stringify({"attractionTypes": attractionTypes}),
                    headers: {'Content-Type': 'application/json'},
                    signal,
                })

            const data = await response.json()
            resolve(data);
        } catch (ex) {
            if (isAbortError(ex)) {
                console.log(ex.message);
            } else {
                console.log(ex.message)
                alert('Something went wrong. Contact the developers')
            }
        }
    });
}
