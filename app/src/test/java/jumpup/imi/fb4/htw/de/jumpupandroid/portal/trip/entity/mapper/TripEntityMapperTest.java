package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.mapper;

import org.junit.Before;

import jumpup.imi.fb4.htw.de.jumpupandroid.lib.EntityMapperTest;
import jumpup.imi.fb4.htw.de.jumpupandroid.lib.TestObjects;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Trip;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.JsonMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.MapperFactory;

/**
 * Project: jumpup_android
 * <p/>
 * Test of TripEntityMapper class.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 26.01.2016
 */
public class TripEntityMapperTest extends EntityMapperTest<Trip> {
    private static final String TEST_RESPONSE_SUCCESS = "{\"identity\":10" +
            ",\"creationTimestamp\":1453800546000," +
            "\"updateTimestamp\":null," +
            "\"startpoint\":\"Berlin, Deutschland\"," +
            "\"endpoint\":\"Cologne, Deutschland\"," +
            "\"latStartpoint\":52.5200119," +
            "\"longStartpoint\":6.96027939999999," +
            "\"latEndpoint\":50.937531," +
            "\"longEndpoint\":6.96027939999999," +
            "\"startDateTime\":1454144460000," +
            "\"endDateTime\":1454166060000," +
            "\"price\":90.0," +
            "\"overViewPath\":\"52.520010000000006,13.404950000000001;52.512220000000006,13.31473;52.46312,13.228760000000001;52.442800000000005,13.203080000000002;52.42296,13.195310000000001;52.411660000000005,13.196980000000002;52.39103000000001,13.160870000000001;52.37181,13.14994;52.34351,13.140600000000001;52.30724000000001,13.08815;52.3036,12.998320000000001;52.305460000000004,12.886030000000002;52.31504,12.866650000000002;52.328570000000006,12.826260000000001;52.33816,12.80413;52.34022,12.71319;52.34705,12.6156;52.351290000000006,12.55756;52.30127,12.472090000000001;52.28622000000001,12.45307;52.252590000000005,12.215580000000001;52.23274000000001,12.05353;52.235380000000006,11.95804;52.2334,11.898460000000002;52.22789,11.790880000000001;52.22106,11.703040000000001;52.19017,11.62799;52.185790000000004,11.447280000000001;52.187490000000004,11.35022;52.19762000000001,11.257930000000002;52.203410000000005,11.18543;52.20841000000001,11.16004;52.208490000000005,11.11616;52.21477,11.092260000000001;52.21752000000001,11.06568;52.22771,11.036760000000001;52.243900000000004,11.008000000000001;52.2601,10.990210000000001;52.26681000000001,10.974900000000002;52.27886,10.93439;52.284800000000004,10.89967;52.306650000000005,10.84035;52.31199,10.80732;52.312670000000004,10.765030000000001;52.30823,10.71823;52.30915,10.67294;52.31168,10.587280000000002;52.31382000000001,10.523150000000001;52.33008,10.451130000000001;52.339290000000005,10.373240000000001;52.33511000000001,10.291300000000001;52.33744,10.23475;52.34639000000001,10.20899;52.35557000000001,10.18914;52.35934,10.169630000000002;52.36365000000001,10.12066;52.36880000000001,10.068850000000001;52.379400000000004,10.03161;52.388870000000004,9.99003;52.39768,9.894960000000001;52.404410000000006,9.865150000000002;52.420210000000004,9.835550000000001;52.42925,9.813820000000002;52.43085000000001,9.79471;52.42779,9.7247;52.43054000000001,9.696290000000001;52.42510000000001,9.653210000000001;52.421170000000004,9.6211;52.423390000000005,9.578790000000001;52.417500000000004,9.52569;52.33614000000001,9.41122;52.289620000000006,9.358910000000002;52.21562,9.182310000000001;52.219620000000006,9.14776;52.22142,9.065040000000002;52.22037,9.038390000000001;52.22223,9.021730000000002;52.212610000000005,8.93844;52.209680000000006,8.91491;52.21121,8.885670000000001;52.20861000000001,8.85219;52.15294,8.80654;52.13412,8.740160000000001;52.11206000000001,8.73484;52.04410000000001,8.651570000000001;52.00697,8.62578;51.986740000000005,8.61494;51.96705000000001,8.602580000000001;51.9386,8.53668;51.90905000000001,8.50941;51.89922000000001,8.47058;51.87239,8.406730000000001;51.861250000000005,8.3519;51.850660000000005,8.31433;51.834970000000006,8.261230000000001;51.82466,8.19115;51.78134000000001,8.041110000000002;51.769560000000006,8.007620000000001;51.72429,7.980220000000001;51.70244,7.976730000000001;51.6227,7.813490000000001;51.613980000000005,7.766280000000001;51.601580000000006,7.711950000000001;51.601760000000006,7.6908;51.524170000000005,7.658580000000001;51.454860000000004,7.5526100000000005;51.40462,7.472980000000001;51.39327,7.442540000000001;51.35868000000001,7.37497;51.33413,7.322220000000001;51.31483000000001,7.274590000000001;51.273860000000006,7.256950000000001;51.228950000000005,7.23209;51.21835,7.249180000000001;51.19773000000001,7.2424800000000005;51.17249,7.238080000000001;51.119110000000006,7.1561200000000005;51.06819,7.0817000000000005;51.05673,7.0568100000000005;51.04041,7.003310000000001;51.038790000000006,6.975630000000001;51.023610000000005,6.9415700000000005;51.02393000000001,6.9429300000000005;50.98181,6.967840000000001;50.93526000000001,6.962980000000001;50.93753,6.960280000000001;\",\"viaWaypoints\":\"\",\"numberOfSeats\":3,\"vehicle\":null,\"driver\":null,\"cancelationDateTime\":null,\"distanceMeters\":572948,\"durationSeconds\":20248},{\"identity\":12,\"creationTimestamp\":1453800554000,\"updateTimestamp\":null,\"startpoint\":\"Berlin, Deutschland\",\"endpoint\":\"Bonn, Deutschland\",\"latStartpoint\":52.5200119,\"longStartpoint\":6.96027939999999,\"latEndpoint\":50.937531,\"longEndpoint\":6.96027939999999,\"startDateTime\":1454144460000,\"endDateTime\":1454166060000,\"price\":90.0,\"overViewPath\":\"52.520010000000006,13.404950000000001;52.512220000000006,13.31473;52.46312,13.228760000000001;52.442800000000005,13.203080000000002;52.42296,13.195310000000001;52.411660000000005,13.196980000000002;52.39103000000001,13.160870000000001;52.37181,13.14994;52.34351,13.140600000000001;52.30724000000001,13.08815;52.3036,12.998320000000001;52.305460000000004,12.886030000000002;52.31504,12.866650000000002;52.328570000000006,12.826260000000001;52.33816,12.80413;52.34022,12.71319;52.34705,12.6156;52.351290000000006,12.55756;52.30127,12.472090000000001;52.28622000000001,12.45307;52.252590000000005,12.215580000000001;52.23274000000001,12.05353;52.235380000000006,11.95804;52.2334,11.898460000000002;52.22789,11.790880000000001;52.22106,11.703040000000001;52.19017,11.62799;52.185790000000004,11.447280000000001;52.187490000000004,11.35022;52.19762000000001,11.257930000000002;52.203410000000005,11.18543;52.20841000000001,11.16004;52.208490000000005,11.11616;52.21477,11.092260000000001;52.21752000000001,11.06568;52.22771,11.036760000000001;52.243900000000004,11.008000000000001;52.2601,10.990210000000001;52.26681000000001,10.974900000000002;52.27886,10.93439;52.284800000000004,10.89967;52.306650000000005,10.84035;52.31199,10.80732;52.312670000000004,10.765030000000001;52.30823,10.71823;52.30915,10.67294;52.31168,10.587280000000002;52.31382000000001,10.523150000000001;52.33008,10.451130000000001;52.339290000000005,10.373240000000001;52.33511000000001,10.291300000000001;52.33744,10.23475;52.34639000000001,10.20899;52.35557000000001,10.18914;52.35934,10.169630000000002;52.36365000000001,10.12066;52.36880000000001,10.068850000000001;52.379400000000004,10.03161;52.388870000000004,9.99003;52.39768,9.894960000000001;52.404410000000006,9.865150000000002;52.420210000000004,9.835550000000001;52.42925,9.813820000000002;52.43085000000001,9.79471;52.42779,9.7247;52.43054000000001,9.696290000000001;52.42510000000001,9.653210000000001;52.421170000000004,9.6211;52.423390000000005,9.578790000000001;52.417500000000004,9.52569;52.33614000000001,9.41122;52.289620000000006,9.358910000000002;52.21562,9.182310000000001;52.219620000000006,9.14776;52.22142,9.065040000000002;52.22037,9.038390000000001;52.22223,9.021730000000002;52.212610000000005,8.93844;52.209680000000006,8.91491;52.21121,8.885670000000001;52.20861000000001,8.85219;52.15294,8.80654;52.13412,8.740160000000001;52.11206000000001,8.73484;52.04410000000001,8.651570000000001;52.00697,8.62578;51.986740000000005,8.61494;51.96705000000001,8.602580000000001;51.9386,8.53668;51.90905000000001,8.50941;51.89922000000001,8.47058;51.87239,8.406730000000001;51.861250000000005,8.3519;51.850660000000005,8.31433;51.834970000000006,8.261230000000001;51.82466,8.19115;51.78134000000001,8.041110000000002;51.769560000000006,8.007620000000001;51.72429,7.980220000000001;51.70244,7.976730000000001;51.6227,7.813490000000001;51.613980000000005,7.766280000000001;51.601580000000006,7.711950000000001;51.601760000000006,7.6908;51.524170000000005,7.658580000000001;51.454860000000004,7.5526100000000005;51.40462,7.472980000000001;51.39327,7.442540000000001;51.35868000000001,7.37497;51.33413,7.322220000000001;51.31483000000001,7.274590000000001;51.273860000000006,7.256950000000001;51.228950000000005,7.23209;51.21835,7.249180000000001;51.19773000000001,7.2424800000000005;51.17249,7.238080000000001;51.119110000000006,7.1561200000000005;51.06819,7.0817000000000005;51.05673,7.0568100000000005;51.04041,7.003310000000001;51.038790000000006,6.975630000000001;51.023610000000005,6.9415700000000005;51.02393000000001,6.9429300000000005;50.98181,6.967840000000001;50.93526000000001,6.962980000000001;50.93753,6.960280000000001;\",\"viaWaypoints\":\"\",\"numberOfSeats\":3,\"vehicle\":null,\"driver\":null,\"cancelationDateTime\":null,\"distanceMeters\":572948,\"durationSeconds\":20248},{\"identity\":14,\"creationTimestamp\":1453800555000,\"updateTimestamp\":null,\"startpoint\":\"Berlin, Deutschland\",\"endpoint\":\"Bonn, Deutschland\",\"latStartpoint\":52.5200119,\"longStartpoint\":7.098194100000001,\"latEndpoint\":50.7374891,\"longEndpoint\":7.098194100000001,\"startDateTime\":1454144460000,\"endDateTime\":1454166060000,\"price\":90.0,\"overViewPath\":\"52.520010000000006,13.404950000000001;52.512220000000006,13.31473;52.46312,13.228760000000001;52.43137,13.19189;52.42296,13.195310000000001;52.411660000000005,13.196980000000002;52.39103000000001,13.160870000000001;52.37181,13.14994;52.33874,13.137120000000001;52.30724000000001,13.08815;52.3036,12.998320000000001;52.305460000000004,12.886030000000002;52.31504,12.866650000000002;52.328570000000006,12.826260000000001;52.33816,12.80413;52.34022,12.71319;52.34705,12.6156;52.351290000000006,12.55756;52.30127,12.472090000000001;52.27799,12.435440000000002;52.26415,12.339310000000001;52.252590000000005,12.215580000000001;52.23274000000001,12.05353;52.235380000000006,11.95804;52.2334,11.898460000000002;52.22594,11.835170000000002;52.22789,11.790880000000001;52.228210000000004,11.75727;52.21873,11.68143;52.19017,11.62799;52.16613,11.506680000000001;52.185790000000004,11.447280000000001;52.18829,11.42279;52.187490000000004,11.35022;52.19762000000001,11.257930000000002;52.20745,11.13888;52.21477,11.092260000000001;52.21752000000001,11.06568;52.22771,11.036760000000001;52.243900000000004,11.008000000000001;52.2601,10.990210000000001;52.26681000000001,10.974900000000002;52.27886,10.93439;52.284800000000004,10.89967;52.306650000000005,10.84035;52.31199,10.80732;52.312670000000004,10.765030000000001;52.30915,10.67294;52.31168,10.587280000000002;52.31382000000001,10.523150000000001;52.33008,10.451130000000001;52.339290000000005,10.373240000000001;52.33511000000001,10.291300000000001;52.33744,10.23475;52.34639000000001,10.20899;52.35934,10.169630000000002;52.36365000000001,10.12066;52.36880000000001,10.068850000000001;52.379400000000004,10.03161;52.388870000000004,9.99003;52.39768,9.894960000000001;52.404410000000006,9.865150000000002;52.420210000000004,9.835550000000001;52.42925,9.813820000000002;52.43085000000001,9.79471;52.42779,9.7247;52.43054000000001,9.696290000000001;52.42510000000001,9.653210000000001;52.421170000000004,9.6211;52.423390000000005,9.578790000000001;52.417500000000004,9.52569;52.289620000000006,9.358910000000002;52.231910000000006,9.24149;52.21562,9.182310000000001;52.219620000000006,9.14776;52.22142,9.065040000000002;52.22037,9.038390000000001;52.22223,9.021730000000002;52.217940000000006,8.995940000000001;52.212610000000005,8.93844;52.209680000000006,8.91491;52.21121,8.885670000000001;52.20861000000001,8.85219;52.15294,8.80654;52.14058000000001,8.772390000000001;52.13412,8.740160000000001;52.11206000000001,8.73484;52.07144,8.665640000000002;52.04410000000001,8.651570000000001;52.00697,8.62578;51.975260000000006,8.61491;51.94693,8.55174;51.89922000000001,8.47058;51.87239,8.406730000000001;51.861250000000005,8.3519;51.850660000000005,8.31433;51.834970000000006,8.261230000000001;51.82466,8.19115;51.78134000000001,8.041110000000002;51.77647,8.01998;51.70244,7.976730000000001;51.63705,7.890180000000001;51.626580000000004,7.86035;51.6227,7.813490000000001;51.613980000000005,7.766280000000001;51.601580000000006,7.711950000000001;51.601760000000006,7.6908;51.524170000000005,7.658580000000001;51.472660000000005,7.601660000000001;51.454860000000004,7.5526100000000005;51.41355,7.504860000000001;51.40462,7.472980000000001;51.39327,7.442540000000001;51.35868000000001,7.37497;51.33413,7.322220000000001;51.32515,7.303280000000001;51.31483000000001,7.274590000000001;51.26042,7.238;51.21184,7.25269;51.153130000000004,7.213750000000001;51.13741,7.19021;51.119110000000006,7.1561200000000005;51.10833,7.14292;51.075320000000005,7.11692;51.06819,7.0817000000000005;51.05673,7.0568100000000005;51.04372000000001,7.012090000000001;51.042970000000004,7.006050000000001;50.92331,7.060040000000001;50.89781000000001,7.084460000000001;50.801860000000005,7.140180000000001;50.790780000000005,7.14897;50.76646,7.1398;50.75057,7.141000000000001;50.73749,7.098190000000001;\"" +
            ",\"viaWaypoints\":\"\"," +
            "\"numberOfSeats\":3," +
            "\"vehicle\":null," +
            "\"driver\":null," +
            "\"cancelationDateTime\":null," +
            "\"distanceMeters\":596067," +
            "\"durationSeconds\":20951}";
    private static final Trip EXPECTED_TRIP = TestObjects.newTestTrip();
    private static final String EXPECTED_JSON = "{\"startpoint\":\"Berlin, Deutschland\"," +
            "\"endpoint\":\"Cologne, Deutschland\"," +
            "\"latStartpoint\":52.5200119," +
            "\"longStartpoint\":6.96027939999999," +
            "\"latEndpoint\":50.937531," +
            "\"longEndpoint\":6.96027939999999," +
            "\"startDateTime\":1454144460000," +
            "\"endDateTime\":1454166060000," +
            "\"price\":90.0," +
            "\"overViewPath\":\"52.520010000000006,13.404950000000001;52.512220000000006,13.31473;52.46312,13.228760000000001;52.442800000000005,13.203080000000002;52.42296,13.195310000000001;52.411660000000005,13.196980000000002;52.39103000000001,13.160870000000001;52.37181,13.14994;52.34351,13.140600000000001;52.30724000000001,13.08815;52.3036,12.998320000000001;52.305460000000004,12.886030000000002;52.31504,12.866650000000002;52.328570000000006,12.826260000000001;52.33816,12.80413;52.34022,12.71319;52.34705,12.6156;52.351290000000006,12.55756;52.30127,12.472090000000001;52.28622000000001,12.45307;52.252590000000005,12.215580000000001;52.23274000000001,12.05353;52.235380000000006,11.95804;52.2334,11.898460000000002;52.22789,11.790880000000001;52.22106,11.703040000000001;52.19017,11.62799;52.185790000000004,11.447280000000001;52.187490000000004,11.35022;52.19762000000001,11.257930000000002;52.203410000000005,11.18543;52.20841000000001,11.16004;52.208490000000005,11.11616;52.21477,11.092260000000001;52.21752000000001,11.06568;52.22771,11.036760000000001;52.243900000000004,11.008000000000001;52.2601,10.990210000000001;52.26681000000001,10.974900000000002;52.27886,10.93439;52.284800000000004,10.89967;52.306650000000005,10.84035;52.31199,10.80732;52.312670000000004,10.765030000000001;52.30823,10.71823;52.30915,10.67294;52.31168,10.587280000000002;52.31382000000001,10.523150000000001;52.33008,10.451130000000001;52.339290000000005,10.373240000000001;52.33511000000001,10.291300000000001;52.33744,10.23475;52.34639000000001,10.20899;52.35557000000001,10.18914;52.35934,10.169630000000002;52.36365000000001,10.12066;52.36880000000001,10.068850000000001;52.379400000000004,10.03161;52.388870000000004,9.99003;52.39768,9.894960000000001;52.404410000000006,9.865150000000002;52.420210000000004,9.835550000000001;52.42925,9.813820000000002;52.43085000000001,9.79471;52.42779,9.7247;52.43054000000001,9.696290000000001;52.42510000000001,9.653210000000001;52.421170000000004,9.6211;52.423390000000005,9.578790000000001;52.417500000000004,9.52569;52.33614000000001,9.41122;52.289620000000006,9.358910000000002;52.21562,9.182310000000001;52.219620000000006,9.14776;52.22142,9.065040000000002;52.22037,9.038390000000001;52.22223,9.021730000000002;52.212610000000005,8.93844;52.209680000000006,8.91491;52.21121,8.885670000000001;52.20861000000001,8.85219;52.15294,8.80654;52.13412,8.740160000000001;52.11206000000001,8.73484;52.04410000000001,8.651570000000001;52.00697,8.62578;51.986740000000005,8.61494;51.96705000000001,8.602580000000001;51.9386,8.53668;51.90905000000001,8.50941;51.89922000000001,8.47058;51.87239,8.406730000000001;51.861250000000005,8.3519;51.850660000000005,8.31433;51.834970000000006,8.261230000000001;51.82466,8.19115;51.78134000000001,8.041110000000002;51.769560000000006,8.007620000000001;51.72429,7.980220000000001;51.70244,7.976730000000001;51.6227,7.813490000000001;51.613980000000005,7.766280000000001;51.601580000000006,7.711950000000001;51.601760000000006,7.6908;51.524170000000005,7.658580000000001;51.454860000000004,7.5526100000000005;51.40462,7.472980000000001;51.39327,7.442540000000001;51.35868000000001,7.37497;51.33413,7.322220000000001;51.31483000000001,7.274590000000001;51.273860000000006,7.256950000000001;51.228950000000005,7.23209;51.21835,7.249180000000001;51.19773000000001,7.2424800000000005;51.17249,7.238080000000001;51.119110000000006,7.1561200000000005;51.06819,7.0817000000000005;51.05673,7.0568100000000005;51.04041,7.003310000000001;51.038790000000006,6.975630000000001;51.023610000000005,6.9415700000000005;51.02393000000001,6.9429300000000005;50.98181,6.967840000000001;50.93526000000001,6.962980000000001;50.93753,6.960280000000001;\",\"viaWaypoints\":\"\",\"numberOfSeats\":3,\"vehicle\":null,\"driver\":null,\"cancelationDateTime\":null,\"distanceMeters\":572948,\"durationSeconds\":20248},{\"identity\":12,\"creationTimestamp\":1453800554000,\"updateTimestamp\":null,\"startpoint\":\"Berlin, Deutschland\",\"endpoint\":\"Bonn, Deutschland\",\"latStartpoint\":52.5200119,\"longStartpoint\":6.96027939999999,\"latEndpoint\":50.937531,\"longEndpoint\":6.96027939999999,\"startDateTime\":1454144460000,\"endDateTime\":1454166060000,\"price\":90.0,\"overViewPath\":\"52.520010000000006,13.404950000000001;52.512220000000006,13.31473;52.46312,13.228760000000001;52.442800000000005,13.203080000000002;52.42296,13.195310000000001;52.411660000000005,13.196980000000002;52.39103000000001,13.160870000000001;52.37181,13.14994;52.34351,13.140600000000001;52.30724000000001,13.08815;52.3036,12.998320000000001;52.305460000000004,12.886030000000002;52.31504,12.866650000000002;52.328570000000006,12.826260000000001;52.33816,12.80413;52.34022,12.71319;52.34705,12.6156;52.351290000000006,12.55756;52.30127,12.472090000000001;52.28622000000001,12.45307;52.252590000000005,12.215580000000001;52.23274000000001,12.05353;52.235380000000006,11.95804;52.2334,11.898460000000002;52.22789,11.790880000000001;52.22106,11.703040000000001;52.19017,11.62799;52.185790000000004,11.447280000000001;52.187490000000004,11.35022;52.19762000000001,11.257930000000002;52.203410000000005,11.18543;52.20841000000001,11.16004;52.208490000000005,11.11616;52.21477,11.092260000000001;52.21752000000001,11.06568;52.22771,11.036760000000001;52.243900000000004,11.008000000000001;52.2601,10.990210000000001;52.26681000000001,10.974900000000002;52.27886,10.93439;52.284800000000004,10.89967;52.306650000000005,10.84035;52.31199,10.80732;52.312670000000004,10.765030000000001;52.30823,10.71823;52.30915,10.67294;52.31168,10.587280000000002;52.31382000000001,10.523150000000001;52.33008,10.451130000000001;52.339290000000005,10.373240000000001;52.33511000000001,10.291300000000001;52.33744,10.23475;52.34639000000001,10.20899;52.35557000000001,10.18914;52.35934,10.169630000000002;52.36365000000001,10.12066;52.36880000000001,10.068850000000001;52.379400000000004,10.03161;52.388870000000004,9.99003;52.39768,9.894960000000001;52.404410000000006,9.865150000000002;52.420210000000004,9.835550000000001;52.42925,9.813820000000002;52.43085000000001,9.79471;52.42779,9.7247;52.43054000000001,9.696290000000001;52.42510000000001,9.653210000000001;52.421170000000004,9.6211;52.423390000000005,9.578790000000001;52.417500000000004,9.52569;52.33614000000001,9.41122;52.289620000000006,9.358910000000002;52.21562,9.182310000000001;52.219620000000006,9.14776;52.22142,9.065040000000002;52.22037,9.038390000000001;52.22223,9.021730000000002;52.212610000000005,8.93844;52.209680000000006,8.91491;52.21121,8.885670000000001;52.20861000000001,8.85219;52.15294,8.80654;52.13412,8.740160000000001;52.11206000000001,8.73484;52.04410000000001,8.651570000000001;52.00697,8.62578;51.986740000000005,8.61494;51.96705000000001,8.602580000000001;51.9386,8.53668;51.90905000000001,8.50941;51.89922000000001,8.47058;51.87239,8.406730000000001;51.861250000000005,8.3519;51.850660000000005,8.31433;51.834970000000006,8.261230000000001;51.82466,8.19115;51.78134000000001,8.041110000000002;51.769560000000006,8.007620000000001;51.72429,7.980220000000001;51.70244,7.976730000000001;51.6227,7.813490000000001;51.613980000000005,7.766280000000001;51.601580000000006,7.711950000000001;51.601760000000006,7.6908;51.524170000000005,7.658580000000001;51.454860000000004,7.5526100000000005;51.40462,7.472980000000001;51.39327,7.442540000000001;51.35868000000001,7.37497;51.33413,7.322220000000001;51.31483000000001,7.274590000000001;51.273860000000006,7.256950000000001;51.228950000000005,7.23209;51.21835,7.249180000000001;51.19773000000001,7.2424800000000005;51.17249,7.238080000000001;51.119110000000006,7.1561200000000005;51.06819,7.0817000000000005;51.05673,7.0568100000000005;51.04041,7.003310000000001;51.038790000000006,6.975630000000001;51.023610000000005,6.9415700000000005;51.02393000000001,6.9429300000000005;50.98181,6.967840000000001;50.93526000000001,6.962980000000001;50.93753,6.960280000000001;\",\"viaWaypoints\":\"\",\"numberOfSeats\":3,\"vehicle\":null,\"driver\":null,\"cancelationDateTime\":null,\"distanceMeters\":572948,\"durationSeconds\":20248},{\"identity\":14,\"creationTimestamp\":1453800555000,\"updateTimestamp\":null,\"startpoint\":\"Berlin, Deutschland\",\"endpoint\":\"Bonn, Deutschland\",\"latStartpoint\":52.5200119,\"longStartpoint\":7.098194100000001,\"latEndpoint\":50.7374891,\"longEndpoint\":7.098194100000001,\"startDateTime\":1454144460000,\"endDateTime\":1454166060000,\"price\":90.0,\"overViewPath\":\"52.520010000000006,13.404950000000001;52.512220000000006,13.31473;52.46312,13.228760000000001;52.43137,13.19189;52.42296,13.195310000000001;52.411660000000005,13.196980000000002;52.39103000000001,13.160870000000001;52.37181,13.14994;52.33874,13.137120000000001;52.30724000000001,13.08815;52.3036,12.998320000000001;52.305460000000004,12.886030000000002;52.31504,12.866650000000002;52.328570000000006,12.826260000000001;52.33816,12.80413;52.34022,12.71319;52.34705,12.6156;52.351290000000006,12.55756;52.30127,12.472090000000001;52.27799,12.435440000000002;52.26415,12.339310000000001;52.252590000000005,12.215580000000001;52.23274000000001,12.05353;52.235380000000006,11.95804;52.2334,11.898460000000002;52.22594,11.835170000000002;52.22789,11.790880000000001;52.228210000000004,11.75727;52.21873,11.68143;52.19017,11.62799;52.16613,11.506680000000001;52.185790000000004,11.447280000000001;52.18829,11.42279;52.187490000000004,11.35022;52.19762000000001,11.257930000000002;52.20745,11.13888;52.21477,11.092260000000001;52.21752000000001,11.06568;52.22771,11.036760000000001;52.243900000000004,11.008000000000001;52.2601,10.990210000000001;52.26681000000001,10.974900000000002;52.27886,10.93439;52.284800000000004,10.89967;52.306650000000005,10.84035;52.31199,10.80732;52.312670000000004,10.765030000000001;52.30915,10.67294;52.31168,10.587280000000002;52.31382000000001,10.523150000000001;52.33008,10.451130000000001;52.339290000000005,10.373240000000001;52.33511000000001,10.291300000000001;52.33744,10.23475;52.34639000000001,10.20899;52.35934,10.169630000000002;52.36365000000001,10.12066;52.36880000000001,10.068850000000001;52.379400000000004,10.03161;52.388870000000004,9.99003;52.39768,9.894960000000001;52.404410000000006,9.865150000000002;52.420210000000004,9.835550000000001;52.42925,9.813820000000002;52.43085000000001,9.79471;52.42779,9.7247;52.43054000000001,9.696290000000001;52.42510000000001,9.653210000000001;52.421170000000004,9.6211;52.423390000000005,9.578790000000001;52.417500000000004,9.52569;52.289620000000006,9.358910000000002;52.231910000000006,9.24149;52.21562,9.182310000000001;52.219620000000006,9.14776;52.22142,9.065040000000002;52.22037,9.038390000000001;52.22223,9.021730000000002;52.217940000000006,8.995940000000001;52.212610000000005,8.93844;52.209680000000006,8.91491;52.21121,8.885670000000001;52.20861000000001,8.85219;52.15294,8.80654;52.14058000000001,8.772390000000001;52.13412,8.740160000000001;52.11206000000001,8.73484;52.07144,8.665640000000002;52.04410000000001,8.651570000000001;52.00697,8.62578;51.975260000000006,8.61491;51.94693,8.55174;51.89922000000001,8.47058;51.87239,8.406730000000001;51.861250000000005,8.3519;51.850660000000005,8.31433;51.834970000000006,8.261230000000001;51.82466,8.19115;51.78134000000001,8.041110000000002;51.77647,8.01998;51.70244,7.976730000000001;51.63705,7.890180000000001;51.626580000000004,7.86035;51.6227,7.813490000000001;51.613980000000005,7.766280000000001;51.601580000000006,7.711950000000001;51.601760000000006,7.6908;51.524170000000005,7.658580000000001;51.472660000000005,7.601660000000001;51.454860000000004,7.5526100000000005;51.41355,7.504860000000001;51.40462,7.472980000000001;51.39327,7.442540000000001;51.35868000000001,7.37497;51.33413,7.322220000000001;51.32515,7.303280000000001;51.31483000000001,7.274590000000001;51.26042,7.238;51.21184,7.25269;51.153130000000004,7.213750000000001;51.13741,7.19021;51.119110000000006,7.1561200000000005;51.10833,7.14292;51.075320000000005,7.11692;51.06819,7.0817000000000005;51.05673,7.0568100000000005;51.04372000000001,7.012090000000001;51.042970000000004,7.006050000000001;50.92331,7.060040000000001;50.89781000000001,7.084460000000001;50.801860000000005,7.140180000000001;50.790780000000005,7.14897;50.76646,7.1398;50.75057,7.141000000000001;50.73749,7.098190000000001;\"" +
            ",\"viaWaypoints\":\"\"," +
            "\"numberOfSeats\":3," +
            "\"vehicle\":null," +
            "\"driver\":null," +
            "\"cancelationDateTime\":null," +
            "\"distanceMeters\":596067," +
            "\"durationSeconds\":20951}";

    private TripMapper tripMapper;

    @Before
    public void setUp() {
        tripMapper = (TripMapper) MapperFactory.newTripMapper();
    }

    @Override
    protected Trip givenTheExpectedEntity() {
        return EXPECTED_TRIP;
    }

    @Override
    protected JsonMapper<Trip> getMapper() {
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
}
