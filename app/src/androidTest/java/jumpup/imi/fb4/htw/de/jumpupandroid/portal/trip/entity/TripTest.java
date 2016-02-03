package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;
import jumpup.imi.fb4.htw.de.jumpupandroid.lib.TestObjects;

/**
 * Project: jumpup_android
 * <p/>
 * Test of Trip entity, especially Parcelable functions.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 03.02.2016
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class TripTest {
    private static final Trip TEST_TRIP = TestObjects.newTestTripFromBerlinToCologne();

    @Test
    public void testParcel()
    {
        // given
        Parcel parcel = givenAParcel();

        // when
        whenWrittenToTheParcel(parcel);
        Trip unserializedTripFromParcel = andTripIsReadFromParcel(parcel);

        // then
        thenAssertThatOriginalTripAndParcelOneAreEqual(unserializedTripFromParcel);
    }

    protected Parcel givenAParcel() {
        return Parcel.obtain();
    }

    protected void thenAssertThatOriginalTripAndParcelOneAreEqual(Trip unserializedTripFromParcel) {
        Assert.assertEquals(TEST_TRIP, unserializedTripFromParcel);
    }

    protected Trip andTripIsReadFromParcel(Parcel parcel) {
        parcel.setDataPosition(0);

        return Trip.CREATOR.createFromParcel(parcel);
    }

    protected void whenWrittenToTheParcel(Parcel parcel) {
        TEST_TRIP.writeToParcel(parcel, 0);
    }

}
