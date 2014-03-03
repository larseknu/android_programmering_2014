package com.capgemini.playingwithservices;

import android.app.IntentService;
import android.content.Intent;
import android.location.Location;

public class OnDemandWorkerService extends IntentService {
	public OnDemandWorkerService() {
		super("OnDemandWorker");
	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
		LogHelper.ProcessAndThreadId("OnDemandWorker.onHandleIntent");
		
		String fileName = intent.getStringExtra("fileName");
		if (fileName == null)
			fileName = "ServiceOutputFile.out";
		
		Worker worker = new Worker(this);
		
		Location location = worker.getLocation();
		
		String address = worker.reverseGeocode(location);
		
		worker.save(location, address, fileName);
	}
}
