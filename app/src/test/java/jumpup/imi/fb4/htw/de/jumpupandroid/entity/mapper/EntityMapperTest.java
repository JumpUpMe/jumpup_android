package jumpup.imi.fb4.htw.de.jumpupandroid.entity.mapper;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jumpup.imi.fb4.htw.de.jumpupandroid.BaseTest;

/**
 * Project: jumpup_android
 * <p/>
 * Generic class for all entity mapper tests.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.01.2016
 */
public abstract class EntityMapperTest<EntityType> extends BaseTest {
    private final Pattern PATTERN_JSON_KEY_VALUE = Pattern.compile("\"(.*?)\":\"?(.*?)\"?[,}]+");

    protected abstract EntityType givenTheExpectedEntity();

    protected abstract JsonMapper<EntityType> getMapper();

    protected abstract String givenTheSuccessfulResponse();

    protected abstract String givenTheExpectedMarshalledJSON();

    /**
     * Test a successful response (Happy case).
     * @throws JSONException
     */
    @Test
    public void testSuccessfulResponse() throws JSONException {
        // given
        String successResponse = givenTheSuccessfulResponse();

        // when
        EntityType mappedEntity = getMapper().mapResponse(successResponse);

        // then
        Assert.assertNotNull(mappedEntity);
        Assert.assertEquals(givenTheExpectedEntity(), mappedEntity);
    }

    /**
     * Test successful marshalling of an entity into a JSON string (Happy case).
     * @throws JSONException
     */
    @Test
    public void testSuccessfulMarshalling() throws JSONException {
        // given
        EntityType entity = givenTheExpectedEntity();

        // when
        String marshalledJSON = getMapper().marshalEntity(entity);

        // then
        Assert.assertNotNull(marshalledJSON);
        ensureThatKeyValuePairsExist(marshalledJSON, givenTheExpectedMarshalledJSON());
    }

    private void ensureThatKeyValuePairsExist(String marshalledJSON, String expectedJSON) {
        Matcher mExpectedJSON = PATTERN_JSON_KEY_VALUE.matcher(expectedJSON);

        while(mExpectedJSON.find()) {
            String jsonKey = mExpectedJSON.group(1);
            String jsonValue = mExpectedJSON.group(2);

            Assert.assertTrue("given marshalled JSON contains key " + jsonKey,
                marshalledJSON.contains(jsonKey));
            Assert.assertTrue("given marshalled JSON contains value " + jsonValue,
                marshalledJSON.contains(jsonValue));
        }
    }

    protected void assertUnsupportedOperationExceptionOnMapResponse() throws JSONException {
        // given
        String entity = givenTheExpectedMarshalledJSON();

        // when
        try {
            EntityType marshalledJSON = getMapper().mapResponse(entity);

            Assert.fail("No UnsupportedOperationWasThrown.");
        } catch (UnsupportedOperationException e) {
            // then
            Assert.assertTrue("Expected UnsupportedOperation was thrown.", true);
        }
    }
}
