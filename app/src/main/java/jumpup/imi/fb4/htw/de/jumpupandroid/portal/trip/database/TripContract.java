package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.database;

import android.provider.BaseColumns;

import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Trip;

/**
 * Project: jumpup_android
 * <p/>
 * "Contract" class which mainly defines the schema of the SQLite database to store the user's trips.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 17.02.2016
 */
public class TripContract {
    private TripContract() {}

    public static abstract class TripEntry implements BaseColumns {
        public static final String TABLE_NAME = "trips";
        public static final String COLUMN_NAME_CREATION_TIMESTAMP = Trip.FIELD_NAME_CREATION_TIMESTAMP;
        public static final String COLUMN_NAME_UPDATE_TIMESTAMP = Trip.FIELD_NAME_UPDATE_TIMESTAMP;
        public static final String COLUMN_NAME_STARTPOINT = Trip.FIELD_NAME_START_POINT;
        public static final String COLUMN_NAME_ENDPOINT = Trip.FIELD_NAME_END_POINT;
        public static final String COLUMN_NAME_LAT_STARTPOINT = Trip.FIELD_NAME_START_LAT;
        public static final String COLUMN_NAME_LONG_STARTPOINT = Trip.FIELD_NAME_START_LNG;
        public static final String COLUMN_NAME_LAT_ENDPOINT = Trip.FIELD_NAME_END_LAT;
        public static final String COLUMN_NAME_LONG_ENDPOINT = Trip.FIELD_NAME_END_LNG;
        public static final String COLUMN_NAME_START_DATETIME = Trip.FIELD_NAME_START_DATETIME;
        public static final String COLUMN_NAME_END_DATETIME = Trip.FIELD_NAME_END_DATETIME;
        public static final String COLUMN_NAME_PRICE = Trip.FIELD_NAME_PRICE;
        public static final String COLUMN_NAME_OVERVIEW_PATH = Trip.FIELD_NAME_OVERVIEW_PATH;
        public static final String COLUMN_NAME_VIA_WAYPOINTS = Trip.FIELD_NAME_VIA_WAYPOINTS;
        public static final String COLUMN_NAME_NUMBER_OF_SEATS = Trip.FIELD_NAME_NUMBER_OF_SEATS;
        public static final String COLUMN_NAME_VEHICLE_ID = Trip.FIELD_NAME_VEHICLE;
        public static final String COLUMN_NAME_DRIVER_ID = Trip.FIELD_NAME_DRIVER;
        public static final String COLUMN_NAME_CANCELATION_DATETIME = Trip.FIELD_NAME_CANCELATION_DATETIME;
        public static final String COLUMN_NAME_DISTANCE_METERS = Trip.FIELD_NAME_DISTANCE_METERS;
        public static final String COLUMN_NAME_DURATION_SECONDS = Trip.FIELD_NAME_DURATION_SECONDS;
    }

    // TODO in future
    public static abstract class VehicleEntry implements BaseColumns {
        public static final String TABLE_NAME = "vehicles";

    }

    // TODO in future
    public static abstract class DriverEntry implements BaseColumns {
        public static final String TABLE_NAME = "drivers";

    }

    public static abstract class MetaInfoEntry implements BaseColumns {
        public static final String TABLE_NAME = "MetaInfo";
        public static final String COLUMN_NAME_LAST_SYNCHRONIZATION_DATETIME = "lastSyncDatetime";
        public static final String COLUMN_NAME_USER_ID = "userId";
    }
}
