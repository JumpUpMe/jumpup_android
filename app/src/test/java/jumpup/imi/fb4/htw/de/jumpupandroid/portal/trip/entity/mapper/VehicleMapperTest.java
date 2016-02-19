package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.mapper;

import junit.framework.Assert;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import jumpup.imi.fb4.htw.de.jumpupandroid.lib.EntityMapperTest;
import jumpup.imi.fb4.htw.de.jumpupandroid.lib.TestObjects;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Vehicle;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.JsonMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.MapperFactory;

/**
 * Project: jumpup_android
 * <p/>
 * Test of Vehicle class.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.02.2016
 */
public class VehicleMapperTest extends EntityMapperTest<Vehicle> {
    private static final String TEST_RESPONSE_SUCCESS = "{" + "\"identity\":10" +
            ",\"creationTimestamp\":1453800546000," +
            "\"updateTimestamp\":null }";
    private static final Vehicle EXPECTED_VEHICLE = TestObjects.newTestVehicle();
    private static final String EXPECTED_JSON = "{" + "\"identity\":10" +
            ",\"creationTimestamp\":1453800546000," +
            "\"updateTimestamp\":null }";

    private VehicleMapper driverMapper;

    @Before
    public void setUp() {
        driverMapper = MapperFactory.newVehicleMapper();
    }

    @Override
    protected Vehicle givenTheExpectedEntity() {
        return EXPECTED_VEHICLE;
    }

    @Override
    protected JsonMapper<Vehicle> getMapper() {
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
