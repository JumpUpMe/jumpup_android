package jumpup.imi.fb4.htw.de.jumpupandroid.util.map;

import com.google.android.gms.maps.GoogleMap;

import jumpup.imi.fb4.htw.de.jumpupandroid.util.map.listener.OnTripClickListener;

/**
 * Project: jumpup_android
 * <p/>
 * [short_description]
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 10.02.2016
 */
public class MapFactory {
    public static MapAdapter newGoogleMapsAdapter(GoogleMap googleMap) {
        return new GoogleMapsAdapter().setGoogleMap(googleMap);
    }

    public static MapOptions newDrawTripMapOptions(String startLocationLabel, String destinationLocationLabel, int color) {
        return new MapOptions()
                .setStartLocationLabel(startLocationLabel)
                .setDestinationLocationLabel(destinationLocationLabel)
                .setColor(color);
    }
}
