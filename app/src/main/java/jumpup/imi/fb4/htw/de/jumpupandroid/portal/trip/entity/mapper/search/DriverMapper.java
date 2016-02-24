package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.mapper.search;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search.Driver;
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
public class DriverMapper extends JsonMapper<Driver> {
    private Driver driver;

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public Driver mapResponse(String response) throws JSONException {
        if (null == driver) {
            throw new IllegalStateException("You need to set a Driver before.");
        }

        JSONObject jsonObject = new JSONObject(response);

        driver.setUsername(parseAndFillUsername(jsonObject));
        driver.setEmail(parseAndFillEMail(jsonObject));
        driver.setPrename(parseAndFillPrename(jsonObject));
        driver.setLastname(parseAndFillLastname(jsonObject));
        driver.setTown(parseAndFillTown(jsonObject));
        driver.setCountry(parseAndFillCountry(jsonObject));
        driver.setGender(parseAndFillGender(jsonObject));
        driver.setMobileNumber(parseAndFillMobileNumber(jsonObject));
        driver.setSkype(parseAndFillSkype(jsonObject));
        driver.setSpokenLanguages(parseAndFillSpokenLanguages(jsonObject));
        driver.setUrl(parseAndFillUrl(jsonObject));

        return driver;
    }

    private String parseAndFillUrl(JSONObject jsonObject) throws JSONException {
        return jsonObject.getString(Driver.FIELD_NAME_URL);
    }

    private Set<String> parseAndFillSpokenLanguages(JSONObject jsonObject) throws JSONException {
        JSONArray languagesJSON = jsonObject.getJSONArray(Driver.FIELD_NAME_SPOKEN_LANGUAGES);

        Set<String> langs = new HashSet<>();

        for (int i=0; i < languagesJSON.length(); i++) {
            String lang = languagesJSON.getJSONObject(i).toString();

            langs.add(lang);
        }

        return langs;
    }

    private String parseAndFillSkype(JSONObject jsonObject) throws JSONException {
        return jsonObject.getString(Driver.FIELD_NAME_SKYPE);
    }

    private String parseAndFillMobileNumber(JSONObject jsonObject) throws JSONException {
        return jsonObject.getString(Driver.FIELD_NAME_MOBILE_NUMBER);
    }

    private String parseAndFillGender(JSONObject jsonObject) throws JSONException {
        return jsonObject.getString(Driver.FIELD_NAME_GENDER);
    }

    private String parseAndFillCountry(JSONObject jsonObject) throws JSONException {
        return jsonObject.getString(Driver.FIELD_NAME_COUNTRY);
    }

    private String parseAndFillTown(JSONObject jsonObject) throws JSONException {
        return jsonObject.getString(Driver.FIELD_NAME_TOWN);
    }

    private String parseAndFillLastname(JSONObject jsonObject) throws JSONException {
        return jsonObject.getString(Driver.FIELD_NAME_LASTNAME);
    }

    private String parseAndFillPrename(JSONObject jsonObject) throws JSONException {
        return jsonObject.getString(Driver.FIELD_NAME_PRENAME);
    }

    private String parseAndFillEMail(JSONObject jsonObject) throws JSONException {
        return jsonObject.getString(Driver.FIELD_NAME_EMAIL);
    }

    private String parseAndFillUsername(JSONObject jsonObject) throws JSONException {
        return jsonObject.getString(Driver.FIELD_NAME_USERNAME);
    }

    @Override
    public String marshalEntity(Driver entity) throws JSONException {
        throw new UnsupportedOperationException("Driver is not supposed to be marshalled into a string representation");
    }
}
