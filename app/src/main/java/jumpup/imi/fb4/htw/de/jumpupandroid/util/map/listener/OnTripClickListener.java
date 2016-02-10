package jumpup.imi.fb4.htw.de.jumpupandroid.util.map.listener;

import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Trip;

/**
 * Project: jumpup_android
 * <p/>
 * OnTripClickListener interface that defines the API for the trip click event handling.
 * The map adapter has to implement the logic to fire the onTripClick() event if a trip was selected
 * in the underlaying map.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 10.02.2016
 */
public interface OnTripClickListener {

    /**
     * If the user clicked on a trip in the underlaying map, the MapAdapter will call onTripClick.
     * @param trip the clicked / selected trip
     */
    void onTripClick(Trip trip);
}
