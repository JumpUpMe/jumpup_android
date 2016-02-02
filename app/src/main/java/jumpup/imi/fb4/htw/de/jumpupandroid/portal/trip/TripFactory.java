package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip;

import java.util.Observer;

import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.list.OfferedTripsActivity;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.list.OfferedTripsTask;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.list.TripsListAdapter;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.list.request.TripListRequest;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.list.request.TripListRequestImpl;

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
}
