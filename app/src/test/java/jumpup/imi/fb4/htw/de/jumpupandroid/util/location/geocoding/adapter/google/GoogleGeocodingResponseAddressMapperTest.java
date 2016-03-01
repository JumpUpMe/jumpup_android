package jumpup.imi.fb4.htw.de.jumpupandroid.util.location.geocoding.adapter.google;

import android.location.Address;

import junit.framework.Assert;

import org.json.JSONException;
import org.junit.Before;

import jumpup.imi.fb4.htw.de.jumpupandroid.lib.EntityMapperTest;
import jumpup.imi.fb4.htw.de.jumpupandroid.lib.TestObjects;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.TripList;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.mapper.TripListMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.JsonMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.MapperFactory;

/**
 * Project: jumpup_android
 * <p/>
 * Test of Address mapper used in geocoding.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 01.03.2016
 */
public class GoogleGeocodingResponseAddressMapperTest extends EntityMapperTest<Address> {
    private static final String TEST_RESPONSE_SUCCESS = "{\n" +
            "   \"address_components\":[\n" +
            "      {\n" +
            "         \"long_name\":\"1600\",\n" +
            "         \"short_name\":\"1600\",\n" +
            "         \"types\":[\n" +
            "            \"street_number\"\n" +
            "         ]\n" +
            "      },\n" +
            "      {\n" +
            "         \"long_name\":\"Amphitheatre Pkwy\",\n" +
            "         \"short_name\":\"Amphitheatre Pkwy\",\n" +
            "         \"types\":[\n" +
            "            \"route\"\n" +
            "         ]\n" +
            "      },\n" +
            "      {\n" +
            "         \"long_name\":\"Mountain View\",\n" +
            "         \"short_name\":\"Mountain View\",\n" +
            "         \"types\":[\n" +
            "            \"locality\",\n" +
            "            \"political\"\n" +
            "         ]\n" +
            "      },\n" +
            "      {\n" +
            "         \"long_name\":\"Santa Clara County\",\n" +
            "         \"short_name\":\"Santa Clara County\",\n" +
            "         \"types\":[\n" +
            "            \"administrative_area_level_2\",\n" +
            "            \"political\"\n" +
            "         ]\n" +
            "      },\n" +
            "      {\n" +
            "         \"long_name\":\"California\",\n" +
            "         \"short_name\":\"CA\",\n" +
            "         \"types\":[\n" +
            "            \"administrative_area_level_1\",\n" +
            "            \"political\"\n" +
            "         ]\n" +
            "      },\n" +
            "      {\n" +
            "         \"long_name\":\"United States\",\n" +
            "         \"short_name\":\"US\",\n" +
            "         \"types\":[\n" +
            "            \"country\",\n" +
            "            \"political\"\n" +
            "         ]\n" +
            "      },\n" +
            "      {\n" +
            "         \"long_name\":\"94043\",\n" +
            "         \"short_name\":\"94043\",\n" +
            "         \"types\":[\n" +
            "            \"postal_code\"\n" +
            "         ]\n" +
            "      }\n" +
            "   ],\n" +
            "   \"formatted_address\":\"1600 Amphitheatre Parkway, Mountain View, CA 94043, USA\",\n" +
            "   \"geometry\":{\n" +
            "      \"location\":{\n" +
            "         \"lat\":37.4224764,\n" +
            "         \"lng\":-122.0842499\n" +
            "      },\n" +
            "      \"location_type\":\"ROOFTOP\",\n" +
            "      \"viewport\":{\n" +
            "         \"northeast\":{\n" +
            "            \"lat\":37.4238253802915,\n" +
            "            \"lng\":-122.0829009197085\n" +
            "         },\n" +
            "         \"southwest\":{\n" +
            "            \"lat\":37.4211274197085,\n" +
            "            \"lng\":-122.0855988802915\n" +
            "         }\n" +
            "      }\n" +
            "   },\n" +
            "   \"place_id\":\"ChIJ2eUgeAK6j4ARbn5u_wAGqWA\",\n" +
            "   \"types\":[\n" +
            "      \"street_address\"\n" +
            "   ]\n" +
            "}";
    private static final Address EXPECTED_ADDRESS = TestObjects.newTestAddress();
    private static final String EXPECTED_JSON = "";

    private JsonMapper<Address> tripListMapper;

    @Before
    public void setUp() {
        tripListMapper = (GoogleGeocodingResponseAddressMapper) MapperFactory.newGoogleGeocodingResponseAddressMapper();
    }

    @Override
    protected Address givenTheExpectedEntity() {
        return EXPECTED_ADDRESS;
    }

    @Override
    protected JsonMapper<Address> getMapper() {
        return tripListMapper;
    }

    @Override
    protected String givenTheSuccessfulResponse() {
        return TEST_RESPONSE_SUCCESS;
    }

    @Override
    protected String givenTheExpectedMarshalledJSON() {
        return EXPECTED_JSON;
    }

    @Override
    public void testSuccessfulMarshalling() throws JSONException {
        try {
            getMapper().marshalEntity(givenTheExpectedEntity());

            Assert.fail("Should throw an unsupported operation exception.");
        } catch (UnsupportedOperationException e) {
            // everything is all right
        }
    }
}
