package eloquentelks.recommender.api;

public class Constants {

    private Constants(){
        throw new IllegalStateException("This class is not meant to be instantiated");
    }

    /**
     * Constant for the feature property id
     */
    public static final String GEOJSON_FEATURE_PROPERTY_ID = "id";

    /**
     * Constant for the feature property poiCount
     */
    public static final String GEOJSON_FEATURE_PROPERTY_POICOUNT = "poiCount";
}
