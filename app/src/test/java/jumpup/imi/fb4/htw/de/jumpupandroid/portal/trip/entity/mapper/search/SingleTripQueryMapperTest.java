package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.mapper.search;

import junit.framework.Assert;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import jumpup.imi.fb4.htw.de.jumpupandroid.lib.EntityMapperTest;
import jumpup.imi.fb4.htw.de.jumpupandroid.lib.TestObjects;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search.SingleTripQueryResult;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.JsonMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.MapperFactory;

/**
 * Project: jumpup_android
 * <p/>
 * Test of Single Trip Query mapper class.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.02.2016
 */
public class SingleTripQueryMapperTest extends EntityMapperTest<SingleTripQueryResult> {
    private static final String TEST_RESPONSE_SUCCESS = "{\n" +
            "   \"driver\":{\n" +
            "      \"username\":\"admin\",\n" +
            "      \"email\":\"admin@groupelite.de\",\n" +
            "      \"prename\":\"Admin\",\n" +
            "      \"lastname\":\"Admin\",\n" +
            "      \"town\":\"Berlin\",\n" +
            "      \"country\":\"Germany\",\n" +
            "      \"gender\":\"MAN\",\n" +
            "      \"mobileNumber\":\"+49 124124 124\",\n" +
            "      \"skype\":\"cof1990\",\n" +
            "      \"spokenLanguages\":[\n" +
            "\n" +
            "      ],\n" +
            "      \"url\":\"http://localhost:8080/jumpup/portal/profile/personal_show.xhtml?u=8\"\n" +
            "   },\n" +
            "   \"vehicle\":{\n" +
            "      \"manufactor\":\"BMW\"\n" +
            "   },\n" +
            "   \"trip\":{\n" +
            "      \"identity\":35,\n" +
            "      \"startpoint\":\"Berlin, Deutschland\",\n" +
            "      \"endpoint\":\"Trier, Deutschland\",\n" +
            "      \"latStartpoint\":52.5200119,\n" +
            "      \"longStartpoint\":13.404946500000051,\n" +
            "      \"latEndpoint\":49.749993,\n" +
            "      \"longEndpoint\":6.6371388000000024,\n" +
            "      \"startDateTime\":\"25.02.2016 12:02\",\n" +
            "      \"endDateTime\":\"25.02.2016 20:02\",\n" +
            "      \"price\":90.0,\n" +
            "      \"viaWaypoints\":\"\",\n" +
            "      \"numberOfSeats\":2,\n" +
            "      \"numberOfBookings\":0,\n" +
            "      \"bookingUrl\":\"http://localhost:8080/jumpup/portal/trip/booking.xhtml?t=35&s=Gie%C3%9Fen%2C+Deutschland&e=Trier%2C+Deutschland&sL=50.5840512&sLo=8.678403099999969&eL=49.74999200000001&eLo=6.637143299999934&h=8658\",\n" +
            "      \"distanceFromPassengersLocation\":27.163660162983625,\n" +
            "      \"distanceFromPassengersDestination\":26.40829837253887\n" +
            "   }\n" +
            "}";
    private static final SingleTripQueryResult EXPECTED_SINGLE_TRIP_QUERY_RESULT = TestObjects.newTestSingleTripQueryResult();
    private static final String EXPECTED_JSON = "{\n" +
            "   \"driver\":{\n" +
            "      \"username\":\"admin\",\n" +
            "      \"email\":\"admin@groupelite.de\",\n" +
            "      \"prename\":\"Admin\",\n" +
            "      \"lastname\":\"Admin\",\n" +
            "      \"town\":\"Berlin\",\n" +
            "      \"country\":\"Germany\",\n" +
            "      \"gender\":\"MAN\",\n" +
            "      \"mobileNumber\":\"+49 124124 124\",\n" +
            "      \"skype\":\"cof1990\",\n" +
            "      \"spokenLanguages\":[\n" +
            "\n" +
            "      ],\n" +
            "      \"url\":\"http://localhost:8080/jumpup/portal/profile/personal_show.xhtml?u=8\"\n" +
            "   },\n" +
            "   \"vehicle\":{\n" +
            "      \"manufactor\":\"BMW\"\n" +
            "   },\n" +
            "   \"trip\":{\n" +
            "      \"identity\":35,\n" +
            "      \"startpoint\":\"Berlin, Deutschland\",\n" +
            "      \"endpoint\":\"Trier, Deutschland\",\n" +
            "      \"latStartpoint\":52.5200119,\n" +
            "      \"longStartpoint\":13.404946500000051,\n" +
            "      \"latEndpoint\":49.749993,\n" +
            "      \"longEndpoint\":6.6371388000000024,\n" +
            "      \"startDateTime\":\"25.02.2016 12:02\",\n" +
            "      \"endDateTime\":\"25.02.2016 20:02\",\n" +
            "      \"price\":90.0,\n" +
            "      \"viaWaypoints\":\"\",\n" +
            "      \"numberOfSeats\":2,\n" +
            "      \"numberOfBookings\":0,\n" +
            "      \"bookingUrl\":\"http://localhost:8080/jumpup/portal/trip/booking.xhtml?t=35&s=Gie%C3%9Fen%2C+Deutschland&e=Trier%2C+Deutschland&sL=50.5840512&sLo=8.678403099999969&eL=49.74999200000001&eLo=6.637143299999934&h=8658\",\n" +
            "      \"distanceFromPassengersLocation\":27.163660162983625,\n" +
            "      \"distanceFromPassengersDestination\":26.40829837253887\n" +
            "   }\n" +
            "}";

    private SingleTripQueryResultMapper singleTripQueryResultMapper;

    @Before
    public void setUp() {
        singleTripQueryResultMapper = MapperFactory.newSingleTripQueryResultMapper();
    }

    @Override
    protected SingleTripQueryResult givenTheExpectedEntity() {
        return EXPECTED_SINGLE_TRIP_QUERY_RESULT;
    }

    @Override
    protected JsonMapper<SingleTripQueryResult> getMapper() {
        return singleTripQueryResultMapper;
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
