package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.mapper;

import org.json.JSONArray;
import org.json.JSONException;

import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Trip;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.TripList;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.JsonMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.MapperFactory;

/**
 * Project: jumpup_android
 * <p/>
 * Mapper between JSON responses and TripList entities.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 26.01.2016
 */
public class TripListMapper extends JsonMapper<TripList> {
    private TripMapper tripMapper = (TripMapper) MapperFactory.newTripMapper();

    @Override
    public TripList mapResponse(String response) throws JSONException {
        TripList tripList = new TripList();

        iterateOverJSONTripsAndAddToList(response, tripList);

        return tripList;
    }

    protected void iterateOverJSONTripsAndAddToList(String response, TripList tripList) throws JSONException {
        JSONArray tripsArray = new JSONArray(response);

        for (int i=0; i < tripsArray.length(); i++) {
            String tripJson = tripsArray.getJSONObject(i).toString();

            tripList.add(tripMapper.mapResponse(tripJson));
        }
    }

    @Override
    public String marshalEntity(TripList entity) throws JSONException {
        JSONArray tripsArray = new JSONArray();

        for (Trip trip: entity) {
            tripsArray.put(tripMapper.marshalEntity(trip));
        }

        return tripsArray.toString();
    }
}
