package com.capgemini.playingwithgooglemaps;

import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_HYBRID;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_NONE;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_SATELLITE;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_TERRAIN;

import java.util.ArrayList;

import org.w3c.dom.Document;

import android.graphics.Color;
import android.graphics.Point;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivity extends ActionBarActivity implements OnMapLongClickListener, OnItemSelectedListener {
	private int kittyCounter = 1;
	private ArrayList<Marker> kittyMarkers;
	private GoogleMap map;
	private LatLng HIOF = new LatLng(59.12797849, 11.35272861);
	private LatLng FREDRIKSTAD = new LatLng(59.21047628, 10.93994737);
	private LatLng myPosition = new LatLng(0,0);
	private GMapV2Direction mapDirection;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		kittyMarkers = new ArrayList<Marker>();
		mapDirection = new GMapV2Direction();

		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		map = mapFragment.getMap();

		map.addMarker(new MarkerOptions().position(HIOF).title("Østfold University College"));
		map.addMarker(new MarkerOptions().position(FREDRIKSTAD).title("Fredrikstad Kino"));

		if (savedInstanceState == null) {
			map.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(HIOF, 13, 0, 0)));
			map.animateCamera(CameraUpdateFactory.newLatLng(FREDRIKSTAD), 2000, null);
		}
		else {
			Bundle bundle = savedInstanceState.getBundle("lost_kittens");            
            KittenLocation kittenLocation = bundle.getParcelable("found_kitten");
			map.addMarker(new MarkerOptions().position(kittenLocation.getLatLng()).title(kittenLocation.getName()).snippet("Found Kitten")
					.icon(BitmapDescriptorFactory.fromResource(getResources().getIdentifier("kitten_0" + (kittyCounter % 3 + 1), "drawable", "com.capgemini.playingwithgooglemaps"))));
		}
		map.setOnMapLongClickListener(this);
		
		map.setMyLocationEnabled(true);
		
		UiSettings uiSettings = map.getUiSettings();
		uiSettings.setCompassEnabled(false);
		uiSettings.setTiltGesturesEnabled(false);
		uiSettings.setZoomControlsEnabled(false);
		
		Spinner spinner = (Spinner) findViewById(R.id.layers_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.layers_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
	}
	
	@Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        
        if (!kittyMarkers.isEmpty()) {
        	Marker kittyMarker = kittyMarkers.get(0);
        	KittenLocation kittenLocation = new KittenLocation(kittyMarker.getTitle(), new LatLng(kittyMarker.getPosition().latitude, kittyMarker.getPosition().longitude));
        	
        	Bundle bundle = new Bundle();
        	outState.putParcelable("found_kitten", kittenLocation);
            outState.putBundle("lost_kittens", bundle);
        }
    }
	
	private class drawRoute extends AsyncTask<Void, Void, Document> {
		Document doc;
		PolylineOptions rectLine;

		@Override 
		protected Document doInBackground(Void... params) {
			doc = mapDirection.getDocument(myPosition, HIOF, GMapV2Direction.MODE_DRIVING);

			ArrayList<LatLng> directionPoint = mapDirection.getDirection(doc);
			rectLine = new PolylineOptions().width(3).color(Color.BLUE);

			for (int i = 0; i < directionPoint.size(); i++) {
				rectLine.add(directionPoint.get(i));
			}

			return null;
		}

		@Override
		protected void onPostExecute(Document result) {
			map.addPolyline(rectLine);
		}
	}
	
	@Override
	public void onMapLongClick(LatLng point) {
		Marker kitty = map.addMarker(new MarkerOptions().position(point).title("Mittens the " + kittyCounter + ".").snippet("Kitty Invasion")
				.icon(BitmapDescriptorFactory.fromResource(getResources().getIdentifier("kitten_0" + (kittyCounter % 3 + 1), "drawable", "com.capgemini.playingwithgooglemaps"))));
		kittyMarkers.add(kitty);
		kittyCounter++;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.kitty_attack:
			for (Marker kittyMarker : kittyMarkers)
				animateMarker(kittyMarker, FREDRIKSTAD);
			break;
		case R.id.draw_route:
			Location myLocation = map.getMyLocation();
			myPosition = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
			new drawRoute().execute();
			break;
		default:
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	public void animateMarker(final Marker marker, final LatLng toPosition) {
		final Handler handler = new Handler();
		final long start = SystemClock.uptimeMillis();
		Projection proj = map.getProjection();
		Point startPoint = proj.toScreenLocation(marker.getPosition());
		final LatLng startLatLng = proj.fromScreenLocation(startPoint);
		final long duration = 500;

		final Interpolator interpolator = new LinearInterpolator();

		handler.post(new Runnable() {
			@Override
			public void run() {
				long elapsed = SystemClock.uptimeMillis() - start;
				float t = interpolator.getInterpolation((float) elapsed / duration);
				double lng = t * toPosition.longitude + (1 - t) * startLatLng.longitude;
				double lat = t * toPosition.latitude + (1 - t) * startLatLng.latitude;
				marker.setPosition(new LatLng(lat, lng));

				if (t < 1.0) {
					// Post again 16ms later.
					handler.postDelayed(this, 16);
				}
			}
		});
	}

	@Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch ((String) parent.getItemAtPosition(position)) {
			case "Hybrid":
				map.setMapType(MAP_TYPE_HYBRID);
				break;
			case "Satellite":
				map.setMapType(MAP_TYPE_SATELLITE);
				break;
			case "Terrain":
				map.setMapType(MAP_TYPE_TERRAIN);
				break;
			case "None":
				map.setMapType(MAP_TYPE_NONE);
				break;
			default:
				map.setMapType(MAP_TYPE_NORMAL);
				break;
        }
    }

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
	}
}
