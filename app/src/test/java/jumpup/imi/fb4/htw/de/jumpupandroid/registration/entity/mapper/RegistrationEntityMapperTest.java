package jumpup.imi.fb4.htw.de.jumpupandroid.registration.entity.mapper;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.mapper.EntityMapperTest;
import jumpup.imi.fb4.htw.de.jumpupandroid.entity.mapper.JsonMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.entity.mapper.MapperFactory;
import jumpup.imi.fb4.htw.de.jumpupandroid.registration.entity.Registration;

/**
 * Project: jumpup_android
 * <p/>
 * Test of the RegistrationMapper class.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.01.2016
 */
public class RegistrationEntityMapperTest extends EntityMapperTest<Registration> {


    private static final Registration EXPECTED_REGISTRATION_ENTITY = newExpectedRegistrationEntity();
    private static final String EXPECTED_MARSHALLED_JSON = "{\"username\":\"unittestuser\"," +
            "\"eMail\":\"unittest@groupelite.de\"," +
            "\"prename\":\"Max\"," +
            "\"lastname\":\"Tester\"," +
            "\"password\":\"unittestpw\"," +
            "\"confirmPassword\":\"unittestpw\"}";
    private JsonMapper<Registration> registrationMapper;

    private static Registration newExpectedRegistrationEntity() {
        Registration registration = new Registration();

        registration.seteMail("unittest@groupelite.de");
        registration.setUsername("unittestuser");
        registration.setPassword("unittestpw");
        registration.setPrename("Max");
        registration.setLastname("Tester");

        return registration;
    }

    @Before
    public void setUp()
    {
        this.registrationMapper = (RegistrationMapper) MapperFactory.newRegistrationMapper();
    }

    @Override
    protected Registration givenTheExpectedEntity() {
        return EXPECTED_REGISTRATION_ENTITY;
    }

    @Override
    protected JsonMapper<Registration> getMapper() {
        return registrationMapper;
    }

    @Override
    protected String givenTheSuccessfulResponse() {
        return null;
    }

    @Override
    protected String givenTheExpectedMarshalledJSON() {
        return EXPECTED_MARSHALLED_JSON;
    }

    @Test
    public void testSuccessfulResponse() throws JSONException {
        assertUnsupportedOperationExceptionOnMapResponse();
    }

}
