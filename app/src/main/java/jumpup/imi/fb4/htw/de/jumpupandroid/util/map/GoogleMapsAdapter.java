package jumpup.imi.fb4.htw.de.jumpupandroid.util.map;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Trip;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.map.listener.OnTripClickListener;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.math.CoordinateUtil;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.math.Coordinates;

/**
 * Project: jumpup_android
 * <p/>
 * MapAdapter to the GoogleMaps implementation.
 *
 * @author Sascha Feldmann <a href="mailto:sascha.feldmann@gmx.de">sascha.feldmann@gmx.de</a>
 * @since 10.02.2016
 */
public class GoogleMapsAdapter implements MapAdapter {
    private static final String TAG = GoogleMapsAdapter.class.getName();

    private GoogleMap googleMap;
    private List<Trip> drawnTrips = new ArrayList<>();

    /**
     * Set the underlying google map.
     * @param googleMap the google map to be drawn
     * @return GoogleMapsAdapter this
     */
    public GoogleMapsAdapter setGoogleMap(GoogleMap googleMap) {
        this.googleMap = googleMap;

        return this;
    }

    @Override
    public MapAdapter drawTrip(Trip trip, MapOptions options, final OnTripClickListener onTripClickListener) {
        drawStartLocation(trip, options);
        drawEndLocation(trip, options);
        drawTripPath(trip, options, onTripClickListener);

        drawnTrips.add(trip);
        addListeners(trip, options, onTripClickListener);

        return this;
    }

    private void addListeners(final Trip trip, final MapOptions options, final OnTripClickListener onTripClickListener) {
        googleMap.setOnPolylineClickListener(new GoogleMap.OnPolylineClickListener() {
            @Override
            public void onPolylineClick(Polyline clickedPolyine) {
                onTripClickListener.onTripClick(trip);
            }
        });

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                onTripClickListener.onTripClick(trip);

                if (options.isShowInfoWindows() && !marker.isInfoWindowShown()) {
                    marker.setSnippet("Bla"); // TODO: separate between start and end location
                    marker.showInfoWindow();
                }
                return true;
            }
        });

    }

    protected void drawEndLocation(Trip trip, MapOptions options) {
        // end location
        LatLng endLocation = new LatLng(trip.getLatEndpoint(), trip.getLongEndpoint());
        googleMap.addMarker(new MarkerOptions().position(endLocation).title(options.getDestinationLocationLabel()));
    }

    protected void drawStartLocation(Trip trip, MapOptions options) {
        // start location
        LatLng startLocation = new LatLng(trip.getLatStartpoint(), trip.getLongStartpoint());
        googleMap.addMarker(new MarkerOptions().position(startLocation).title(options.getStartLocationLabel()));
    }

    private void drawTripPath(final Trip trip, MapOptions options, final OnTripClickListener onTripClickListener) {
        // parse given overview path string
        Set<Coordinates> coordinates = CoordinateUtil.parseCoordinateSetBy(trip.getOverViewPath());

        PolylineOptions tripPathOptions = new PolylineOptions();
        for (Coordinates coordinate: coordinates) {
            tripPathOptions.add(new LatLng(coordinate.getLatitudeDegrees(), coordinate.getLongitudeDegrees()));
        }

        tripPathOptions.color(options.getColor()).clickable(true);

        final Polyline polyline = googleMap.addPolyline(tripPathOptions);
    }

    @Override
    public MapAdapter moveCameraToCenterOfAllTrips() {
        LatLng arithmeticCenter = calculateArithmeticCenterOfAllDrawnTrips();

        Log.d(TAG, "moveCameraToCenterOfAllTrips(): will move camera to arithmetic center " + arithmeticCenter);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(arithmeticCenter));

        return this;
    }

    @NonNull
    protected LatLng calculateArithmeticCenterOfAllDrawnTrips() {
        double centerLat = 0;
        double centerLong = 0;

        for (Trip trip: drawnTrips) {
            centerLat += (trip.getLatEndpoint() - trip.getLatStartpoint());
            centerLong += (trip.getLongEndpoint() - trip.getLongStartpoint());
        }

        centerLat /= drawnTrips.size();
        centerLong /= drawnTrips.size();

        return new LatLng(centerLat, centerLong);
    }
}
