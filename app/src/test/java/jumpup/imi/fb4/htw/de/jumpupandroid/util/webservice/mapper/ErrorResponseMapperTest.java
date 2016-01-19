package jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import jumpup.imi.fb4.htw.de.jumpupandroid.lib.EntityMapperTest;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.response.ErrorResponse;

/**
 * Project: jumpup_android
 * <p/>
 * [short_description]
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.01.2016
 */
public class ErrorResponseMapperTest extends EntityMapperTest<ErrorResponse> {

    private static final ErrorResponse EXPECTED_ENTITY = newExpectedErrorResponse();
    private static final String RAW_ERROR_RESPONSE = "{\"success\":false," +
            "\"errors\":[\"The eMail address is already registered on JumpUp. Please use the forgot password function.\"]" +
            ",\"fieldName\":\"eMail\"}";
    private JsonMapper<ErrorResponse> errorMapper;

    private static ErrorResponse newExpectedErrorResponse() {
        ErrorResponse errorResponse = new ErrorResponse();

        errorResponse.setSuccess(false);
        errorResponse.setFailedValidationFieldName("eMail");
        String[] errorMessages = {"The eMail address is already registered on JumpUp. Please use the forgot password function."};
        errorResponse.setErrorMessages(errorMessages);

        return errorResponse;
    }

    @Before
    public void setUp()
    {
        this.errorMapper = (ErrorResponseMapper) MapperFactory.newErrorResponseMapper();
    }

    @Override
    protected ErrorResponse givenTheExpectedEntity() {
        return EXPECTED_ENTITY;
    }

    @Override
    protected JsonMapper<ErrorResponse> getMapper() {
        return errorMapper;
    }

    @Override
    protected String givenTheSuccessfulResponse() {
        return RAW_ERROR_RESPONSE;
    }

    @Override
    protected String givenTheExpectedMarshalledJSON() {
        return null;
    }

    @Override
    public void testSuccessfulMarshalling() throws JSONException {
        assertUnsupportedOperationExceptionOnMarshalEntity();
    }

}
