package jumpup.imi.fb4.htw.de.jumpupandroid.util.map.adapter.googlemaps;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Trip;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.TripList;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.map.MapFactory;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.map.adapter.MapAdapter;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.map.adapter.MapOptions;
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
    private TripInfoWindow infoWindowAdapter;
    private List<Trip> drawnTrips = new ArrayList<>();
    private MapOptions mapOptions;
    private Map<String, Trip> markerIdTripMap = new HashMap<>();
    private Map<String, Trip> polylineIdMap = new HashMap<>();

    /**
     * Set the underlying google map.
     * @param googleMap the google map to be drawn
     * @return GoogleMapsAdapter this
     */
    public GoogleMapsAdapter setGoogleMap(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.infoWindowAdapter = MapFactory.newTripInfoWindowAdapter(this);

        this.googleMap.setInfoWindowAdapter(this.infoWindowAdapter);

        return this;
    }

    @Override
    public GoogleMapsAdapter setMapOptions(MapOptions options) {
        this.mapOptions = options;

        return this;
    }

    public MapOptions getMapOptions() {
        return mapOptions;
    }

    public Trip getTripByMarkerId(String markerId) {
        return this.markerIdTripMap.get(markerId);
    }

    public Trip getTripByPolylineId(String polylineId) {
        return this.polylineIdMap.get(polylineId);
    }

    @Override
    public MapAdapter drawTrip(Trip trip, final OnTripClickListener onTripClickListener) {
        drawStartLocation(trip);
        drawEndLocation(trip);
        drawTripPath(trip, onTripClickListener);

        drawnTrips.add(trip);
        addListeners(trip, onTripClickListener);

        return this;
    }

    private void addListeners(final Trip trip, final OnTripClickListener onTripClickListener) {
        googleMap.setOnPolylineClickListener(new GoogleMap.OnPolylineClickListener() {
            @Override
            public void onPolylineClick(Polyline clickedPolyine) {
                onTripClickListener.onTripClick(polylineIdMap.get(clickedPolyine.getId()));
            }
        });

        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Log.i(TAG, "onMarkerClick(): marker clicked.");

                if (mapOptions.isShowInfoWindows() && !marker.isInfoWindowShown()) {
                    marker.hideInfoWindow();
                    marker.showInfoWindow();
                }

                return true;
            }
        });

    }

    protected void drawEndLocation(Trip trip) {
        // end location
        LatLng endLocation = new LatLng(trip.getLatEndpoint(), trip.getLongEndpoint());
        Marker marker = addMarker(endLocation, mapOptions.getDestinationLocationLabel());

        this.markerIdTripMap.put(marker.getId(), trip);
    }

    protected void drawStartLocation(Trip trip) {
        // start location
        LatLng startLocation = new LatLng(trip.getLatStartpoint(), trip.getLongStartpoint());
        Marker marker = addMarker(startLocation, mapOptions.getStartLocationLabel());

        this.markerIdTripMap.put(marker.getId(), trip);
    }

    private Marker addMarker(LatLng location, String title) {
        return googleMap.addMarker(new MarkerOptions().position(location).title(mapOptions.getStartLocationLabel()));
    }

    private void drawTripPath(final Trip trip, final OnTripClickListener onTripClickListener) {
        // parse given overview path string
        Set<Coordinates> coordinates = CoordinateUtil.parseCoordinateSetBy(trip.getOverViewPath());

        PolylineOptions tripPathOptions = new PolylineOptions();
        for (Coordinates coordinate: coordinates) {
            tripPathOptions.add(new LatLng(coordinate.getLatitudeDegrees(), coordinate.getLongitudeDegrees()));
        }

        tripPathOptions.color(mapOptions.getColor()).clickable(true);

        final Polyline polyline = googleMap.addPolyline(tripPathOptions);
        this.polylineIdMap.put(polyline.getId(), trip);
    }

    @Override
    public MapAdapter moveCameraToCenterOfAllTrips() {
        LatLng arithmeticCenter = calculateArithmeticCenterOfAllDrawnTrips();

        Log.d(TAG, "moveCameraToCenterOfAllTrips(): will move camera to arithmetic center " + arithmeticCenter);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(arithmeticCenter));

        return this;
    }

    @Override
    public MapAdapter drawTrips(TripList trips, OnTripClickListener onTripClickListener) {
        for (Trip trip: trips) {
            drawTrip(trip, onTripClickListener);
        }

        return this;
    }

    @NonNull
    protected LatLng calculateArithmeticCenterOfAllDrawnTrips() {
        double centerLat = 0.0;
        double centerLong = 0.0;

        for (Trip trip: drawnTrips) {
            centerLat += trip.getLatStartpoint();
            centerLong += trip.getLongStartpoint();
        }

        centerLat = centerLat / (drawnTrips.size() * 1.0);
        centerLong = centerLong / (drawnTrips.size() * 1.0);

        return new LatLng(centerLat, centerLong);
    }
}
