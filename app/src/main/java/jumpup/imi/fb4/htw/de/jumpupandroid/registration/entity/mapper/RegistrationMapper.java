package jumpup.imi.fb4.htw.de.jumpupandroid.registration.entity.mapper;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import jumpup.imi.fb4.htw.de.jumpupandroid.registration.entity.Registration;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.JsonMapper;

/**
 * Project: jumpup_android
 * <p/>
 * Marshals a Registration entity to an JSON string.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.01.2016
 */
public class RegistrationMapper extends JsonMapper<Registration> {
    private static final String TAG = RegistrationMapper.class.getName();

    @Override
    public Registration mapResponse(String response) throws JSONException {
        throw new UnsupportedOperationException("mapResponse() should not be called during registration since the response is not an entity.");
    }

    @Override
    public String marshalEntity(Registration entity) throws JSONException {
        JSONObject registrationJson = new JSONObject();

        Log.d(TAG, "marshalEntity(): " + entity);
        registrationJson.put(Registration.FIELD_NAME_USERNAME, entity.getUsername());
        registrationJson.put(Registration.FIELD_NAME_EMAIL, entity.geteMail());
        registrationJson.put(Registration.FIELD_NAME_PRENAME, entity.getPrename());
        registrationJson.put(Registration.FIELD_NAME_LASTNAME, entity.getLastname());
        registrationJson.put(Registration.FIELD_NAME_PASSWORD, entity.getPassword());
        registrationJson.put(Registration.FIELD_NAME_CONFIRM_PASSWORD, entity.getPassword());

        return registrationJson.toString();
    }
}
