package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import jumpup.imi.fb4.htw.de.jumpupandroid.lib.TestObjects;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Trip;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.TripList;

/**
 * Project: jumpup_android
 * <p/>
 * Test of the trip database.
 *
 * This test covers basic tests to ensure that the DB scheme is defined correctly and will work.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 17.02.2016
 */
public class TripDbTest extends AndroidTestCase {

    private SQLiteDatabase db;

    private Map<String, Boolean> expectedTables = new HashMap<>();
    private Map<String, Boolean> expectedTripColumns = new HashMap<>();
    private Map<String, Boolean> expectedVehicleColumns = new HashMap<>();
    private Map<String, Boolean> expectedDriverColumns = new HashMap<>();
    private Map<String, Boolean> expectedMetaInfoColumns = new HashMap<>();
    private long tripInsertionId;
    private Trip insertTrip;
    private TripMetaInfo insertMetaInfo;
    private TripList insertTripList;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        this.fillExpectedTables();
        this.fillExpectedColumns();
        this.cleanDatabase();
    }

    private void fillExpectedTables() {
        this.expectedTables.clear();

        this.expectedTables.put(TripContract.TripEntry.TABLE_NAME, false);
        this.expectedTables.put(TripContract.VehicleEntry.TABLE_NAME, false);
        this.expectedTables.put(TripContract.DriverEntry.TABLE_NAME, false);
        this.expectedTables.put(TripContract.MetaInfoEntry.TABLE_NAME, false);
    }

    private void fillExpectedColumns() {
        this.expectedTripColumns.clear();

        this.expectedTripColumns.put(TripContract.TripEntry._ID, false);
        this.expectedTripColumns.put(TripContract.TripEntry.COLUMN_NAME_CREATION_TIMESTAMP, false);
        this.expectedTripColumns.put(TripContract.TripEntry.COLUMN_NAME_UPDATE_TIMESTAMP, false);
        this.expectedTripColumns.put(TripContract.TripEntry.COLUMN_NAME_STARTPOINT, false);
        this.expectedTripColumns.put(TripContract.TripEntry.COLUMN_NAME_ENDPOINT, false);
        this.expectedTripColumns.put(TripContract.TripEntry.COLUMN_NAME_LAT_STARTPOINT, false);
        this.expectedTripColumns.put(TripContract.TripEntry.COLUMN_NAME_LONG_STARTPOINT, false);
        this.expectedTripColumns.put(TripContract.TripEntry.COLUMN_NAME_LAT_ENDPOINT, false);
        this.expectedTripColumns.put(TripContract.TripEntry.COLUMN_NAME_LONG_ENDPOINT, false);
        this.expectedTripColumns.put(TripContract.TripEntry.COLUMN_NAME_START_DATETIME, false);
        this.expectedTripColumns.put(TripContract.TripEntry.COLUMN_NAME_END_DATETIME, false);
        this.expectedTripColumns.put(TripContract.TripEntry.COLUMN_NAME_PRICE, false);
        this.expectedTripColumns.put(TripContract.TripEntry.COLUMN_NAME_OVERVIEW_PATH, false);
        this.expectedTripColumns.put(TripContract.TripEntry.COLUMN_NAME_VIA_WAYPOINTS, false);
        this.expectedTripColumns.put(TripContract.TripEntry.COLUMN_NAME_NUMBER_OF_SEATS, false);
        this.expectedTripColumns.put(TripContract.TripEntry.COLUMN_NAME_VEHICLE_ID, false);
        this.expectedTripColumns.put(TripContract.TripEntry.COLUMN_NAME_DRIVER_ID, false);
        this.expectedTripColumns.put(TripContract.TripEntry.COLUMN_NAME_CANCELATION_DATETIME, false);
        this.expectedTripColumns.put(TripContract.TripEntry.COLUMN_NAME_DISTANCE_METERS, false);
        this.expectedTripColumns.put(TripContract.TripEntry.COLUMN_NAME_DURATION_SECONDS, false);

        this.expectedVehicleColumns.clear();
        this.expectedVehicleColumns.put(TripContract.VehicleEntry._ID, false);

        this.expectedDriverColumns.clear();
        this.expectedDriverColumns.put(TripContract.DriverEntry._ID, false);

        this.expectedMetaInfoColumns.clear();
        this.expectedMetaInfoColumns.put(TripContract.MetaInfoEntry._ID, false);
        this.expectedMetaInfoColumns.put(TripContract.MetaInfoEntry.COLUMN_NAME_LAST_SYNCHRONIZATION_DATETIME, false);
        this.expectedMetaInfoColumns.put(TripContract.MetaInfoEntry.COLUMN_NAME_USER_ID, false);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        try {
            this.db.close();
        } catch (Exception e) {}

        this.db = null;
        this.insertTrip = null;
    }

    private void cleanDatabase() {
        mContext.deleteDatabase(TripDbHelper.DATABASE_NAME);
    }

    @Test
    public void testCreate() {
        givenAWriteableDb();

        thenTheDbShouldBeOpen();
        thenTheMasterDbShouldContainTheTableNames();
        thenTheTablesShouldContainCorrectColumns();
    }

    private void thenTheDbShouldBeOpen() {
        assertEquals(true, db.isOpen());
    }

    private void thenTheMasterDbShouldContainTheTableNames() {
        Cursor c = db.rawQuery("SELECT `name` FROM `sqlite_master` WHERE type='table'", null);

        assertTrue("Error: the SQLITE database doesn't contain any table", c.moveToFirst());

        int nameColumnIndex = c.getColumnIndex("name");
        do {
            String tableName = c.getString(nameColumnIndex);
            this.expectedTables.put(tableName, true); // the table is contained in the database
        } while ( c.moveToNext() );

        checkTables();
    }

    private void checkTables() {
        for (String expectedTableName: expectedTables.keySet()) {
            assertTrue(
                    "Error: " + expectedTableName + " isn't contained in DB ",
                    expectedTables.get(expectedTableName));
        }
    }

    protected void givenAWriteableDb() {
        this.db = new TripDbHelper(this.mContext).getWritableDatabase();
    }

    private void thenTheTablesShouldContainCorrectColumns() {
        analyzeTable(TripContract.TripEntry.TABLE_NAME, this.expectedTripColumns);
        analyzeTable(TripContract.VehicleEntry.TABLE_NAME, this.expectedVehicleColumns);
        analyzeTable(TripContract.DriverEntry.TABLE_NAME, this.expectedDriverColumns);
        analyzeTable(TripContract.MetaInfoEntry.TABLE_NAME, this.expectedMetaInfoColumns);
    }

    private void analyzeTable(String tableName, Map<String, Boolean> expectedColumnsName) {
        Cursor c = db.rawQuery("PRAGMA table_info(" + tableName + ")", null);

        assertTrue("Error: unable to get table information for table " + tableName,
                c.moveToFirst());

        int nameColumnIndex = c.getColumnIndex("name");
        do {
            String columnName = c.getString(nameColumnIndex);
            expectedColumnsName.put(columnName, true); // the column is contained
        } while ( c.moveToNext() );


        checkColumns(expectedColumnsName);
        c.close();
    }

    private void checkColumns(Map<String, Boolean> expectedTripColumns) {
        for (String expectedColumnName: expectedTripColumns.keySet()) {
            assertTrue(
                    "Error: column '" + expectedColumnName + "' isn't contained in the table ",
                    expectedTripColumns.get(expectedColumnName));
        }
    }

    @Test
    public void testTripTable() {
        this.insertTrip = TestObjects.newTestTripFromBerlinToCologne();

        givenATripInsertion();

        thenTheDataShouldHaveBeenInserted();
    }

    private void givenATripInsertion() {
        this.db = new TripDbHelper(mContext).getWritableDatabase();

        ContentValues testValues = TripDbHelper.createContentValues(insertTrip);

        this.tripInsertionId = db.insert(TripContract.TripEntry.TABLE_NAME, null, testValues);
    }

    private void thenTheDataShouldHaveBeenInserted() {
        assertTrue("Error: the row wasn't inserted", tripInsertionId != -1);

        Cursor cSelect = db.query(
                TripContract.TripEntry.TABLE_NAME,
                null, // projection: all
                null, // columns for where: all
                null, // values for where: all
                null, // group by columns
                null, // filter
                null // order
        );

        assertTrue("Error: no rows returned from trip query", cSelect.moveToFirst());

        Trip tripFromQuery = TripDbHelper.createTripFromSelectCursor(cSelect);

        assertEquals("Error: trip loaded from database should be equal to inserted one",
                insertTrip, tripFromQuery);

        cSelect.close();
    }

    @Test
    public void testMetaInfoConvenienceFunctions() {
        this.insertMetaInfo = TestObjects.newTestTripMetaInfo();

        givenADatabasetSetOfTheLastMetaInfo();

        thenTheLastMetaInfoShouldHaveBeenUpdated();
    }

    protected void givenADatabasetSetOfTheLastMetaInfo() {
        TripDbHelper.setLastMetaInfo(this.insertMetaInfo, mContext);
    }

    protected void thenTheLastMetaInfoShouldHaveBeenUpdated() {
        TripMetaInfo fromDb = TripDbHelper.getLastMetaInfo(mContext);

        assertEquals("Error: trip meta info loaded from database should be equal to inserted one",
                insertMetaInfo, fromDb);
    }

    @Test
    public void testSetAndGetTripsConvenienceFunctions() {
        this.insertTripList = TestObjects.newTestTripList();

        TripDbHelper.setTrips(mContext, insertTripList, TestObjects.newTestUser());

        TripList tripListFromDb = TripDbHelper.getTrips(mContext);

        assertEquals("Error: trip list loaded from database should be equal to inserted one",
                insertTripList, tripListFromDb);
    }
}
