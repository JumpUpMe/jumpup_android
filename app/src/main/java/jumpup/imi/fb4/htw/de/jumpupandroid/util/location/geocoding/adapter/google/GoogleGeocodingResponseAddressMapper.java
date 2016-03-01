package jumpup.imi.fb4.htw.de.jumpupandroid.util.location.geocoding.adapter.google;

import android.location.Address;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.JsonMapper;

/**
 * Project: jumpup_android
 * <p/>
 * Mapper for https://developers.google.com/maps/documentation/geocoding/intro single result.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 01.03.2016
 */
public class GoogleGeocodingResponseAddressMapper extends JsonMapper<Address> {
    private static final String FIELD_GEOMETRY = "geometry";
    private static final String FIELD_GEOMETRY_LOCATION = "location";
    private static final String FIELD_GEOMETRY_LOCATION_LAT = "lat";
    private static final String FIELD_GEOMETRY_LOCATION_LNG = "lng";

    @Override
    public Address mapResponse(String response) throws JSONException {
        Address address = new Address(Locale.getDefault());

        fillAddressByResponse(address, response);

        return address;
    }

    private void fillAddressByResponse(Address address, String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);

        fillGeometry(address, jsonObject);;
    }

    private void fillGeometry(Address address, JSONObject jsonObject) throws JSONException {
        JSONObject geometry = jsonObject.getJSONObject(FIELD_GEOMETRY);
        JSONObject location = geometry.getJSONObject(FIELD_GEOMETRY_LOCATION);

        double latitude = location.getDouble(FIELD_GEOMETRY_LOCATION_LAT);
        double longitude = location.getDouble(FIELD_GEOMETRY_LOCATION_LNG);

        address.setLongitude(longitude);
        address.setLatitude(latitude);
    }

    @Override
    public String marshalEntity(Address entity) throws JSONException {
        throw new UnsupportedOperationException("marshalEntity() is nut supported");
    }
}
