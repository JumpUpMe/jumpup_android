package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.mapper;

import org.json.JSONException;
import org.json.JSONObject;

import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Vehicle;
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

    @Override
    public Vehicle mapResponse(String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);

        Vehicle v = new Vehicle();

        return v;
    }

    @Override
    public String marshalEntity(Vehicle entity) throws JSONException {
        throw new UnsupportedOperationException("Vehicle is not supposed yet to be marshalled into a string representation");

    }
}
