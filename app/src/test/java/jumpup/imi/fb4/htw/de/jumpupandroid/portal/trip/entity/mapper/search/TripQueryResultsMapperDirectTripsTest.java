package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.mapper.search;

import junit.framework.Assert;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import jumpup.imi.fb4.htw.de.jumpupandroid.lib.EntityMapperTest;
import jumpup.imi.fb4.htw.de.jumpupandroid.lib.TestObjects;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search.SingleTripQueryResult;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search.TripQueryResults;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.JsonMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.MapperFactory;

/**
 * Project: jumpup_android
 * <p/>
 * Test of QueryResultsMapper.
 *
 * In this test, a test response for direct trips is returned.
 * Therefore, we expect an instance of DirectTripsResult to be returned.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.02.2016
 */
public class TripQueryResultsMapperDirectTripsTest extends EntityMapperTest<TripQueryResults> {
    private static final String TEST_RESPONSE_SUCCESS = "{\n" +
            "   \"trips\":[\n" +
            "      {\n" +
            "         \"driver\":{\n" +
            "            \"username\":\"admin\",\n" +
            "            \"email\":\"admin@groupelite.de\",\n" +
            "            \"prename\":\"Admin\",\n" +
            "            \"lastname\":\"Admin\",\n" +
            "            \"town\":\"Berlin\",\n" +
            "            \"country\":\"Germany\",\n" +
            "            \"gender\":\"MAN\",\n" +
            "            \"mobileNumber\":\"+49 124124 124\",\n" +
            "            \"skype\":\"cof1990\",\n" +
            "            \"spokenLanguages\":[\n" +
            "\n" +
            "            ],\n" +
            "            \"url\":\"http://localhost:8080/jumpup/portal/profile/personal_show.xhtml?u=8\"\n" +
            "         },\n" +
            "         \"vehicle\":{\n" +
            "            \"manufactor\":\"BMW\"\n" +
            "         },\n" +
            "         \"trip\":{\n" +
            "            \"identity\":35,\n" +
            "            \"startpoint\":\"Berlin, Deutschland\",\n" +
            "            \"endpoint\":\"Trier, Deutschland\",\n" +
            "            \"latStartpoint\":52.5200119,\n" +
            "            \"longStartpoint\":13.404946500000051,\n" +
            "            \"latEndpoint\":49.749993,\n" +
            "            \"longEndpoint\":6.6371388000000024,\n" +
            "            \"startDateTime\":\"25.02.2016 12:02\",\n" +
            "            \"endDateTime\":\"25.02.2016 20:02\",\n" +
            "            \"price\":90.0,\n" +
            "            \"viaWaypoints\":\"\",\n" +
            "            \"numberOfSeats\":2,\n" +
            "            \"numberOfBookings\":0,\n" +
            "            \"bookingUrl\":\"http://localhost:8080/jumpup/portal/trip/booking.xhtml?t=35&s=Gie%C3%9Fen%2C+Deutschland&e=Trier%2C+Deutschland&sL=50.5840512&sLo=8.678403099999969&eL=49.74999200000001&eLo=6.637143299999934&h=8658\",\n" +
            "            \"distanceFromPassengersLocation\":27.163660162983625,\n" +
            "            \"distanceFromPassengersDestination\":26.40829837253887\n" +
            "         }\n" +
            "      }\n" +
            "   ],\n" +
            "   \"translations\":{\n" +
            "      \"to\":\"to\",\n" +
            "      \"locationDistance\":\"Distance to Start\",\n" +
            "      \"startDate\":\"Start Date\",\n" +
            "      \"driver\":\"Driver\",\n" +
            "      \"overallPrice\":\"Overall price\",\n" +
            "      \"numberBookings\":\"Number of bookings\",\n" +
            "      \"destinationDistance\":\"Distance to destination\",\n" +
            "      \"vehicle\":\"Vehicle\",\n" +
            "      \"book\":\"Book\",\n" +
            "      \"bookTooltip\":\"Click to book this trip now\"\n" +
            "   },\n" +
            "   \"type\":\"DIRECT_TRIP_RESULT\"\n" +
            "}";
    private static final TripQueryResults EXPECTED_QUERY_RESULTS = TestObjects.newTestTripQueryResultDirectTripFound();
    private static final String EXPECTED_JSON = "{\n" +
            "   \"trips\":[\n" +
            "      {\n" +
            "         \"driver\":{\n" +
            "            \"username\":\"admin\",\n" +
            "            \"email\":\"admin@groupelite.de\",\n" +
            "            \"prename\":\"Admin\",\n" +
            "            \"lastname\":\"Admin\",\n" +
            "            \"town\":\"Berlin\",\n" +
            "            \"country\":\"Germany\",\n" +
            "            \"gender\":\"MAN\",\n" +
            "            \"mobileNumber\":\"+49 124124 124\",\n" +
            "            \"skype\":\"cof1990\",\n" +
            "            \"spokenLanguages\":[\n" +
            "\n" +
            "            ],\n" +
            "            \"url\":\"http://localhost:8080/jumpup/portal/profile/personal_show.xhtml?u=8\"\n" +
            "         },\n" +
            "         \"vehicle\":{\n" +
            "            \"manufactor\":\"BMW\"\n" +
            "         },\n" +
            "         \"trip\":{\n" +
            "            \"identity\":35,\n" +
            "            \"startpoint\":\"Berlin, Deutschland\",\n" +
            "            \"endpoint\":\"Trier, Deutschland\",\n" +
            "            \"latStartpoint\":52.5200119,\n" +
            "            \"longStartpoint\":13.404946500000051,\n" +
            "            \"latEndpoint\":49.749993,\n" +
            "            \"longEndpoint\":6.6371388000000024,\n" +
            "            \"startDateTime\":\"25.02.2016 12:02\",\n" +
            "            \"endDateTime\":\"25.02.2016 20:02\",\n" +
            "            \"price\":90.0,\n" +
            "            \"viaWaypoints\":\"\",\n" +
            "            \"numberOfSeats\":2,\n" +
            "            \"numberOfBookings\":0,\n" +
            "            \"bookingUrl\":\"http://localhost:8080/jumpup/portal/trip/booking.xhtml?t=35&s=Gie%C3%9Fen%2C+Deutschland&e=Trier%2C+Deutschland&sL=50.5840512&sLo=8.678403099999969&eL=49.74999200000001&eLo=6.637143299999934&h=8658\",\n" +
            "            \"distanceFromPassengersLocation\":27.163660162983625,\n" +
            "            \"distanceFromPassengersDestination\":26.40829837253887\n" +
            "         }\n" +
            "      }\n" +
            "   ],\n" +
            "   \"translations\":{\n" +
            "      \"to\":\"to\",\n" +
            "      \"locationDistance\":\"Distance to Start\",\n" +
            "      \"startDate\":\"Start Date\",\n" +
            "      \"driver\":\"Driver\",\n" +
            "      \"overallPrice\":\"Overall price\",\n" +
            "      \"numberBookings\":\"Number of bookings\",\n" +
            "      \"destinationDistance\":\"Distance to destination\",\n" +
            "      \"vehicle\":\"Vehicle\",\n" +
            "      \"book\":\"Book\",\n" +
            "      \"bookTooltip\":\"Click to book this trip now\"\n" +
            "   },\n" +
            "   \"type\":\"DIRECT_TRIP_RESULT\"\n" +
            "}";

    private JsonMapper<TripQueryResults> singleTripQueryResultMapper;

    @Before
    public void setUp() {
        singleTripQueryResultMapper = MapperFactory.newTripQueryResultsMapper();
    }

    @Override
    protected TripQueryResults givenTheExpectedEntity() {
        return EXPECTED_QUERY_RESULTS;
    }

    @Override
    protected JsonMapper<TripQueryResults> getMapper() {
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
