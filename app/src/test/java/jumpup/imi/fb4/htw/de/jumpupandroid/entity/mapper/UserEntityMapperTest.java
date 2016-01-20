package jumpup.imi.fb4.htw.de.jumpupandroid.entity.mapper;

import org.junit.Before;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;
import jumpup.imi.fb4.htw.de.jumpupandroid.lib.EntityMapperTest;
import jumpup.imi.fb4.htw.de.jumpupandroid.lib.TestObjects;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.JsonMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.MapperFactory;

/**
 * Project: jumpup_android
 * <p/>
 * Test of UserEntityMapper class.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 18.01.2016
 */
public class UserEntityMapperTest extends EntityMapperTest<User> {
    private static final String TEST_RESPONSE_SUCCESS = "{\"identity\":1,\"creationTimestamp\":null," +
            "\"updateTimestamp\":null,\"username\":\"sascha\",\"eMail\":\"info@groupelite.de\"," +
            "\"prename\":\"Sascha\",\"lastname\":\"Feldmann\",\"town\":\"Berlin\"," +
            "\"country\":\"Germany\",\"locale\":null,\"isConfirmed\":true,\"dateOfBirth\":648691200000," +
            "\"placeOfBirth\":\"Linz am Rhein\",\"gender\":\"MAN\",\"mobileNumber\":\"+49 124124 124\"," +
            "\"skype\":\"cof1990\"}";
    private static final User EXPECTED_USER = TestObjects.newTestUser();
    private static final String EXPECTED_JSON =  "{\"username\":\"sascha\",\"eMail\":\"info@groupelite.de\"," +
            "\"prename\":\"Sascha\",\"lastname\":\"Feldmann\",\"town\":\"Berlin\"," +
            "\"country\":\"Germany\",\"locale\":\"\",\"isConfirmed\":true,\"dateOfBirth\":648691200000," +
            "\"placeOfBirth\":\"Linz am Rhein\",\"gender\":\"MAN\",\"mobileNumber\":\"+49 124124 124\"," +
            "\"skype\":\"cof1990\"}";

    private UserMapper userMapper;

    @Before
    public void setUp() {
        userMapper = (UserMapper) MapperFactory.newUserMapper();
    }

    @Override
    protected User givenTheExpectedEntity() {
        return EXPECTED_USER;
    }

    @Override
    protected JsonMapper<User> getMapper() {
        return userMapper;
    }

    @Override
    protected String givenTheSuccessfulResponse() {
        return TEST_RESPONSE_SUCCESS;
    }

    @Override
    protected String givenTheExpectedMarshalledJSON() {
        return EXPECTED_JSON;
    }
}
