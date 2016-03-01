package jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper;

import android.location.Address;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.mapper.UserMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.mapper.TripListMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.mapper.TripMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.mapper.search.TripQueryResultsMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.mapper.VehicleMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.mapper.search.DriverMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.mapper.search.SingleTripQueryResultMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.mapper.search.TripSearchCriteriaMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.registration.entity.mapper.RegistrationMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.location.geocoding.adapter.google.GoogleGeocodingResponseAddressListMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.location.geocoding.adapter.google.GoogleGeocodingResponseAddressMapper;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.response.ErrorResponse;

/**
 * Project: jumpup_android
 * <p/>
 * [short_description]
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 18.01.2016
 */
public class MapperFactory {

    public static JsonMapper newUserMapper()
    {
        return new UserMapper();
    }

    public static JsonMapper newRegistrationMapper() {
        return new RegistrationMapper();
    }

    public static JsonMapper<ErrorResponse> newErrorResponseMapper() {
        return new ErrorResponseMapper();
    }

    public static JsonMapper newTripMapper() {
        return new TripMapper();
    }

    public static JsonMapper newTripListMapper() {
        return new TripListMapper();
    }

    public static DriverMapper newDriverMapper() {
        return new DriverMapper();
    }

    public static VehicleMapper newVehicleMapper() {
        return new VehicleMapper();
    }

    public static SingleTripQueryResultMapper newSingleTripQueryResultMapper() {
        return new SingleTripQueryResultMapper();
    }

    public static JsonMapper newTripQueryResultsMapper() {
        return new TripQueryResultsMapper();
    }

    public static TripSearchCriteriaMapper newTripSearchCriteriaMapper() {
        return new TripSearchCriteriaMapper();
    }

    public static JsonMapper<Address> newAddressJsonMapper() {
        return new GoogleGeocodingResponseAddressMapper();
    }

    public static GoogleGeocodingResponseAddressListMapper newGoogleGeocodingResponseAddressListMapper() {
        return new GoogleGeocodingResponseAddressListMapper();
    }

    public static GoogleGeocodingResponseAddressMapper newGoogleGeocodingResponseAddressMapper() {
        return new GoogleGeocodingResponseAddressMapper();
    }
}
