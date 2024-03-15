db = new Mongo().getDB("eloquent-elks");
db.createCollection('feature', {capped : false});
db.feature.createIndex({ geometry: "2dsphere"});