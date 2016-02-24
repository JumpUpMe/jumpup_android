package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import jumpup.imi.fb4.htw.de.jumpupandroid.lib.TestObjects;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Trip;

/**
 * Project: jumpup_android
 * <p/>
 * Test of TripQueryResults entity, especially Parcelable functions.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 24.02.2016
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class TripQueryResultsDirectTripTest {
    private static final TripQueryResults TEST_TRIP_QUERY_RESULTS = TestObjects.newTestTripQueryResultDirectTripFound();

    @Test
    public void testParcel()
    {
        // given
        Parcel parcel = givenAParcel();

        // when
        whenWrittenToTheParcel(parcel);
        TripQueryResults unserializedTripFromParcel = andTripIsReadFromParcel(parcel);

        // then
        thenAssertThatOriginalTripAndParcelOneAreEqual(unserializedTripFromParcel);
    }

    protected Parcel givenAParcel() {
        return Parcel.obtain();
    }

    protected void thenAssertThatOriginalTripAndParcelOneAreEqual(TripQueryResults unserializedTripFromParcel) {
        Assert.assertEquals(TEST_TRIP_QUERY_RESULTS, unserializedTripFromParcel);
    }

    protected TripQueryResults andTripIsReadFromParcel(Parcel parcel) {
        parcel.setDataPosition(0);

        return TripQueryResults.CREATOR.createFromParcel(parcel);
    }

    protected void whenWrittenToTheParcel(Parcel parcel) {
        TEST_TRIP_QUERY_RESULTS.writeToParcel(parcel, 0);
    }

}
