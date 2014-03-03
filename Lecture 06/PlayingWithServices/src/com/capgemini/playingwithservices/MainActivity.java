package com.capgemini.playingwithservices;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		LogHelper.ProcessAndThreadId("MyActivity.onCreate");
	}

	private void doWork() {
		Worker worker = new Worker(this);
		
		Location location = worker.getLocation();
		
		String address = worker.reverseGeocode(location);
		
		worker.save(location, address, "ServiceOutputFile.out");
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
