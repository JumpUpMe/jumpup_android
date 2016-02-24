package jumpup.imi.fb4.htw.de.jumpupandroid.lib;

import java.util.ArrayList;
import java.util.List;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.TripSearchCriteria;
import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.database.TripMetaInfo;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Trip;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.TripForPassenger;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.TripList;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Vehicle;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search.Driver;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search.SingleTripQueryResult;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search.TripQueryResults;

/**
 * Project: jumpup_android
 * <p/>
 * [short_description]
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 20.01.2016
 */
public class TestObjects {

    public static User newTestUser() {
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
        user.setDateOfBirth(648691200L);
        user.setPlaceOfBirth("Linz am Rhein");
        user.setGender("MAN");
        user.setMobileNumber("+49 124124 124");
        user.setSkype("cof1990");

        return user;
    }

    public static Trip newTestTripFromBerlinToCologne() {
        Trip trip = new Trip();

        trip.setIdentity(10L);
        trip.setCreationTimestamp(1454955135L);
        trip.setUpdateTimestamp(0L);
        trip.setStartpoint("Berlin, Deutschland");
        trip.setEndpoint("Cologne, Deutschland");
        trip.setLatStartpoint(52.5200119);
        trip.setLongStartpoint(6.96027939999999);
        trip.setLatEndpoint(50.937531);
        trip.setLongEndpoint(6.96027939999999);
        trip.setStartDateTime(1456531200L);
        trip.setEndDateTime(1456531200L);
        trip.setPrice(90.0);
        trip.setOverViewPath("52.520010000000006,13.404950000000001;52.512220000000006,13.31473;52.46312,13.228760000000001;52.442800000000005,13.203080000000002;52.42296,13.195310000000001;52.411660000000005,13.196980000000002;52.39103000000001,13.160870000000001;52.37181,13.14994;52.34351,13.140600000000001;52.30724000000001,13.08815;52.3036,12.998320000000001;52.305460000000004,12.886030000000002;52.31504,12.866650000000002;52.328570000000006,12.826260000000001;52.33816,12.80413;52.34022,12.71319;52.34705,12.6156;52.351290000000006,12.55756;52.30127,12.472090000000001;52.28622000000001,12.45307;52.252590000000005,12.215580000000001;52.23274000000001,12.05353;52.235380000000006,11.95804;52.2334,11.898460000000002;52.22789,11.790880000000001;52.22106,11.703040000000001;52.19017,11.62799;52.185790000000004,11.447280000000001;52.187490000000004,11.35022;52.19762000000001,11.257930000000002;52.203410000000005,11.18543;52.20841000000001,11.16004;52.208490000000005,11.11616;52.21477,11.092260000000001;52.21752000000001,11.06568;52.22771,11.036760000000001;52.243900000000004,11.008000000000001;52.2601,10.990210000000001;52.26681000000001,10.974900000000002;52.27886,10.93439;52.284800000000004,10.89967;52.306650000000005,10.84035;52.31199,10.80732;52.312670000000004,10.765030000000001;52.30823,10.71823;52.30915,10.67294;52.31168,10.587280000000002;52.31382000000001,10.523150000000001;52.33008,10.451130000000001;52.339290000000005,10.373240000000001;52.33511000000001,10.291300000000001;52.33744,10.23475;52.34639000000001,10.20899;52.35557000000001,10.18914;52.35934,10.169630000000002;52.36365000000001,10.12066;52.36880000000001,10.068850000000001;52.379400000000004,10.03161;52.388870000000004,9.99003;52.39768,9.894960000000001;52.404410000000006,9.865150000000002;52.420210000000004,9.835550000000001;52.42925,9.813820000000002;52.43085000000001,9.79471;52.42779,9.7247;52.43054000000001,9.696290000000001;52.42510000000001,9.653210000000001;52.421170000000004,9.6211;52.423390000000005,9.578790000000001;52.417500000000004,9.52569;52.33614000000001,9.41122;52.289620000000006,9.358910000000002;52.21562,9.182310000000001;52.219620000000006,9.14776;52.22142,9.065040000000002;52.22037,9.038390000000001;52.22223,9.021730000000002;52.212610000000005,8.93844;52.209680000000006,8.91491;52.21121,8.885670000000001;52.20861000000001,8.85219;52.15294,8.80654;52.13412,8.740160000000001;52.11206000000001,8.73484;52.04410000000001,8.651570000000001;52.00697,8.62578;51.986740000000005,8.61494;51.96705000000001,8.602580000000001;51.9386,8.53668;51.90905000000001,8.50941;51.89922000000001,8.47058;51.87239,8.406730000000001;51.861250000000005,8.3519;51.850660000000005,8.31433;51.834970000000006,8.261230000000001;51.82466,8.19115;51.78134000000001,8.041110000000002;51.769560000000006,8.007620000000001;51.72429,7.980220000000001;51.70244,7.976730000000001;51.6227,7.813490000000001;51.613980000000005,7.766280000000001;51.601580000000006,7.711950000000001;51.601760000000006,7.6908;51.524170000000005,7.658580000000001;51.454860000000004,7.5526100000000005;51.40462,7.472980000000001;51.39327,7.442540000000001;51.35868000000001,7.37497;51.33413,7.322220000000001;51.31483000000001,7.274590000000001;51.273860000000006,7.256950000000001;51.228950000000005,7.23209;51.21835,7.249180000000001;51.19773000000001,7.2424800000000005;51.17249,7.238080000000001;51.119110000000006,7.1561200000000005;51.06819,7.0817000000000005;51.05673,7.0568100000000005;51.04041,7.003310000000001;51.038790000000006,6.975630000000001;51.023610000000005,6.9415700000000005;51.02393000000001,6.9429300000000005;50.98181,6.967840000000001;50.93526000000001,6.962980000000001;50.93753,6.960280000000001;");
        trip.setViaWaypoints("");
        trip.setNumberOfSeats(3);
        trip.setVehicle(null);
        trip.setDriver(null);
        trip.setCancelationDateTime(null);
        trip.setDistanceMeters(572948);
        trip.setDurationSeconds(20248);

        return trip;
    }

    public static TripList newTestTripList() {
        TripList tripList = new TripList();

        tripList.add(newTestTripFromBerlinToCologne());

        return tripList;
    }

    public static TripMetaInfo newTestTripMetaInfo() {
        TripMetaInfo tripMetaInfo = new TripMetaInfo();

        tripMetaInfo.setLastSyncTimestampSeconds(1456531200L);
        tripMetaInfo.setUserId(10);

        return tripMetaInfo;
    }

    public static TripSearchCriteria newTestTripSearchCriteria() {
        TripSearchCriteria tripSearchCriteria = new TripSearchCriteria();

        tripSearchCriteria.setDateFrom("18.02.2016 17:02");
        tripSearchCriteria.setDateTo("27.02.2016 22:02");
        tripSearchCriteria.setEndPoint("Trier, Deutschland");
        tripSearchCriteria.setLatEndPoint(49.74999200000001);
        tripSearchCriteria.setLatStartPoint(50.5840512);
        tripSearchCriteria.setLongEndPoint(6.637143299999934);
        tripSearchCriteria.setLongStartPoint(8.678403099999969);
        tripSearchCriteria.setMaxDistance(30);
        tripSearchCriteria.setPriceFrom(0.0f);
        tripSearchCriteria.setPriceTo(100.0f);
        tripSearchCriteria.setStartPoint("Gießen, Deutschland");

        return tripSearchCriteria;
    }

    public static Driver newTestDriver() {
        Driver driver =  new Driver();

        fillTestDriver(driver);

        return driver;
    }

    protected static void fillTestDriver(Driver driver) {
        driver.setUsername("admin");
        driver.setEmail("admin@groupelite.de");
        driver.setPrename("Admin");
        driver.setLastname("Admin");
        driver.setTown("Berlin");
        driver.setCountry("Germany");
        driver.setGender("MAN");
        driver.setMobileNumber("+49 124124 124");
        driver.setSkype("cof1990");

        driver.setUrl("http://localhost:8080/jumpup/portal/profile/personal_show.xhtml?u=8");
    }

    public static Vehicle newTestVehicle() {
        Vehicle v = new Vehicle();

        return v;
    }

    public static SingleTripQueryResult newTestSingleTripQueryResult() {
        SingleTripQueryResult result = new SingleTripQueryResult();

        fillTestDriver(result.getDriver());
        result.setVehicle(newTestVehicle());
        result.setTrip(newPassengerTestTripFromBerlinToTrier());

        return result;
    }

    private static Trip newPassengerTestTripFromBerlinToTrier() {
        TripForPassenger trip = new TripForPassenger();

        trip.setIdentity(35L);
        trip.setCreationTimestamp(0L);
        trip.setUpdateTimestamp(0L);
        trip.setStartpoint("Berlin, Deutschland");
        trip.setEndpoint("Trier, Deutschland");
        trip.setLatStartpoint(52.5200119);
        trip.setLongStartpoint(13.404946500000051);
        trip.setLatEndpoint(49.749993);
        trip.setLongEndpoint(6.6371388000000024);
        trip.setStartDateTime("25.02.2016 12:02");
        trip.setEndDateTime("25.02.2016 20:02");
        trip.setPrice(90.0);
        trip.setNumberOfSeats(2);
        trip.setVehicle(null);
        trip.setDriver(null);
        trip.setCancelationDateTime(null);
        trip.setOverViewPath("");
        trip.setViaWaypoints("");

        trip.setBookingUrl("http://localhost:8080/jumpup/portal/trip/booking.xhtml?t=35&s=Gie%C3%9Fen%2C+Deutschland&e=Trier%2C+Deutschland&sL=50.5840512&sLo=8.678403099999969&eL=49.74999200000001&eLo=6.637143299999934&h=8658");
        trip.setDistanceFromPassengersLocation(27.163660162983625);
        trip.setDistanceFromPassengersDestination(26.40829837253887);

        return trip;
    }

    public static TripQueryResults newTestTripQueryResultDirectTripFound() {
        TripQueryResults results = new TripQueryResults();

        List<SingleTripQueryResult> singleTripQueryResultList = new ArrayList<>();
        singleTripQueryResultList.add(newTestSingleTripQueryResult());

        results.setTrips(singleTripQueryResultList);

        return results;
    }


}
