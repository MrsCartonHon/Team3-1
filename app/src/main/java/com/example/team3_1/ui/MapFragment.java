package com.example.team3_1.ui;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.team3_1.TruckDb.Truck;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.team3_1.R;
import com.example.team3_1.TruckDb.TruckViewModel;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mapbox.android.core.location.LocationEngine;
import com.mapbox.android.core.location.LocationEngineCallback;
import com.mapbox.android.core.location.LocationEngineProvider;
import com.mapbox.android.core.location.LocationEngineRequest;
import com.mapbox.android.core.location.LocationEngineResult;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.plugins.annotation.Symbol;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager;
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions;
import com.mapbox.mapboxsdk.plugins.markerview.MarkerView;
import com.mapbox.mapboxsdk.plugins.markerview.MarkerViewManager;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MapFragment extends Fragment implements PermissionsListener, OnMapReadyCallback {
    private static final long DEFAULT_INTERVAL_IN_MILLISECONDS = 1000L;
    private static final long DEFAULT_MAX_WAIT_TIME = DEFAULT_INTERVAL_IN_MILLISECONDS * 5;
    private MapView mapView;
    private MapboxMap mapboxMap;
    private View view;
    private PermissionsManager permissionsManager;
    private LocationEngine locationEngine;
    private LocationChangeListeningActivityLocationCallback callback = new LocationChangeListeningActivityLocationCallback(this);
    private MarkerViewManager markerViewManager;
    private MarkerView markerView;
    private SymbolManager symbolManager;
    private Symbol symbol;
    private Boolean isPopupDisplaying = false;
    private TruckViewModel mTruckViewModel;
    private List<Truck> mTruckList;
    private ArrayList<SymbolOptions> symbolOptionsList;
    private ArrayList<MarkerView> markerViewList;


    public MapFragment() {
        super(R.layout.map_fragment);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Mapbox.getInstance(getContext().getApplicationContext(),getString(R.string.mapbox_access_token));

        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.map_fragment, container, false);

        mapView = (MapView) view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        // Get a new or existing ViewModel from the ViewModelProvider.
        mTruckViewModel = ViewModelProviders.of(this).get(TruckViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mTruckViewModel.getAllTrucks().observe(getViewLifecycleOwner(), new Observer<List<Truck>>() {
            @Override
            public void onChanged(@Nullable final List<Truck> trucks) {
                // Update the cached copy of the words in the adapter.
                mTruckList = trucks;
            }
        });


        return view;
    }

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        this.mapboxMap = mapboxMap;

        //make markerviewmanager
        markerViewManager = new MarkerViewManager(mapView, mapboxMap);
        markerViewList = new ArrayList<MarkerView>();
        symbolOptionsList = new ArrayList<SymbolOptions>();

        // Use an XML layout to create a View object
        View customView = LayoutInflater.from(getActivity()).inflate(
                R.layout.marker_view_bubble, null);
        customView.setLayoutParams(new FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));

        // Set the View's TextViews with content
        TextView titleTextView = customView.findViewById(R.id.marker_window_title);
        titleTextView.setText(R.string.draw_marker_options_title);

        TextView snippetTextView = customView.findViewById(R.id.marker_window_snippet);
        snippetTextView.setText(R.string.draw_marker_options_snippet);

        mapboxMap.setStyle(Style.LIGHT, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {
                //define marker images
                style.addImage(
                        "marker-ic-id",
                        BitmapFactory.decodeResource(view.getResources(), R.drawable.ic_truck_map), false
                );


                // Create a SymbolManager.
                symbolManager = new SymbolManager(mapView, mapboxMap, style);

                symbolManager.addClickListener(symbol -> {
                    JsonObject object = (JsonObject) symbol.getData();
                    int pos = object.get("pos").getAsInt();
                    if(!isPopupDisplaying){ //show or remove pop up depending on if it already is showing
                        markerViewManager.addMarker(markerViewList.get(pos));//show pop up when clicked
                    } else {
                        markerViewManager.removeMarker(markerViewList.get(pos)); //remove marker
                    }
                    isPopupDisplaying = !isPopupDisplaying;
                    return false;
                });

                // Set non-data-driven properties.
                symbolManager.setIconAllowOverlap(true);
                symbolManager.setTextAllowOverlap(true);

                displayTrucks(mTruckList);
                /*
                // Create a symbol at the specified location.
                SymbolOptions symbolOptions = new SymbolOptions()
                        .withLatLng(new LatLng(41.556019, -90.495431)) //these are the coordinates of the truck
                        .withIconImage("marker-ic-id")
                        .withIconSize(1.3f);
                //defines marker view(the pop up bubble) but doesnt display it yet
                markerView = new MarkerView(new LatLng(41.556019, -90.495431), customView);

                // Use the manager to draw the symbol.
                symbol = symbolManager.create(symbolOptions);


                 */
                enableLocationComponent(style);
            }
        });
    }

    /**
     * Initialize the Maps SDK's LocationComponent
     */
    @SuppressWarnings( {"MissingPermission"})
    private void enableLocationComponent(@NonNull Style loadedMapStyle) {
        // Check if permissions are enabled and if not request

        if (PermissionsManager.areLocationPermissionsGranted(getContext())) {

            // Get an instance of the component
            LocationComponent locationComponent = mapboxMap.getLocationComponent();

            // Set the LocationComponent activation options
            LocationComponentActivationOptions locationComponentActivationOptions =
                    LocationComponentActivationOptions.builder(getActivity(), loadedMapStyle)
                            .useDefaultLocationEngine(false)
                            .build();

            // Activate with the LocationComponentActivationOptions object
            locationComponent.activateLocationComponent(locationComponentActivationOptions);

            // Enable to make component visible
            locationComponent.setLocationComponentEnabled(true);

            // Set the component's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING);

            // Set the component's render mode
            locationComponent.setRenderMode(RenderMode.COMPASS);

            initLocationEngine();
        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(getActivity());
        }
    }

    /**
     * Set up the LocationEngine and the parameters for querying the device's location
     */
    @SuppressLint("MissingPermission")
    private void initLocationEngine() {
        locationEngine = LocationEngineProvider.getBestLocationEngine(getActivity());

        LocationEngineRequest request = new LocationEngineRequest.Builder(DEFAULT_INTERVAL_IN_MILLISECONDS)
                .setPriority(LocationEngineRequest.PRIORITY_HIGH_ACCURACY)
                .setMaxWaitTime(DEFAULT_MAX_WAIT_TIME).build();
        locationEngine.requestLocationUpdates(request, callback, getActivity().getMainLooper());
        locationEngine.getLastLocation(callback);
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(getActivity(), R.string.user_location_permission_explanation, Toast.LENGTH_LONG).show();
    }
    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            mapboxMap.getStyle(new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(@NonNull Style style) {
                    Log.d("Map", "here");
                    enableLocationComponent(style);
                }
            });
        } else {
            Toast.makeText(getActivity(), R.string.user_location_permission_not_granted, Toast.LENGTH_LONG).show();
        }
    }


    private void displayTrucks(List<Truck> trucks){
        trucks.forEach(truck -> displayTruck(truck));
        symbolManager.create(symbolOptionsList);
    }

    private void displayTruck(Truck truck){
        // Use an XML layout to create a View object
        View customView = LayoutInflater.from(getActivity()).inflate(R.layout.marker_view_bubble, null);
        customView.setLayoutParams(new FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));

        // Set the View's TextViews with content
        TextView titleTextView = customView.findViewById(R.id.marker_window_title);
        titleTextView.setText(R.string.draw_marker_options_title);

        TextView snippetTextView = customView.findViewById(R.id.marker_window_snippet);
        snippetTextView.setText(R.string.draw_marker_options_snippet);

        titleTextView.setText(truck.getName());

        double truckLat = Double.parseDouble(truck.getLatitude());
        double truckLong = Double.parseDouble(truck.getLongitude());

        MarkerView markerView = new MarkerView(new LatLng(truckLat, truckLong), customView);
        markerViewList.add(markerView);
        String jsonString = (String)"{pos : " + (markerViewList.size()-1) + "}";
        JsonObject jsonObject = (JsonObject) JsonParser.parseString(jsonString);



        // Create a symbol at the specified location.
        SymbolOptions symbolOptions = new SymbolOptions()
                .withLatLng(new LatLng(truckLat, truckLong)) //these are the coordinates of the truck
                .withIconImage("marker-ic-id")
                .withData(jsonObject)
                .withIconSize(1.3f);
        //defines marker view(the pop up bubble) but doesnt display it yet


        symbolOptionsList.add(symbolOptions);

    }






    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Prevent leaks
        if (locationEngine != null) {
            locationEngine.removeLocationUpdates(callback);
        }
        markerViewManager.onDestroy();
        mapView.onDestroy();
    }






    private static class LocationChangeListeningActivityLocationCallback implements LocationEngineCallback<LocationEngineResult> {

        private final WeakReference<MapFragment> activityWeakReference;

        LocationChangeListeningActivityLocationCallback(MapFragment activity) {
            this.activityWeakReference = new WeakReference<>(activity);
        }

        /**
         * The LocationEngineCallback interface's method which fires when the device's location has changed.
         *
         * @param result the LocationEngineResult object which has the last known location within it.
         */
        @Override
        public void onSuccess(LocationEngineResult result) {
            MapFragment activity = activityWeakReference.get();

            if (activity != null) {
                Location location = result.getLastLocation();

                if (location == null) {
                    Log.d("MapFragment", "here");
                    return;
                }
                /*
                // Create a Toast which displays the new location's coordinates
                Toast.makeText(activity.getActivity(), String.format(activity.getActivity().getString(R.string.new_location),
                        String.valueOf(result.getLastLocation().getLatitude()),
                        String.valueOf(result.getLastLocation().getLongitude())),
                        Toast.LENGTH_SHORT).show();
                */
                // Pass the new location to the Maps SDK's LocationComponent
                if (activity.mapboxMap != null && result.getLastLocation() != null) {
                    activity.mapboxMap.getLocationComponent().forceLocationUpdate(result.getLastLocation());
                }
            }
        }

        /**
         * The LocationEngineCallback interface's method which fires when the device's location can't be captured
         *
         * @param exception the exception message
         */
        @Override
        public void onFailure(@NonNull Exception exception) {
            MapFragment activity = activityWeakReference.get();
            if (activity != null) {
                Toast.makeText(activity.getActivity(), exception.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }







}
