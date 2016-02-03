package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import jumpup.imi.fb4.htw.de.jumpupandroid.R;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.PortalActivity;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Trip;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.AppUtility;

public class ViewTripActivity extends PortalActivity {

    private static final String TAG = ViewTripActivity.class.getName();
    public static final String EXTRA_PARCELABLE_TRIP = "trip";

    private Trip trip;
    private EditText inputStartLocation;
    private EditText inputEndLocation;
    private EditText inputDepartureDateTime;
    private EditText inputArrivalDateTime;
    private EditText inputPrice;
    private EditText inputNumberSeats;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trip);

        this.bindTrip(savedInstanceState);
        this.bindInputElements();
        this.fillInputFieldsByTrip();
    }

    private void bindInputElements() {
        this.inputStartLocation = (EditText) this.findViewById(R.id.edStartLocation);
        this.inputEndLocation = (EditText) this.findViewById(R.id.edEndLocation);
        this.inputDepartureDateTime = (EditText) this.findViewById(R.id.edDepartureDate);
        this.inputArrivalDateTime = (EditText) this.findViewById(R.id.edArrivalDate);
        this.inputPrice = (EditText) this.findViewById(R.id.edPrice);
        this.inputNumberSeats = (EditText) this.findViewById(R.id.edNumberSeats);
    }

    private void fillInputFieldsByTrip() {
        if (AppUtility.isSet(this.trip.getStartpoint())) {
            this.inputStartLocation.setText(this.trip.getStartpoint());
        }

        if (AppUtility.isSet(this.trip.getEndpoint())) {
            this.inputEndLocation.setText(this.trip.getEndpoint());
        }

        if (AppUtility.isSet(this.trip.getStartDateTime())) {
            this.inputDepartureDateTime.setText(AppUtility.formatDate(this.trip.getStartDateTime()));
        }

        if (AppUtility.isSet(this.trip.getEndDateTime())) {
            this.inputArrivalDateTime.setText(AppUtility.formatDate(this.trip.getEndDateTime()));
        }

        this.inputPrice.setText(AppUtility.formatPrice(this.trip.getPrice()));

        if (AppUtility.isSet(this.trip.getNumberOfSeats())) {
            this.inputNumberSeats.setText(AppUtility.formatNumber(this.trip.getNumberOfSeats()));
        }
    }

    private void bindTrip(Bundle savedInstanceState) {
        if (getIntent().hasExtra(EXTRA_PARCELABLE_TRIP)) {
            // activity was started via intent
            this.trip = getIntent().getParcelableExtra(EXTRA_PARCELABLE_TRIP);
        } else if (savedInstanceState.containsKey(EXTRA_PARCELABLE_TRIP)) {
            // activity was restored and trip was saved
            this.trip = (Trip) savedInstanceState.get(EXTRA_PARCELABLE_TRIP);
        } else {
            Log.e(TAG, "bindTrip(): can't get trip instance, neither from intent nor from saved instance state bundle. Will navigate the user to show my trips so that the trips can be loaded again.");
            navigateToShowMyTrips();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putParcelable(EXTRA_PARCELABLE_TRIP, this.trip);

        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AsyncTask getTask() {
        return null;
    }
}
