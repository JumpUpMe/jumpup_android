package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.search;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import jumpup.imi.fb4.htw.de.jumpupandroid.R;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.PortalActivity;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.TripFactory;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search.SingleTripQueryResult;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.search.TripQueryResults;

/**
 * Activity which is launched if direct trips were found for a passenger.
 */
public class FoundDirectTripsActivity extends PortalActivity {

    private static final String TAG = FoundDirectTripsActivity.class.getName();
    public static final String EXTRA_PARCELABLE_TRIP_QUERY_RESULTS = "trip_query_results";
    private Button btnViewOnMap;
    private ListView tripsListView;
    private TripsSingleTripQueryResultAdapter singleTripsQueryResultListAdapter;
    private TripQueryResults tripQueryResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_direct_trips);

        this.bindTripsSearchResults(savedInstanceState);
        this.initializeListViewAdapter();
        this.registerButton();
    }

    private void bindTripsSearchResults(Bundle savedInstanceState) {
        if (getIntent().hasExtra(EXTRA_PARCELABLE_TRIP_QUERY_RESULTS)) {
            // activity was started via intent
            this.tripQueryResults = getIntent().getParcelableExtra(EXTRA_PARCELABLE_TRIP_QUERY_RESULTS);
        } else if (savedInstanceState.containsKey(EXTRA_PARCELABLE_TRIP_QUERY_RESULTS)) {
            // activity was restored and trip was saved
            this.tripQueryResults = (TripQueryResults) savedInstanceState.get(EXTRA_PARCELABLE_TRIP_QUERY_RESULTS);
        } else {
            Log.e(TAG, "bindTripsSearchResults(): can't get trip query result instance, neither from intent nor from saved instance state bundle. Will navigate the user to look for trips again so that the trips can be loaded again.");
            navigateToLookForTrips();
        }
    }

    private void initializeListViewAdapter() {
        this.tripsListView = (ListView) findViewById(R.id.listOfferedTrips);

        this.singleTripsQueryResultListAdapter = TripFactory.newTripQueryResultsAdapter(this);
        singleTripsQueryResultListAdapter.addAll(tripQueryResults.getTrips());

        tripsListView.setAdapter(this.singleTripsQueryResultListAdapter);
        tripsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                SingleTripQueryResult singleTripQueryResult = singleTripsQueryResultListAdapter.getItem(position);

                // start detail view activity
                navigateToViewTrip(singleTripQueryResult.getTrip());
            }
        });
    }

    private void registerButton() {
        this.btnViewOnMap = (Button) findViewById(R.id.btnViewOfferedTripsOnMap);

        btnViewOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "btnViewOnMap.onClick: button clicked");
            }
        });
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
