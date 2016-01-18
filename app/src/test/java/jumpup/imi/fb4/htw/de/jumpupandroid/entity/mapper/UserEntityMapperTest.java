package jumpup.imi.fb4.htw.de.jumpupandroid.entity.mapper;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jumpup.imi.fb4.htw.de.jumpupandroid.BaseTest;
import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;

/**
 * Project: jumpup_android
 * <p/>
 * Test of UserEntityMapper class.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 18.01.2016
 */
public class UserEntityMapperTest extends BaseTest {
    private static final String TEST_RESPONSE_SUCCESS = "{\"identity\":1,\"creationTimestamp\":null," +
            "\"updateTimestamp\":null,\"username\":\"sascha\",\"eMail\":\"info@groupelite.de\"," +
            "\"prename\":\"Sascha\",\"lastname\":\"Feldmann\",\"town\":\"Berlin\"," +
            "\"country\":\"Germany\",\"locale\":null,\"isConfirmed\":true,\"dateOfBirth\":648691200000," +
            "\"placeOfBirth\":\"Linz am Rhein\",\"gender\":\"MAN\",\"mobileNumber\":\"+49 124124 124\"," +
            "\"skype\":\"cof1990\"}";
    private static final User EXPECTED_USER = newExpectedUser();

    private static User newExpectedUser() {
        User user = new User();

        user.setIdentity(1L);
        user.setCreationTimestamp(0L);
        user.setUpdateTimestamp(0L);
        user.setUsername("sascha");
        user.seteMail("info@groupelite.de");
        user.setPrename("Sascha");
        user.setLastname("Feldmann");
        user.setTown("Berlin");
        user.setCountry("Germany");
        user.setLocale("");
        user.setIsConfirmed(true);
        user.setDateOfBirth(648691200000L);
        user.setPlaceOfBirth("Linz am Rhein");
        user.setGender("MAN");
        user.setMobileNumber("+49 124124 124");
        user.setSkype("cof1990");

        return user;
    }

    private UserMapper userMapper;

    @Before
    public void setUp() {
        userMapper = (UserMapper) MapperFactory.newUserMapper();
    }

    @Test
    public void testSuccessfulResponse() throws JSONException {
        // given
        String successResponse = TEST_RESPONSE_SUCCESS;

        // when
        User mappedUser = userMapper.mapResponse(successResponse);

        // then
        Assert.assertNotNull(mappedUser);
        Assert.assertEquals(EXPECTED_USER, mappedUser);
    }


}
