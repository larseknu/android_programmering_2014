package com.capgemini.playingwiththreads;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView _outputTextView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		_outputTextView = (TextView) findViewById(R.id.outputTextView); 
		StrictMode.enableDefaults(); 
	}
	
	public void doWork() {
		Worker worker = new Worker(MainActivity.this);
		_outputTextView.setText("Starting");
		
		Location location = worker.getLocation();
		_outputTextView.setText("Retrieved Location");
		
		String address = worker.reverseGeocode(location);
		_outputTextView.setText("Retrieved Address");
		
		worker.save(location, address, "FancyFileName.out");
		_outputTextView.setText("Done");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.do_work:
			doWork();
			break;
		default:
			super.onOptionsItemSelected(item);
			break;
		}
		return true;
	}
}