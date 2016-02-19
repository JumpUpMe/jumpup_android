package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip;

import android.os.AsyncTask;

import java.util.Observer;

import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.edit.EditTripTask;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.edit.request.EditTripRequest;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.edit.request.EditTripRequestImpl;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.list.OfferedTripsActivity;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.list.OfferedTripsTask;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.list.TripsListAdapter;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.list.request.TripListRequest;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.list.request.TripListRequestImpl;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.search.SearchTripsActivity;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.search.SearchTripsTask;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.view.ViewTripActivity;

/**
 * Project: jumpup_android
 * <p/>
 * Factory for trip module.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 02.02.2016
 */
public class TripFactory {
    public static TripListRequest newTripListRequest() {
        return new TripListRequestImpl();
    }

    public static OfferedTripsTask newOfferedTripsTask(Observer observer) {
        OfferedTripsTask offeredTripsTask = new OfferedTripsTask(observer);

        return offeredTripsTask;
    }

    public static TripsListAdapter newTripListAdapter(OfferedTripsActivity offeredTripsActivity) {
        return new TripsListAdapter(offeredTripsActivity);
    }

    public static EditTripRequest newEditTripRequest() {
        return new EditTripRequestImpl();
    }

    public static EditTripTask newEditTripTask(Observer observer) {
        EditTripTask editTripTask = new EditTripTask();

        editTripTask.getObservable().addObserver(observer);

        return editTripTask;
    }

    public static SearchTripsTask newSearchTripsTask(Observer observer) {
        SearchTripsTask task = new SearchTripsTask();
        task.getObservable().addObserver(observer);

        return task;
    }
}
