package jumpup.imi.fb4.htw.de.jumpupandroid.portal;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import jumpup.imi.fb4.htw.de.jumpupandroid.MainActivity;
import jumpup.imi.fb4.htw.de.jumpupandroid.R;
import jumpup.imi.fb4.htw.de.jumpupandroid.entity.User;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.profile.ProfileActivity;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Trip;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.list.OfferedTripsActivity;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.view.ViewTripActivity;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.activity.JumpUpActivity;

/**
 * Project: jumpup_android
 * <p/>
 * Portal activities are all activites that are accessible after a successful login.
 * They have common look & feel, e.g. a unique menue.
 *
 * Portal activities also ensure that a user instance is given by an intent or restored by the savedInstanceState bundle.
 * They provide a default implementation to stop the realted task before the activity is stopped.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 20.01.2016
 */
public abstract class PortalActivity extends JumpUpActivity {
    /**
     * The logged-in user.
     */
    protected User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bindUser(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putParcelable(EXTRA_PARCELABLE_USER, this.user);

        super.onSaveInstanceState(outState, outPersistentState);
    }

    private void bindUser(Bundle savedInstanceState) {
        if (getIntent().hasExtra(EXTRA_PARCELABLE_USER)) {
            // activity was started via intent
            this.user = getIntent().getParcelableExtra(EXTRA_PARCELABLE_USER);
        } else if (null != savedInstanceState && savedInstanceState.containsKey(EXTRA_PARCELABLE_USER)) {
            // activity was restored
            this.user = savedInstanceState.getParcelable(EXTRA_PARCELABLE_USER);
        } else {
            Log.e(getTag(), "bindUser(): can't get user entity, neither from intent extra nor from the saved instance state bundle." +
                    "Will trigger logout to prevent errors.");
            logoutUser();
        }
    }

    @SuppressWarnings("SameReturnValue")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_portal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.portal_menu_profile:
                navigateToProfile();
                break;
            case R.id.portal_menu_look_for_trips:
                navigateToLookForTrips();
                break;
            case R.id.portal_menu_offer_trip:
                navigateToOfferTrips();
                break;
            case R.id.portal_menu_show_my_bookings:
                navigateToShowMyBookings();
                break;
            case R.id.portal_menu_show_my_trips:
                navigateToShowMyTrips();
                break;
            case R.id.portal_menu_logout:
                logoutUser();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void navigateToShowMyTrips() {
        Log.d(getTag(), "navigateToShowMyTrips(): menu item pressed");

        this.navigateToWithUserParcel(OfferedTripsActivity.class, this.user);
    }

    protected void navigateToShowMyBookings() {
        Log.d(getTag(), "navigateToShowMyBookings(): menu item pressed");
    }

    protected void navigateToOfferTrips() {
        Log.d(getTag(), "navigateToOfferTrips(): menu item pressed");
    }

    protected void navigateToLookForTrips() {
        Log.d(getTag(), "navigateToLookForTrips(): menu item pressed");
    }

    protected void navigateToProfile() {
        Log.d(getTag(), "navigateToProfile(): menu item pressed");

        this.navigateToWithUserParcel(ProfileActivity.class, this.user);
    }

    protected void logoutUser() {
        Log.d(getTag(), "logoutUser(): menu item pressed");

        this.navigateToAndClearActivityStack(MainActivity.class);

        this.showSuccessNotification(getString(R.string.portal_activities_logout_success));
    }

    protected void navigateToViewTrip(Trip trip) {
        Log.d(getTag(), "navigateToViewTrip(): navigating to trip with ID " + trip.getIdentity());

        this.navigateToWithUserAndAnotherExtraParcel(
                ViewTripActivity.class,
                this.user,
                ViewTripActivity.EXTRA_PARCELABLE_TRIP,
                trip
        );
    }

}
