package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.list;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.Observable;
import java.util.Observer;

import jumpup.imi.fb4.htw.de.jumpupandroid.R;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.PortalActivity;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.TripFactory;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Trip;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.TripList;

/**
 * Project: jumpup_android
 * <p/>
 * OfferedTripsActivity presenter class
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 02.02.2016
 */
public class OfferedTripsActivity  extends PortalActivity implements Observer {

    private static final String TAG = OfferedTripsActivity.class.getName();
    private OfferedTripsTask task;
    private ListView tripsListView;
    private TripsListAdapter tripsListAdapter;
    private ProgressBar progressBar;
    private TripList offeredTrips;
    private Button btnRefresh;
    private Button btnViewOnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offered_trips);

        this.progressBar = (ProgressBar) this.findViewById(R.id.progressBar);
        this.initializeListViewAdapter();
        this.registerButton();
    }

    private void registerButton() {
        this.btnViewOnMap = (Button) findViewById(R.id.btnViewOfferedTripsOnMap);

        btnViewOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigateToOfferedTripsMap(offeredTrips);
            }
        });

        this.btnRefresh = (Button) findViewById(R.id.btnRefresh);

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTask(true);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.startTask(false);
    }

    private void startProgress() {
        btnRefresh.setEnabled(false);
        btnViewOnMap.setEnabled(false);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void stopProgress() {
        btnRefresh.setEnabled(true);
        btnViewOnMap.setEnabled(true);
        progressBar.setIndeterminate(false);
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void initializeListViewAdapter() {
        this.tripsListView = (ListView) findViewById(R.id.listOfferedTrips);

        this.tripsListAdapter = TripFactory.newTripListAdapter(this);

        tripsListView.setAdapter(this.tripsListAdapter);
        tripsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Trip trip = tripsListAdapter.getItem(position);

                // start detail view activity
                navigateToViewTrip(trip);
            }
        });
    }

    private void startTask(boolean forceReload) {
        if (getTask() == null || getTask().getStatus().equals(AsyncTask.Status.FINISHED)) {
            this.startProgress();
            resetTask();
            this.task.setForceReload(forceReload);
            this.task.execute(this.user);
        }
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AsyncTask getTask() {
        return task;
    }

    @Override
    public void update(Observable observable, Object o) {
        Log.d(TAG, "update(): getting notified...");

        if (o instanceof OfferedTripsTask) {
            this.handleOfferedTripsResult();
        }
    }

    private void handleOfferedTripsResult() {
       stopProgress();
       if (this.task.isHasError()) {
            Log.d(TAG, "handleOfferedTripsResult(): error");
            this.showErrorNotification(this.getResources().getString(this.task.getToastMessageId()));
        } else {
            Log.d(TAG, "handleOfferedTripsResult(): success");

           this.tripsListAdapter.clear();
           offeredTrips = this.task.getOfferedTrips();
           this.tripsListAdapter.addAll(offeredTrips);

           if (this.task.getOfferedTrips().size() == 0) {
               this.showSuccessNotification(this.getResources().getString(R.string.activity_offered_trips_no_offered_trips));
           }
        }
    }

    private void resetTask() {
        this.task = TripFactory.newOfferedTripsTask(this);
    }
}
