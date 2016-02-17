package jumpup.imi.fb4.htw.de.jumpupandroid.util.map.adapter.googlemaps;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import jumpup.imi.fb4.htw.de.jumpupandroid.App;
import jumpup.imi.fb4.htw.de.jumpupandroid.R;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Trip;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.AppUtility;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.map.adapter.googlemaps.GoogleMapsAdapter;

/**
 * Project: jumpup_android
 * <p/>
 * Custom implementation of GoogleMap Info Window adapter which is shown on click on a marker.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 10.02.2016
 */
public class TripInfoWindow implements GoogleMap.InfoWindowAdapter {
    private static final String TAG = TripInfoWindow.class.getName();
    private static final int WIDTH = 600;
    private static final int HEIGHT = 700;
    private GoogleMapsAdapter adapter;

    public TripInfoWindow(GoogleMapsAdapter adapter) {
        this.setAdapter(adapter);
    }

    public void setAdapter(GoogleMapsAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        // if this returns null, getInfoContents() will be called
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return buildView(marker);
    }

    private View buildView(Marker marker) {
        Log.i(TAG, "buildView(): building view...");
        View view = adapter.getMapOptions().getInfoWindowView();
        view.setLayoutParams(new ViewGroup.LayoutParams(WIDTH, HEIGHT));

        TextView textView = (TextView) view.findViewById(R.id.txtStartLocationInfoWindow);
        textView.setText(buildText(textView, marker));

        return view;
    }

    @NonNull
    protected String buildText(TextView textView, Marker marker) {
        Trip trip = findTripByMarker(marker);

        if (null != trip) {
            return String.format(
                    App.getStringFromResourceId(R.string.activity_view_trip_on_map_start_location_info_window),
                    AppUtility.formatDateTime(trip.getStartDateTime()),
                    Integer.toString(trip.getNumberOfSeats()),
                    AppUtility.formatDuration(trip.getDurationSeconds(), true),
                    AppUtility.formatDistance(trip.getDistanceMeters(), true),
                    AppUtility.formatDateTime(trip.getEndDateTime()));
        }

        return "";
    }

    private Trip findTripByMarker(Marker marker) {
        return this.adapter.getTripByMarkerId(marker.getId());
    }
}
