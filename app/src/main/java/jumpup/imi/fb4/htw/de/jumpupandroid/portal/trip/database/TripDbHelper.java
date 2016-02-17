package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Project: jumpup_android
 * <p/>
 * DBHelper to perform CRUD operations on the underlying trip table.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 17.02.2016
 */
public class TripDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "trips.db";
    public static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TRIPS_TABLE = "CREATE TABLE " + TripContract.TripEntry.TABLE_NAME + " ("
            + TripContract.TripEntry._ID + " INTEGER PRIMARY KEY, "
            + TripContract.TripEntry.COLUMN_NAME_CREATION_TIMESTAMP + " INTEGER, "
            + TripContract.TripEntry.COLUMN_NAME_UPDATE_TIMESTAMP + " INTEGER, "
            + TripContract.TripEntry.COLUMN_NAME_STARTPOINT + " TEXT NOT NULL, "
            + TripContract.TripEntry.COLUMN_NAME_ENDPOINT + " TEXT NOT NULL, "
            + TripContract.TripEntry.COLUMN_NAME_LAT_STARTPOINT + " REAL NOT NULL, "
            + TripContract.TripEntry.COLUMN_NAME_LONG_STARTPOINT + " REAL NOT NULL, "
            + TripContract.TripEntry.COLUMN_NAME_LAT_ENDPOINT + " REAL NOT NULL, "
            + TripContract.TripEntry.COLUMN_NAME_LONG_ENDPOINT + " REAL NOT NULL, "
            + TripContract.TripEntry.COLUMN_NAME_START_DATETIME + " INTEGER NOT NULL, "
            + TripContract.TripEntry.COLUMN_NAME_END_DATETIME + " INTEGER NOT NULL, "
            + TripContract.TripEntry.COLUMN_NAME_PRICE + " REAL NOT NULL, "
            + TripContract.TripEntry.COLUMN_NAME_OVERVIEW_PATH + " TEXT NOT NULL, "
            + TripContract.TripEntry.COLUMN_NAME_VIA_WAYPOINTS + " TEXT, "
            + TripContract.TripEntry.COLUMN_NAME_NUMBER_OF_SEATS + " INTEGER, "
            + TripContract.TripEntry.COLUMN_NAME_VEHICLE_ID + " INTEGER, " // foreign key to vehicle table
            + TripContract.TripEntry.COLUMN_NAME_DRIVER_ID + " INTEGER, " // foreign key to driver table
            + TripContract.TripEntry.COLUMN_NAME_CANCELATION_DATETIME + " INTEGER, "
            + TripContract.TripEntry.COLUMN_NAME_DISTANCE_METERS + " INTEGER, "
            + TripContract.TripEntry.COLUMN_NAME_DURATION_SECONDS + " INTEGER, "
            + " FOREIGN KEY (" + TripContract.TripEntry.COLUMN_NAME_VEHICLE_ID + ") REFERENCES "
            + TripContract.VehicleEntry.TABLE_NAME + " (" + TripContract.VehicleEntry._ID + "), "
            + " FOREIGN KEY (" + TripContract.TripEntry.COLUMN_NAME_DRIVER_ID + ") REFERENCES "
            + TripContract.TripEntry.TABLE_NAME + " (" + TripContract.TripEntry._ID + ") "
            + ");";

    private static final String SQL_CREATE_VEHICLE_TABLE = "CREATE TABLE " + TripContract.VehicleEntry.TABLE_NAME + " ("
            + TripContract.VehicleEntry._ID + " INTEGER PRIMARY KEY"
            + ");";

    private static final String SQL_CREATE_DRIVER_TABLE = "CREATE TABLE " + TripContract.DriverEntry.TABLE_NAME + " ("
            + TripContract.DriverEntry._ID + " INTEGER PRIMARY KEY"
            + ");";
    private static final java.lang.String SQL_CREATE_METAINFO_TABLE =  "CREATE TABLE " + TripContract.MetaInfo.TABLE_NAME + " ("
            + TripContract.MetaInfo._ID + " INTEGER PRIMARY KEY, "
            + TripContract.MetaInfo.COLUMN_NAME_LAST_SYNCHRONIZATION_DATETIME + " INTEGER "
            + ");";

    public TripDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_VEHICLE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_DRIVER_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_TRIPS_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_METAINFO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        /**
         * Since this database is a simple cache of the trips of the user,
         * we discard all tables if the database version changes as simple upgrade policy.
         */
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TripContract.VehicleEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TripContract.DriverEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TripContract.TripEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TripContract.MetaInfo.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
