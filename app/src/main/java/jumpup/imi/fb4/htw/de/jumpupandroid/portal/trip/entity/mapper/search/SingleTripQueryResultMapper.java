package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.mapper.search;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Trip;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Vehicle;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.mapper.TripMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.mapper.VehicleMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search.SingleTripQueryResult;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.JsonMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.MapperFactory;

/**
 * Project: jumpup_android
 * <p/>
 * [short_description]
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.02.2016
 */
public class SingleTripQueryResultMapper extends JsonMapper<SingleTripQueryResult> {
    private DriverMapper driverMapper = MapperFactory.newDriverMapper();
    private VehicleMapper vehicleMapper = MapperFactory.newVehicleMapper();
    private JsonMapper<Trip> tripMapper = MapperFactory.newTripMapper();

    @Override
    public SingleTripQueryResult mapResponse(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);

        SingleTripQueryResult singleTripQueryResult = new SingleTripQueryResult();

        fillDriver(singleTripQueryResult, jsonObject);
        fillVehicle(singleTripQueryResult, jsonObject);
        fillTrip(singleTripQueryResult, jsonObject);

        return singleTripQueryResult;
    }

    private void fillDriver(SingleTripQueryResult singleTripQueryResult, JSONObject jsonObject) throws JSONException {
        JSONObject driverJSON = jsonObject.getJSONObject(SingleTripQueryResult.FIELD_NAME_DRIVER);

        driverMapper.setDriver(singleTripQueryResult.getDriver());
        // driver mapper will work on the driver instance above
        driverMapper.mapResponse(driverJSON.toString());
    }

    private void fillVehicle(SingleTripQueryResult singleTripQueryResult, JSONObject jsonObject) throws JSONException {
        JSONObject vehicleJSON = jsonObject.getJSONObject(SingleTripQueryResult.FIELD_NAME_VEHICLE);

        Vehicle vehicle = vehicleMapper.mapResponse(vehicleJSON.toString());
        singleTripQueryResult.setVehicle(vehicle);
    }

    private void fillTrip(SingleTripQueryResult singleTripQueryResult, JSONObject jsonObject) throws JSONException {
        JSONObject tripJson = jsonObject.getJSONObject(SingleTripQueryResult.FIELD_NAME_TRIP);

        Trip trip = tripMapper.mapResponse(tripJson.toString());
        singleTripQueryResult.setTrip(trip);
    }


    @Override
    public String marshalEntity(SingleTripQueryResult entity) throws JSONException {
        throw new UnsupportedOperationException("SingleTripQueryResult is not supposed to be marshalled into a string representation");
    }
}
