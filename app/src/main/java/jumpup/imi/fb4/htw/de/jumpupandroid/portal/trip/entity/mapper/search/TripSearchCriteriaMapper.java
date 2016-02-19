package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.mapper.search;

import org.json.JSONException;
import org.json.JSONObject;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.TripSearchCriteria;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Trip;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.JsonMapper;

/**
 * Project: jumpup_android
 * <p/>
 * [short_description]
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.02.2016
 */
public class TripSearchCriteriaMapper extends JsonMapper<TripSearchCriteria> {
    @Override
    public TripSearchCriteria mapResponse(String response) throws JSONException {
        throw new UnsupportedOperationException("TripSearchCriteria is not meant to be returned by a web service");
    }

    @Override
    public String marshalEntity(TripSearchCriteria entity) throws JSONException {
        JSONObject marshalledTrip = new JSONObject();

        marshalledTrip.put(TripSearchCriteria.FIELD_NAME_DATE_FROM, entity.getDateFrom());
        marshalledTrip.put(TripSearchCriteria.FIELD_NAME_DATE_TO, entity.getDateTo());
        marshalledTrip.put(TripSearchCriteria.FIELD_NAME_END_POINT, entity.getEndPoint());
        marshalledTrip.put(TripSearchCriteria.FIELD_NAME_START_POINT, entity.getStartPoint());
        marshalledTrip.put(TripSearchCriteria.FIELD_NAME_LAT_ENDPOINT, entity.getLatEndPoint());
        marshalledTrip.put(TripSearchCriteria.FIELD_NAME_LONG_ENDPOINT, entity.getLongEndPoint());
        marshalledTrip.put(TripSearchCriteria.FIELD_NAME_LAT_START_POINT, entity.getLatStartPoint());
        marshalledTrip.put(TripSearchCriteria.FIELD_NAME_LONG_START_POINT, entity.getLongStartPoint());
        marshalledTrip.put(TripSearchCriteria.FIELD_NAME_MAX_DISTANCE, entity.getMaxDistance());
        marshalledTrip.put(TripSearchCriteria.FIELD_NAME_PRICE_FROM, entity.getPriceFrom());
        marshalledTrip.put(TripSearchCriteria.FIELD_NAME_PRICE_TO, entity.getPriceTo());

        return marshalledTrip.toString();
    }
}
