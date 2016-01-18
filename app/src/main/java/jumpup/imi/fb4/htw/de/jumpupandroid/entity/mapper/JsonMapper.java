package jumpup.imi.fb4.htw.de.jumpupandroid.entity.mapper;

import org.json.JSONException;
import org.json.JSONObject;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.AbstractEntity;
import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;

/**
 * Project: jumpup_android
 * <p/>
 * Abstract class for all JSON readers.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 18.01.2016
 */
public abstract class JsonMapper<EntityType extends AbstractEntity> {

    /**
     * Map the given response which should be directly given by a WebService.
     *
     * @param response
     * @return the AbstractEntity
     * @throws JSONException if the raw response couldn't be parsed.
     */
    abstract public EntityType mapResponse(String response) throws JSONException;

    protected Long parseIdentity(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.getLong(AbstractEntity.FIELD_NAME_IDENTITY);
    }

    protected Long parseCreationTimestamp(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.optLong(AbstractEntity.FIELD_NAME_CREATION_TIMESTAMP);
    }

    protected Long parseUpdateTimestamp(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.optLong(AbstractEntity.FIELD_NAME_UPDATE_TIMESTAMP);
    }

}
