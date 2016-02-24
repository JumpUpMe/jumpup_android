package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.mapper.search;

import junit.framework.Assert;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.TripSearchCriteria;
import jumpup.imi.fb4.htw.de.jumpupandroid.lib.EntityMapperTest;
import jumpup.imi.fb4.htw.de.jumpupandroid.lib.TestObjects;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search.Driver;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search.SingleTripQueryResult;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.JsonMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.MapperFactory;

/**
 * Project: jumpup_android
 * <p/>
 * Test of Driver class.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.02.2016
 */
public class DriverMapperTest extends EntityMapperTest<Driver> {
    private static final String TEST_RESPONSE_SUCCESS = "{\n" +
            "   \"username\":\"admin\",\n" +
            "   \"email\":\"admin@groupelite.de\",\n" +
            "   \"prename\":\"Admin\",\n" +
            "   \"lastname\":\"Admin\",\n" +
            "   \"town\":\"Berlin\",\n" +
            "   \"country\":\"Germany\",\n" +
            "   \"gender\":\"MAN\",\n" +
            "   \"mobileNumber\":\"+49 124124 124\",\n" +
            "   \"skype\":\"cof1990\",\n" +
            "   \"spokenLanguages\":[\n" +
            "\n" +
            "   ],\n" +
            "   \"url\":\"http://localhost:8080/jumpup/portal/profile/personal_show.xhtml?u=8\"\n" +
            "}";
    private static final Driver EXPECTED_DRIVER = TestObjects.newTestDriver();
    private static final String EXPECTED_JSON = "{\n" +
            "   \"username\":\"admin\",\n" +
            "   \"email\":\"admin@groupelite.de\",\n" +
            "   \"prename\":\"Admin\",\n" +
            "   \"lastname\":\"Admin\",\n" +
            "   \"town\":\"Berlin\",\n" +
            "   \"country\":\"Germany\",\n" +
            "   \"gender\":\"MAN\",\n" +
            "   \"mobileNumber\":\"+49 124124 124\",\n" +
            "   \"skype\":\"cof1990\",\n" +
            "   \"spokenLanguages\":[\n" +
            "\n" +
            "   ],\n" +
            "   \"url\":\"http://localhost:8080/jumpup/portal/profile/personal_show.xhtml?u=8\"\n" +
            "}";

    private DriverMapper driverMapper;

    @Before
    public void setUp() {
        driverMapper = MapperFactory.newDriverMapper();
        driverMapper.setDriver(givenTheExpectedEntity());
    }

    @Override
    protected Driver givenTheExpectedEntity() {
        return EXPECTED_DRIVER;
    }

    @Override
    protected JsonMapper<Driver> getMapper() {
        return driverMapper;
    }

    @Override
    protected String givenTheSuccessfulResponse() {
        return TEST_RESPONSE_SUCCESS;
    }

    @Override
    protected String givenTheExpectedMarshalledJSON() {
        return EXPECTED_JSON;
    }

    @Test
    public void testSuccessfulMarshalling() throws JSONException {
        try {
            getMapper().marshalEntity(givenTheExpectedEntity());
            Assert.fail("marshalEntity() should throw an UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
        }
    }
}
