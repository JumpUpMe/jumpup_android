package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.mapper;

import org.json.JSONException;
import org.json.JSONObject;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Trip;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Vehicle;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.webservice.mapper.JsonMapper;

/**
 * Project: jumpup_android
 * <p/>
 * Mapper between JSON responses and Trip entities.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 26.01.2016
 */
public class TripMapper extends JsonMapper<Trip> {
    private JsonMapper userMapper;

    @Override
    public Trip mapResponse(String response) throws JSONException {
        Trip trip = new Trip();

        this.parseResponseAndFillTrip(trip, response);

        return trip;
    }

    private void parseResponseAndFillTrip(Trip trip, String response) throws JSONException {
        JSONObject jsonResponse = new JSONObject(response);

        fillAbstractEntity(trip, jsonResponse);

        trip.setStartpoint(parseStartpoint(jsonResponse));
        trip.setEndpoint(parseEndpoint(jsonResponse));
        trip.setLatStartpoint(parseLatStartpoint(jsonResponse));
        trip.setLongStartpoint(parseLongStartpoint(jsonResponse));
        trip.setLatEndpoint(parseLatEndpoint(jsonResponse));
        trip.setLongEndpoint(parseLongEndpoint(jsonResponse));
        trip.setStartDateTime(parseStartDatetime(jsonResponse));
        trip.setEndDateTime(parseEndDatetime(jsonResponse));
        trip.setPrice(parsePrice(jsonResponse));
        trip.setOverViewPath(parseOverviewPath(jsonResponse));
        trip.setViaWaypoints(parseViaWaypoints(jsonResponse));
        trip.setNumberOfSeats(parseNumberOfSeats(jsonResponse));
        trip.setVehicle(parseVehicle(jsonResponse));
        trip.setDriver(parseDriver(jsonResponse));
        trip.setCancelationDateTime(parseCancelationDateTime(jsonResponse));
        trip.setDistanceMeters(parseDistanceMeters(jsonResponse));
        trip.setDurationSeconds(parseDurationSeconds(jsonResponse));
    }

    private long parseDurationSeconds(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.getLong(Trip.FIELD_NAME_DURATION_SECONDS);
    }

    private long parseDistanceMeters(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.getLong(Trip.FIELD_NAME_DISTANCE_METERS);
    }

    private Long parseCancelationDateTime(JSONObject jsonResponse) throws JSONException {
        if (!jsonResponse.isNull(Trip.FIELD_NAME_CANCELATION_DATETIME)) {
            return jsonResponse.getLong(Trip.FIELD_NAME_CANCELATION_DATETIME);
        }

        return null;
    }

    private User parseDriver(JSONObject jsonResponse) throws JSONException {
        if (!jsonResponse.isNull(Trip.FIELD_NAME_DRIVER)) {
            // value exists and is not null
            return getUserMapper().mapResponse(jsonResponse.getString(Trip.FIELD_NAME_DRIVER));
        }

        return null;
    }

    private Vehicle parseVehicle(JSONObject jsonResponse) {
        // TODO delegate to VehicleMapper
        return null;
    }

    private Integer parseNumberOfSeats(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.getInt(Trip.FIELD_NAME_NUMBER_OF_SEATS);
    }

    private String parseViaWaypoints(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.getString(Trip.FIELD_NAME_VIA_WAYPOINTS);
    }

    private String parseOverviewPath(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.getString(Trip.FIELD_NAME_OVERVIEW_PATH);
    }

    private double parsePrice(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.getDouble(Trip.FIELD_NAME_PRICE);
    }

    private Long parseEndDatetime(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.getLong(Trip.FIELD_NAME_END_DATETIME);
    }

    private Long parseStartDatetime(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.getLong(Trip.FIELD_NAME_START_DATETIME);
    }

    private double parseLongEndpoint(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.getDouble(Trip.FIELD_NAME_END_LNG);
    }

    private double parseLatEndpoint(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.getDouble(Trip.FIELD_NAME_END_LAT);
    }

    private double parseLongStartpoint(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.getDouble(Trip.FIELD_NAME_START_LNG);
    }

    private double parseLatStartpoint(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.getDouble(Trip.FIELD_NAME_START_LAT);
    }

    private String parseEndpoint(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.getString(Trip.FIELD_NAME_END_POINT);
    }

    private String parseStartpoint(JSONObject jsonResponse) throws JSONException {
        return jsonResponse.getString(Trip.FIELD_NAME_START_POINT);
    }

    @Override
    public String marshalEntity(Trip entity) throws JSONException {
        JSONObject marshalledTrip = new JSONObject();

        marshalledTrip.put(Trip.FIELD_NAME_START_POINT, entity.getStartpoint());
        marshalledTrip.put(Trip.FIELD_NAME_END_POINT, entity.getEndpoint());
        marshalledTrip.put(Trip.FIELD_NAME_START_LAT, entity.getLatStartpoint());
        marshalledTrip.put(Trip.FIELD_NAME_START_LNG, entity.getLongStartpoint());
        marshalledTrip.put(Trip.FIELD_NAME_END_LAT, entity.getLatEndpoint());
        marshalledTrip.put(Trip.FIELD_NAME_END_LNG, entity.getLongEndpoint());
        marshalledTrip.put(Trip.FIELD_NAME_START_DATETIME, entity.getStartDateTime());
        marshalledTrip.put(Trip.FIELD_NAME_END_DATETIME, entity.getEndDateTime());
        marshalledTrip.put(Trip.FIELD_NAME_PRICE, entity.getPrice());
        marshalledTrip.put(Trip.FIELD_NAME_OVERVIEW_PATH, entity.getOverViewPath());
        marshalledTrip.put(Trip.FIELD_NAME_VIA_WAYPOINTS, entity.getViaWaypoints());
        marshalledTrip.put(Trip.FIELD_NAME_NUMBER_OF_SEATS, entity.getNumberOfSeats());
        // TODO put vehicle when present
        putAllowNull(Trip.FIELD_NAME_VEHICLE, entity.getVehicle(), marshalledTrip);
        putAllowNull(Trip.FIELD_NAME_DRIVER, entity.getDriver(), marshalledTrip);
        putAllowNull(Trip.FIELD_NAME_CANCELATION_DATETIME, entity.getCancelationDateTime(), marshalledTrip);
        marshalledTrip.put(Trip.FIELD_NAME_DISTANCE_METERS, entity.getDistanceMeters());
        marshalledTrip.put(Trip.FIELD_NAME_DURATION_SECONDS, entity.getDurationSeconds());

        return marshalledTrip.toString();
    }
}
