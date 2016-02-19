package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.mapper;

import org.json.JSONException;

import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Vehicle;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search.SingleTripQueryResult;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.JsonMapper;

/**
 * Project: jumpup_android
 * <p/>
 * [short_description]
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.02.2016
 */
public class VehicleMapper extends JsonMapper<Vehicle> {
    private SingleTripQueryResult.Vehicle vehicle;

    @Override
    public Vehicle mapResponse(String response) throws JSONException {
        // TODO implement when feature is available
        return null;
    }

    @Override
    public String marshalEntity(Vehicle entity) throws JSONException {
        return null;
    }

    public void setVehicle(SingleTripQueryResult.Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
