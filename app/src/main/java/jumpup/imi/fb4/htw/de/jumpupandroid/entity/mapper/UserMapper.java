package jumpup.imi.fb4.htw.de.jumpupandroid.entity.mapper;

import org.json.JSONException;
import org.json.JSONObject;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;

/**
 * Project: jumpup_android
 * <p/>
 * [short_description]
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 18.01.2016
 */
@SuppressWarnings("RedundantThrows")
public class UserMapper extends JsonMapper<User> {
    @SuppressWarnings("unused")
    private static final String TAG = UserMapper.class.getName();

    @Override
    public User mapResponse(String response) throws JSONException {
        User user = new User();

        this.parseResponseAndFillUser(user, response);

        return user;
    }

    private void parseResponseAndFillUser(User user, String response) throws JSONException {
        JSONObject jsonResponse = new JSONObject(response);

        user.setIdentity(parseIdentity(jsonResponse));
        user.setCreationTimestamp(parseCreationTimestamp(jsonResponse));
        user.setUpdateTimestamp(parseUpdateTimestamp(jsonResponse));
        user.setUsername(parseUsername(jsonResponse));
        user.seteMail(parseEMail(jsonResponse));
        user.setPrename(parsePrename(jsonResponse));
        user.setLastname(parseLastname(jsonResponse));
        user.setTown(parseTown(jsonResponse));
        user.setCountry(parseCountry(jsonResponse));
        user.setLocale(parseLocale(jsonResponse));
        user.setIsConfirmed(parseIsConfirmed(jsonResponse));
        user.setDateOfBirth(parseDateOfBirth(jsonResponse));
        user.setPlaceOfBirth(parsePlaceOfBirth(jsonResponse));
        user.setGender(parseGender(jsonResponse));
        user.setMobileNumber(parseMobileNumber(jsonResponse));
        user.setSkype(parseSkype(jsonResponse));
    }

    private String parseUsername(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.getString(User.FIELD_NAME_USERNAME);
    }

    private String parseEMail(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.getString(User.FIELD_EMAIL);
    }

    private String parsePrename(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.getString(User.FIELD_PRENAME);
    }

    private String parseLastname(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.getString(User.FIELD_LASTNAME);
    }

    private String parseTown(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.optString(User.FIELD_TOWN);
    }

    private String parseCountry(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.optString(User.FIELD_COUNTRY);
    }

    private String parseLocale(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.optString(User.FIELD_LOCALE);
    }

    private Boolean parseIsConfirmed(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.optBoolean(User.FIELD_IS_CONFIRMED);
    }

    private Long parseDateOfBirth(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.optLong(User.FIELD_DATE_OF_BIRTH);
    }

    private String parsePlaceOfBirth(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.optString(User.FIELD_PLACE_OF_BIRTH);
    }

    private String parseGender(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.optString(User.FIELD_GENDER);
    }

    private String parseMobileNumber(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.optString(User.FIELD_MOBILE_NUMBER);
    }

    private String parseSkype(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.optString(User.FIELD_SKYPE);
    }
}
