package jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.response.ErrorResponse;

/**
 * Project: jumpup_android
 * <p/>
 * [short_description]
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.01.2016
 */
public class ErrorResponseMapper extends JsonMapper<ErrorResponse> {

    @Override
    public ErrorResponse mapResponse(String response) throws JSONException {
        ErrorResponse errorResponse = new ErrorResponse();

        if (null != response) {
            JSONObject errorResponseJSONObject = new JSONObject(response);
            errorResponse.setSuccess(parseSuccess(errorResponseJSONObject));
            errorResponse.setErrorMessages(parseErrorMessage(errorResponseJSONObject));
            errorResponse.setFailedValidationFieldName(parseFailedValidationFieldName(errorResponseJSONObject));
        }

        return errorResponse;
    }

    private String parseFailedValidationFieldName(JSONObject errorResponseJSONObject) {
        return errorResponseJSONObject.optString(ErrorResponse.FIELD_NAME_VALIDATION_FIELDNAME);
    }

    private boolean parseSuccess(JSONObject errorResponseJSONObject) {
        return errorResponseJSONObject.optBoolean(ErrorResponse.FIELD_NAME_SUCCESS);
    }

    private String[] parseErrorMessage(JSONObject errorResponseJSONObject) {
        JSONArray errorMessagesJSONArray = errorResponseJSONObject.optJSONArray(ErrorResponse.FIELD_NAME_ERRORS);

        return buildStringArray(errorMessagesJSONArray);
    }

    @Override
    public String marshalEntity(ErrorResponse entity) throws JSONException {
        throw new UnsupportedOperationException();
    }
}
