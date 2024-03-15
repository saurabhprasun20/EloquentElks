import {getPOIDensityDBDomain} from "../helpers/getPOIDensityDBDomain";
import mongo from "mongodb";


export async function getPOIDensityfromDB(attractionType) {
    let db;
    let layer;
    try {
        db = await mongo.connect(getPOIDensityDBDomain());
        let dbo = db.db("eloquent-elks");
        let query = { attractionType: attractionType };
        layer = await dbo.collection("featureCollection").findOne(query)
    } catch (err) {
        throw err
    }
    finally {
        db.close()
    }

    return layer
}

export async function writePOIDensityToDB(density) {
    let db;
    try {
        db = await mongo.connect(getPOIDensityDBDomain());
        let dbo = db.db("eloquent-elks");
        let query = { attractionType: density.attractionType };
        let layer = await dbo.collection("featureCollection").findOne(query)
        if (layer === null) {
            await dbo.collection("featureCollection").insertOne(density)
        }
    } catch (err) {
        throw err
    }
    finally {
        db.close()
    }
}
