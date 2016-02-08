package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.view;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Set;

import jumpup.imi.fb4.htw.de.jumpupandroid.R;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.PortalActivity;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Trip;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.math.CoordinateUtil;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.math.Coordinates;

public class ViewTripOnMapActivity extends PortalActivity implements OnMapReadyCallback {

    public static final String EXTRA_PARCELABLE_TRIP = "extra_parcelable_trip";
    private static final String TAG = ViewTripOnMapActivity.class.getName();
    private GoogleMap mMap;
    private Trip trip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trip_on_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        bindTrip(savedInstanceState);
    }

    private void bindTrip(Bundle savedInstanceState) {
        if (getIntent().hasExtra(EXTRA_PARCELABLE_TRIP)) {
            // activity was started via intent
            this.trip = getIntent().getParcelableExtra(EXTRA_PARCELABLE_TRIP);
        } else if (savedInstanceState.containsKey(EXTRA_PARCELABLE_TRIP)) {
            // activity was restored and trip was saved
            this.trip = (Trip) savedInstanceState.get(EXTRA_PARCELABLE_TRIP);
        } else {
            Log.e(TAG, "bindTrip(): can't get trip instance, neither from intent nor from saved instance state bundle. Will navigate the user to show my trips so that the trips can be loaded again.");
            navigateToShowMyTrips();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putParcelable(EXTRA_PARCELABLE_TRIP, this.trip);

        super.onSaveInstanceState(outState, outPersistentState);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        showTripOnGoogleMap(googleMap);
    }

    private void showTripOnGoogleMap(GoogleMap googleMap) {
        // start location
        LatLng startLocation = new LatLng(trip.getLatStartpoint(), trip.getLongStartpoint());
        googleMap.addMarker(new MarkerOptions().position(startLocation).title(getString(R.string.activity_view_trip_on_map_start_location_label)));

        // end location
        LatLng endLocation = new LatLng(trip.getLatEndpoint(), trip.getLongEndpoint());
        googleMap.addMarker(new MarkerOptions().position(endLocation).title(getString(R.string.activity_view_trip_on_map_end_location_label)));

        // draw route
        drawTripPath(googleMap);

        // move camera
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(startLocation));
    }

    private void drawTripPath(GoogleMap googleMap) {
        // parse given overview path string
        Set<Coordinates> coordinates = CoordinateUtil.parseCoordinateSetBy(trip.getOverViewPath());

        PolylineOptions tripPathOptions = new PolylineOptions();
        for (Coordinates coordinate: coordinates) {
            tripPathOptions.add(new LatLng(coordinate.getLatitudeDegrees(), coordinate.getLongitudeDegrees()));
        }

        tripPathOptions.color(Color.BLUE);

        Polyline tripPath = googleMap.addPolyline(tripPathOptions);
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AsyncTask getTask() {
        return null;
    }
}
