package com.capgemini.playingwithservices;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;

public class WorkerService extends Service {
	Worker _worker;

	@Override
	public void onCreate() {
		_worker = new Worker(this);
		_worker.MonitorGpsInBackground();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Location location = _worker.getLocation();
		
		String address = _worker.reverseGeocode(location);
		
		_worker.save(location, address, "WorkerService.out");
		
		return Service.START_NOT_STICKY;
	}
	
	@Override
	public void onDestroy() {
		_worker.stopGpsMonitoring();
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
}
