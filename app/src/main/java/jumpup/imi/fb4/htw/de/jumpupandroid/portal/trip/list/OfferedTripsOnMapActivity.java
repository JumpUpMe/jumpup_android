package jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.list;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import jumpup.imi.fb4.htw.de.jumpupandroid.R;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.PortalActivity;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.Trip;
import jumpup.imi.fb4.htw.de.jumpupandroid.portal.trip.entity.TripList;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.map.MapFactory;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.map.adapter.MapAdapter;
import jumpup.imi.fb4.htw.de.jumpupandroid.util.map.listener.OnTripClickListener;

public class OfferedTripsOnMapActivity extends PortalActivity implements OnMapReadyCallback, OnTripClickListener {

    private static final String TAG = OfferedTripsOnMapActivity.class.getName();
    public static final String EXTRA_PARCELABLE_TRIPS = "trips";
    private GoogleMap googleMap;
    private TripList trips;
    private MapAdapter mapAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offered_trips_on_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        bindTrips(savedInstanceState);
    }

    private void bindTrips(Bundle savedInstanceState) {
        if (getIntent().hasExtra(EXTRA_PARCELABLE_TRIPS)) {
            // activity was started via intent
            this.trips = getIntent().getParcelableExtra(EXTRA_PARCELABLE_TRIPS);
        } else if (savedInstanceState.containsKey(EXTRA_PARCELABLE_TRIPS)) {
            // activity was restored and trip was saved
            this.trips = (TripList) savedInstanceState.get(EXTRA_PARCELABLE_TRIPS);
        } else {
            Log.e(TAG, "bindTrips(): can't get tripList instance, neither from intent nor from saved instance state bundle. Will navigate the user to show my trips so that the trips can be loaded again.");
            navigateToShowMyTrips();
        }
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
        this.googleMap = googleMap;

        this.mapAdapter = MapFactory.newGoogleMapsAdapter(googleMap);
        this.mapAdapter.setMapOptions(MapFactory.newDrawTripMapOptions(
                getString(R.string.activity_view_trip_on_map_start_location_label),
                getString(R.string.activity_view_trip_on_map_end_location_label),
                Color.BLUE,
                true,
                getLayoutInflater().inflate(R.layout.activity_view_trip_on_map_marker, null)));

        this.mapAdapter.drawTrips(trips, this);

        this.mapAdapter.moveCameraToCenterOfAllTrips();
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected AsyncTask getTask() {
        return null;
    }

    @Override
    public void onTripClick(Trip trip) {
        Log.i(TAG, "onTripClick(): " + trip);

        navigateToViewTrip(trip);
    }
}
