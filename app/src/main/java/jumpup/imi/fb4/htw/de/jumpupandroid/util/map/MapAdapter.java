package jumpup.imi.fb4.htw.de.jumpupandroid.util.map;

import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Trip;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.map.listener.OnTripClickListener;

/**
 * Project: jumpup_android
 * <p/>
 * Map adapter interface to decouple dependencies to the underlaying map layer.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 10.02.2016
 */
public interface MapAdapter {

    /**
     * Draw the given trip on the underlying map.
     *
     * @param trip the given trip
     * @param options the options to be applied on the underlying map
     * @param onTripClickListener will be triggered if the user selected the trip on the underlying map
     * @return this - the map adapter itself
     */
    MapAdapter drawTrip(Trip trip, MapOptions options, OnTripClickListener onTripClickListener);

    /**
     * Move the camera to the center of all drawn trips (drawTrip()).
     *
     * @return this - the map adapter itself
     */
    MapAdapter moveCameraToCenterOfAllTrips();
}
