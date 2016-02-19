package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.search;

import android.location.Address;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.Observable;
import java.util.Observer;

import jumpup.imi.fb4.htw.de.jumpupandroid.R;
import jumpup.imi.fb4.htw.de.jumpupandroid.entity.TripSearchCriteria;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.PortalActivity;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.TripFactory;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.edit.EditTripTask;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.location.geocoding.GeocodingFactory;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.location.geocoding.GeocodingTask;

/**
 * Project: jumpup_android
 * <p/>
 * Search trips activity which allows a potential passenger to find trips offered by drivers.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 19.02.2016
 */
public class SearchTripsActivity extends PortalActivity implements Observer {

    private static final String TAG = SearchTripsActivity.class.getName();
    private EditText inputStartLocation;
    private EditText inputEndLocation;
    private EditText inputDateFrom;
    private EditText inputDateTo;
    private EditText inputPriceFrom;
    private EditText inputPriceTo;
    private EditText inputMaxDistance;
    private Button btnLookForTrips;
    private SearchTripsTask searchTripsTask;
    private GeocodingTask geocodingTask;
    private ProgressBar progressBar;
    private TripSearchCriteria tripSearchCriteria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_trips);

        this.bindInputElements();

        this.registerButton();
    }

    private void bindInputElements() {
        this.inputStartLocation = (EditText) this.findViewById(R.id.edStartLocation);
        this.inputEndLocation = (EditText) this.findViewById(R.id.edEndLocation);

        this.inputDateFrom = (EditText) this.findViewById(R.id.edDateFrom);
        this.inputDateTo = (EditText) this.findViewById(R.id.edDateTo);

        this.inputPriceFrom = (EditText) this.findViewById(R.id.edPriceFrom);
        this.inputPriceTo = (EditText) this.findViewById(R.id.edPriceTo);

        this.inputMaxDistance = (EditText) this.findViewById(R.id.edMaxDistance);

        this.progressBar = (ProgressBar) this.findViewById(R.id.progressBar);
    }

    private void registerButton() {
        this.btnLookForTrips = (Button) this.findViewById(R.id.btnSearchTrips);

        btnLookForTrips.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                tripSearchCriteria = fillTripSearchCriteriaByInputs();

                startGeocodingForStartLocation();
            }
        });
    }

    protected void startGeocodingForStartLocation() {
        if (getTask() == null || !getTask().getStatus().equals(AsyncTask.Status.RUNNING)) {
            // do geocoding
            resetGeocodingTask();
            startProgress();

            geocodingTask.execute(tripSearchCriteria.getStartPoint());
        }
    }

    private void startGeocodingForEndLocation() {
        if (getTask() == null || !getTask().getStatus().equals(AsyncTask.Status.RUNNING)) {
            // do geocoding
            resetGeocodingTask();
            startProgress();

            geocodingTask.execute(tripSearchCriteria.getEndPoint());
        }
    }

    protected void startSearchForTripTask() {
        resetSearchForTripsTask();

        if (getTask() == null || !getTask().getStatus().equals(AsyncTask.Status.RUNNING)) {
            // no task is running in parallel, so start new one
            resetSearchForTripsTask();
            startProgress();
            searchTripsTask.execute(tripSearchCriteria);
        }
    }

    private TripSearchCriteria fillTripSearchCriteriaByInputs() {
        TripSearchCriteria criteria = new TripSearchCriteria();

        criteria.setStartPoint(inputStartLocation.getText().toString());
        criteria.setEndPoint(inputEndLocation.getText().toString());
        criteria.setDateFrom(inputDateFrom.getText().toString());
        criteria.setDateTo(inputDateTo.getText().toString());
        criteria.setPriceFrom(Float.parseFloat(inputPriceFrom.getText().toString()));
        criteria.setPriceTo(Float.parseFloat(inputPriceTo.getText().toString()));
        criteria.setMaxDistance(Integer.parseInt(inputMaxDistance.getText().toString()));

        return criteria;
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AsyncTask getTask() {
        // determine which task is currently active since it needs to be stopped
        if (geocodingTask.getStatus().equals(AsyncTask.Status.RUNNING)) {
            return geocodingTask;
        }

        return searchTripsTask;
    }

    private void resetSearchForTripsTask() {
        searchTripsTask = TripFactory.newSearchTripsTask(this);
    }

    private void resetGeocodingTask() {
        geocodingTask = GeocodingFactory.newGeocodingTask(this);
    }

    private void startProgress() {
        btnLookForTrips.setEnabled(false);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void stopProgress() {
        btnLookForTrips.setEnabled(true);
        progressBar.setIndeterminate(false);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void update(Observable observable, Object o) {
        Log.d(TAG, "update(): getting notified...");

        if (o instanceof GeocodingTask) {
            this.handleGeocodingResult();
        }
    }

    private void handleGeocodingResult() {
        if (geocodingTask.isHasError()) {
            stopProgress();
            showErrorNotification(getString(R.string.activity_search_trips_geocoding_error));
        } else {
            if (geocodingTask.getInputLocationString().equals(this.inputStartLocation.toString())) {
                // we determined the start location
                enhanceTripSearchCriteriaByStartLocationGeocoding();
                // now we need to determine the end location as well
                startGeocodingForEndLocation();
            } else {
                // we determined the end location
                enhanceTripSearchCriteriaByEndLocationGeocoding();
                // now we can start the actual search trip task
                startSearchForTripTask();
            }
        }
    }

    private void enhanceTripSearchCriteriaByStartLocationGeocoding() {
        Address startLocation = geocodingTask.getResolvedAdresses().get(0);
        Log.d(TAG, "enhanceTripSearchCriteriaByStartLocationGeocoding(): resolved to " + startLocation);

        tripSearchCriteria.setLatStartPoint(startLocation.getLatitude());
        tripSearchCriteria.setLongStartPoint(startLocation.getLongitude());
    }

    private void enhanceTripSearchCriteriaByEndLocationGeocoding() {
        Address endLocation = geocodingTask.getResolvedAdresses().get(0);
        Log.d(TAG, "enhanceTripSearchCriteriaByEndLocationGeocoding(): resolved to " + endLocation);

        tripSearchCriteria.setLatEndPoint(endLocation.getLatitude());
        tripSearchCriteria.setLongEndPoint(endLocation.getLongitude());
    }

    private void handleSearchTripsResult() {
        stopProgress();

        if (this.searchTripsTask.isHasValidationError()) {
            Log.d(TAG, "handleSearchTripsResult(): validation error");
            showValidationFailure();
        } else if (this.searchTripsTask.isHasError()) {
            Log.d(TAG, "handleSearchTripsResult(): error");
            this.showErrorNotification(this.getResources().getString(this.searchTripsTask.getToastMessageId()));
        } else {
            Log.d(TAG, "handleSearchTripsResult(): success");
            this.showSuccessNotification(this.getResources().getString(R.string.activity_view_trip_edit_success));
            navigateToShowMyTrips();
        }
    }

    private void showValidationFailure() {
        this.showValidationFailureNotification(this.searchTripsTask.getValidationFailureField(), this.searchTripsTask.getValidationFailureErrorMessages());
    }
}
