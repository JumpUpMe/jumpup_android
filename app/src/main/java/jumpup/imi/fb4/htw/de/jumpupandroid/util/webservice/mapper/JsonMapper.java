package jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper;

import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.AbstractEntity;
import jumpup.imi.fb4.htw.de.jumpupandroid.entity.mapper.UserMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.exception.TechnicalErrorException;

/**
 * Project: jumpup_android
 * <p/>
 * Abstract class for all JSON readers.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 18.01.2016
 */
@SuppressWarnings({"RedundantThrows", "WeakerAccess"})
public abstract class JsonMapper<EntityType> {

    /**
     * Map the given response which should be directly given by a WebService.
     *
     * @param response the raw JSON response as returned by the WebService
     * @return the AbstractEntity
     * @throws JSONException if the raw response couldn't be parsed.
     */
    abstract public EntityType mapResponse(String response) throws JSONException;

    /**
     * Marshal the given entity into a JSON string that is sent during a WebService request.
     * @param entity the entity of interest
     * @return String the built JSON
     */
    abstract public String marshalEntity(EntityType entity) throws JSONException;

    protected Long parseIdentity(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.optLong(AbstractEntity.FIELD_NAME_IDENTITY);
    }

    @SuppressWarnings("WeakerAccess")
    protected Long parseCreationTimestamp(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.optLong(AbstractEntity.FIELD_NAME_CREATION_TIMESTAMP);
    }

    @SuppressWarnings("WeakerAccess")
    protected Long parseUpdateTimestamp(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.optLong(AbstractEntity.FIELD_NAME_UPDATE_TIMESTAMP);
    }

    @NonNull
    /**
     * Get a String array out of a flat JSON array (e.g. an array of simple string messages).
     */
    protected String[] buildStringArray(JSONArray errorMessagesJSONArray) {
        ArrayList<String> errorMessages = new ArrayList<>();
        if (null != errorMessagesJSONArray) {
            for (int i=0; i < errorMessagesJSONArray.length(); i++) {
                String errorMessage = errorMessagesJSONArray.optString(i);

                if (errorMessage != null && errorMessage.trim().length() != 0) {
                    errorMessages.add(errorMessage);
                }
            }
        }

        return errorMessages.toArray(new String[errorMessages.size()]);
    }

    protected void fillAbstractEntity(AbstractEntity abstractEntity, JSONObject jsonResponse) throws JSONException {
        abstractEntity.setIdentity(parseIdentity(jsonResponse));
        abstractEntity.setCreationTimestamp(parseCreationTimestamp(jsonResponse));
        abstractEntity.setUpdateTimestamp(parseUpdateTimestamp(jsonResponse));
    }


    public UserMapper getUserMapper() {
        return (UserMapper) MapperFactory.newUserMapper();
    }


    /**
     * Wrapper method to explicitly allow and set NULL values if a field is null.
     * This extends the default behaviour of JSONObject. If you put a null value, JSONObject won't print the key - value pair in the resulting JSON string.
     * But we want to explictly set NULL values and therefore set JSONObject.NULL.
     *
     * @param fieldKey JSON key
     * @param fieldValue value object
     * @param marshalledEntity the actual JSONObject
     * @throws JSONException
     */
    protected void putAllowNull(String fieldKey, Object fieldValue, JSONObject marshalledEntity) throws JSONException {
        marshalledEntity.putOpt(fieldKey, fieldValue == null ? JSONObject.NULL : fieldValue);
    }
}
