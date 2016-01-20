package jumpup.imi.fb4.htw.de.jumpupandroid.entity;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestRunner;
import android.test.suitebuilder.annotation.SmallTest;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import jumpup.imi.fb4.htw.de.jumpupandroid.lib.TestObjects;

/**
 * Project: jumpup_android
 * <p/>
 * Test of User entity, especially Parcelable functions.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 20.01.2016
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class UserTest {
    private static final User TEST_USER = TestObjects.newTestUser();

    @Before
    public void setUp()
    {
        // set a password on the test user
        TEST_USER.setPassword("$test1234");
    }

    @Test
    public void testParcel()
    {
        // given
        Parcel parcel = givenAParcel();

        // when
        whenWrittenToTheParcel(parcel);
        User unserializedUserFromParcel = andUserIsReadFromParcel(parcel);

        // then
        thenAssertThatOriginalUserAndParcelOneAreEqual(unserializedUserFromParcel);
    }

    protected Parcel givenAParcel() {
        return Parcel.obtain();
    }

    protected void thenAssertThatOriginalUserAndParcelOneAreEqual(User unserializedUserFromParcel) {
        Assert.assertEquals(TEST_USER, unserializedUserFromParcel);
    }

    protected User andUserIsReadFromParcel(Parcel parcel) {
        parcel.setDataPosition(0);

        return User.CREATOR.createFromParcel(parcel);
    }

    protected void whenWrittenToTheParcel(Parcel parcel) {
        TEST_USER.writeToParcel(parcel, 0);
    }

}
