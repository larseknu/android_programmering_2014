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
	Thread _workerThread;
	String _tempMessage;
	AsyncTaskWorker _asyncTaskWorker;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		_outputTextView = (TextView) findViewById(R.id.outputTextView); 
		StrictMode.enableDefaults(); 
	}
	
	public void doAsyncWork() {
		_asyncTaskWorker = new AsyncTaskWorker();
		_asyncTaskWorker.execute(_outputTextView);
	}
	
	public void doWork() {
		_workerThread = new Thread(new Runnable() {
			public void run() {
				Worker worker = new Worker(MainActivity.this);
				updateUI("Starting");
				
				Location location = worker.getLocation();
				updateUI("Retrieved Location");
				
				String address = worker.reverseGeocode(location);
				updateUI("Retrieved Address");
				
				worker.save(location, address, "FancyFileName.out");
				updateUI("Done");
			}
		});
		_workerThread.start(); 
	}

	public void updateUI(String message) {
		_tempMessage = message;
		
		_outputTextView.post(new Runnable() {
			public void run() {
				_outputTextView.setText(_tempMessage);
			}
		});
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
		case R.id.do_async_work:
			doAsyncWork();
			break;
		default:
			super.onOptionsItemSelected(item);
			break;
		}
		return true;
	}
}