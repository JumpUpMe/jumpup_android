package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.mapper.search;

import junit.framework.Assert;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.TripSearchCriteria;
import jumpup.imi.fb4.htw.de.jumpupandroid.lib.EntityMapperTest;
import jumpup.imi.fb4.htw.de.jumpupandroid.lib.TestObjects;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Trip;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.mapper.TripMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.JsonMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.MapperFactory;

/**
 * Project: jumpup_android
 * <p/>
 * Test of TripSearchCriteria class.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.02.2016
 */
public class TripSearchCriteriaMapperTest extends EntityMapperTest<TripSearchCriteria> {
    private static final String TEST_RESPONSE_SUCCESS = "{\n" +
            "   \"dateFrom\":\"18.02.2016 17:02\",\n" +
            "   \"dateTo\":\"27.02.2016 22:02\",\n" +
            "   \"endPoint\":\"Trier, Deutschland\",\n" +
            "   \"latEndPoint\":\"49.74999200000001\",\n" +
            "   \"latStartPoint\":\"50.5840512\",\n" +
            "   \"longEndPoint\":\"6.637143299999934\",\n" +
            "   \"longStartPoint\":\"8.678403099999969\",\n" +
            "   \"maxDistance\":\"30\",\n" +
            "   \"priceFrom\":\"0\",\n" +
            "   \"priceTo\":\"100\",\n" +
            "   \"startPoint\":\"Gießen, Deutschland\"\n" +
            "}";
    private static final TripSearchCriteria EXPECTED_TRIP_SEARCH_CRITERIA = TestObjects.newTestTripSearchCriteria();
    private static final String EXPECTED_JSON = "{\n" +
            "   \"dateFrom\":\"18.02.2016 17:02\",\n" +
            "   \"dateTo\":\"27.02.2016 22:02\",\n" +
            "   \"endPoint\":\"Trier, Deutschland\",\n" +
            "   \"latEndPoint\":\"49.74999200000001\",\n" +
            "   \"latStartPoint\":\"50.5840512\",\n" +
            "   \"longEndPoint\":\"6.637143299999934\",\n" +
            "   \"longStartPoint\":\"8.678403099999969\",\n" +
            "   \"maxDistance\":\"30\",\n" +
            "   \"priceFrom\":\"0\",\n" +
            "   \"priceTo\":\"100\",\n" +
            "   \"startPoint\":\"Gießen, Deutschland\"\n" +
            "}";

    private TripSearchCriteriaMapper tripMapper;

    @Before
    public void setUp() {
        tripMapper = MapperFactory.newTripSearchCriteriaMapper();
    }

    @Override
    protected TripSearchCriteria givenTheExpectedEntity() {
        return EXPECTED_TRIP_SEARCH_CRITERIA;
    }

    @Override
    protected JsonMapper<TripSearchCriteria> getMapper() {
        return tripMapper;
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
    public void testSuccessfulResponse() throws JSONException {
        try {
            getMapper().mapResponse(TEST_RESPONSE_SUCCESS);
            Assert.fail("mapResponse should throw an UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
        }
    }

    @Test
    public void testSuccessfulMarshalling() throws JSONException {
        super.testSuccessfulMarshalling();
    }
}
