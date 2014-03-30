package com.capgemini.playingwithgooglemaps;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends ActionBarActivity {
	private GoogleMap map;
	private LatLng HIOF = new LatLng(59.12797849, 11.35272861);
	private LatLng FREDRIKSTAD = new LatLng(59.21047628, 10.93994737);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		map = mapFragment.getMap();
		
		map.addMarker(new MarkerOptions().position(HIOF).title("Østfold University College"));
		map.addMarker(new MarkerOptions().position(FREDRIKSTAD).title("Fredrikstad Kino"));
		
		map.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(HIOF, 13, 0, 0)));
		
		map.animateCamera(CameraUpdateFactory.newLatLng(FREDRIKSTAD), 2000, null);
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
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
