package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import jumpup.imi.fb4.htw.de.jumpupandroid.lib.TestObjects;

/**
 * Project: jumpup_android
 * <p/>
 * Test of Trip list entity, especially Parcelable functions.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 12.02.2016
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class TripListTest {
    private static final TripList TEST_TRIP_LIST = TestObjects.newTestTripList();

    @Test
    public void testParcel()
    {
        // given
        Parcel parcel = givenAParcel();

        // when
        whenWrittenToTheParcel(parcel);
        TripList unserializedTripFromParcel = andTripListIsReadFromParcel(parcel);

        // then
        thenAssertThatOriginalTripListAndParcelOneAreEqual(unserializedTripFromParcel);
    }

    protected Parcel givenAParcel() {
        return Parcel.obtain();
    }

    protected void thenAssertThatOriginalTripListAndParcelOneAreEqual(TripList unserializedTripFromParcel) {
        Assert.assertEquals(TEST_TRIP_LIST, unserializedTripFromParcel);
    }

    protected TripList andTripListIsReadFromParcel(Parcel parcel) {
        parcel.setDataPosition(0);

        return TripList.CREATOR.createFromParcel(parcel);
    }

    protected void whenWrittenToTheParcel(Parcel parcel) {
        TEST_TRIP_LIST.writeToParcel(parcel, 0);
    }

}
