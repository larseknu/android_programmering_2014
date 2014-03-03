package com.capgemini.playingwithservices;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		LogHelper.ProcessAndThreadId("MyActivity.onCreate");
	}

	private void doWork() {
		LogHelper.ProcessAndThreadId("MyActivity.doWork");
		
		Intent intent = new Intent(this, OnDemandWorkerService.class);
		intent.putExtra("fileName", "IntentFileName.out");
		startService(intent);
		
		Toast toast = Toast.makeText(this, "Work has started", Toast.LENGTH_LONG);
		toast.show();
	}
	
	private void doMoreWork() {
		Intent intent = new Intent(this, WorkerService.class);
		startService(intent); 
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.do_work:
				doWork();
				break;
			case R.id.worker_service:
				doMoreWork();
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
