package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.list;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.Observable;
import java.util.Observer;

import jumpup.imi.fb4.htw.de.jumpupandroid.R;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.PortalActivity;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.TripFactory;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offered_trips);

        this.initializeListViewAdapter();
    }

    @Override
    protected void onStart() {
        super.onStart();

        this.displayProgressBar();
        this.startTask();
    }

    private void displayProgressBar() {
        this.progressBar = new ProgressBar(this);
        progressBar.setIndeterminate(true);
        tripsListView.setEmptyView(progressBar);
    }

    private void initializeListViewAdapter() {
        this.tripsListView = (ListView) findViewById(R.id.listOfferedTrips);

        this.tripsListAdapter = TripFactory.newTripListAdapter(this);

        tripsListView.setAdapter(this.tripsListAdapter);
    }

    private void startTask() {
        if (getTask() == null || getTask().getStatus().equals(AsyncTask.Status.FINISHED)) {
            resetTask();
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
       if (this.task.isHasError()) {
            Log.d(TAG, "handleOfferedTripsResult(): error");
            this.showErrorNotification(this.getResources().getString(this.task.getToastMessageId()));
        } else {
            Log.d(TAG, "handleOfferedTripsResult(): success");

            this.tripsListAdapter.clear();
            this.tripsListAdapter.addAll(this.task.getOfferedTrips());

           if (this.task.getOfferedTrips().size() == 0) {
               this.showSuccessNotification(this.getResources().getString(R.string.activity_offered_trips_no_offered_trips));
           }
        }
    }

    private void resetTask() {
        this.task = TripFactory.newOfferedTripsTask(this);
    }
}
