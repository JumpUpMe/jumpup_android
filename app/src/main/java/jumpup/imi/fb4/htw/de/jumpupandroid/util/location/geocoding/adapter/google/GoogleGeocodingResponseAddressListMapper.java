package jumpup.imi.fb4.htw.de.jumpupandroid.util.location.geocoding.adapter.google;

import android.location.Address;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import jumpup.imi.fb4.htw.de.jumpupandroid.R;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.TechnicalErrorException;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.JsonMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.MapperFactory;

/**
 * Project: jumpup_android
 * <p/>
 * Mapper for https://developers.google.com/maps/documentation/geocoding/intro result list.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 01.03.2016
 */
public class GoogleGeocodingResponseAddressListMapper extends JsonMapper<List<Address>> {
    private static final String FIELD_RESULTS = "results";
    private static final String FIELD_STATUS = "status";
    private static final String STATUS_OK = "OK";

    private JsonMapper<Address> addressJsonMapper = MapperFactory.newAddressJsonMapper();

    @Override
    public List<Address> mapResponse(String response) throws JSONException {
        if (!checkStatus(response)) {
            throw new IllegalStateException("Google Geocoding Service returned error:\n" + response);
        }

        List<Address> addresses = new ArrayList<>();

        fillAddressByResponse(addresses, response);

        return addresses;
    }

    private boolean checkStatus(String response) throws JSONException {
        JSONObject object = new JSONObject(response);

        String status = object.getString(FIELD_STATUS);

        if (status.equals(STATUS_OK)) {
            return true;
        }

        return false;
    }

    private void fillAddressByResponse(List<Address> address, String response) throws JSONException {
        JSONObject jsonObject = new JSONObject(response);

        JSONArray results = jsonObject.getJSONArray(FIELD_RESULTS);

        for (int i=0; i < results.length(); i++) {
            String tripJson = results.getJSONObject(i).toString();

            address.add(addressJsonMapper.mapResponse(tripJson));
        }
    }

    @Override
    public String marshalEntity(List<Address> entity) throws JSONException {
        throw new UnsupportedOperationException("marshalEntity() is nut supported");
    }
}
