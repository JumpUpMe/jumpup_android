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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.Observable;
import java.util.Observer;

import jumpup.imi.fb4.htw.de.jumpupandroid.R;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.PortalActivity;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.profile.ProfileTask;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.TripFactory;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.edit.EditTripTask;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Trip;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.welcome.WelcomeActivity;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.AppUtility;

public class ViewTripActivity extends PortalActivity implements Observer {

    private static final String TAG = ViewTripActivity.class.getName();
    public static final String EXTRA_PARCELABLE_TRIP = "trip";

    private Trip trip;
    private EditText inputStartLocation;
    private EditText inputEndLocation;
    private EditText inputDepartureDateTime;
    private EditText inputArrivalDateTime;
    private EditText inputPrice;
    private EditText inputNumberSeats;
    private EditTripTask editTripTask;
    private Button btnEditTrip;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trip);

        this.bindTrip(savedInstanceState);
        this.bindInputElements();
        this.fillInputFieldsByTrip();
        this.registerButton();
    }

    private void registerButton() {

        btnEditTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fillTripByUserInputFields();

                if (getTask() == null || getTask().getStatus().equals(AsyncTask.Status.FINISHED)) {
                    // no task is running in parallel, so start new one
                    resetTask();
                    startProgress();
                    editTripTask.execute(trip);
                }
            }
        });
    }

    private void startProgress() {
        btnEditTrip.setEnabled(false);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void stopProgress() {
        btnEditTrip.setEnabled(true);
        progressBar.setIndeterminate(false);
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void bindInputElements() {
        this.inputStartLocation = (EditText) this.findViewById(R.id.edStartLocation);
        this.inputEndLocation = (EditText) this.findViewById(R.id.edEndLocation);
        this.inputDepartureDateTime = (EditText) this.findViewById(R.id.edDepartureDate);
        this.inputArrivalDateTime = (EditText) this.findViewById(R.id.edArrivalDate);
        this.inputPrice = (EditText) this.findViewById(R.id.edPrice);
        this.inputNumberSeats = (EditText) this.findViewById(R.id.edNumberSeats);

        this.btnEditTrip = (Button) this.findViewById(R.id.btnEditTrip);

        this.progressBar = (ProgressBar) this.findViewById(R.id.progressBar);
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

    private void fillTripByUserInputFields() {
        // do not change start and end location since they need to be set via GoogleMaps directions service
        this.trip.setStartDateTime(AppUtility.getUTCTimestamp(this.inputDepartureDateTime.getText().toString()));
        this.trip.setEndDateTime(AppUtility.getUTCTimestamp(this.inputArrivalDateTime.getText().toString()));
        this.trip.setPrice(AppUtility.getPriceAsDouble(this.inputPrice.getText().toString()));
        this.trip.setNumberOfSeats(AppUtility.getNumberAsInt(this.inputNumberSeats.getText().toString()));
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

    private void resetTask() {
        editTripTask = TripFactory.newEditTripTask(this);
        editTripTask.setUser(user);
    }

    @Override
    protected AsyncTask getTask() {
        return editTripTask;
    }

    @Override
    public void update(Observable observable, Object o) {
        Log.d(TAG, "update(): getting notified...");

        if (o instanceof EditTripTask) {
            this.handleEditTripResult();
        }
    }

    private void handleEditTripResult() {
        stopProgress();

        if (this.editTripTask.isHasValidationError()) {
            Log.d(TAG, "handleEditTripResult(): validation error");
            showValidationFailure();
        } else if (this.editTripTask.isHasError()) {
            Log.d(TAG, "handleEditTripResult(): error");
            this.showErrorNotification(this.getResources().getString(this.editTripTask.getToastMessageId()));
        } else {
            Log.d(TAG, "handleEditTripResult(): success");
            this.showSuccessNotification(this.getResources().getString(R.string.activity_view_trip_edit_success));
            navigateToShowMyTrips();
        }
    }

    private void showValidationFailure() {
        this.showValidationFailureNotification(this.editTripTask.getValidationFailureField(), this.editTripTask.getValidationFailureErrorMessages());
    }
}
