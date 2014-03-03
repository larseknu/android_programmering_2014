package com.capgemini.playingwithservices;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;

public class WorkerService extends Service {
	Worker _worker;
	ExecutorService _executorService;
	ScheduledExecutorService _scheduledStopService;

	@Override
	public void onCreate() {
		_worker = new Worker(this);
		_worker.MonitorGpsInBackground();
		_executorService = Executors.newSingleThreadExecutor();
		_scheduledStopService = Executors.newSingleThreadScheduledExecutor();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		ServiceRunnable runnable = new ServiceRunnable(this, startId);
		_executorService.execute(runnable);
		
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
	
	class ServiceRunnable implements Runnable {
		WorkerService _theService;
		int _startId;
		
		public ServiceRunnable(WorkerService theService, int startId) {
			_theService = theService;
			_startId = startId;
		}
		
		public void run() {
			LogHelper.ProcessAndThreadId("WorkerService.onStartCommand");
			Location location = _worker.getLocation();
	
			String address = _worker.reverseGeocode(location);
			
			_worker.save(location, address, "WorkerService.out");
			
			DelayedStopRequest delayedStopRequest = new DelayedStopRequest(_theService, _startId);
			_theService._scheduledStopService.schedule(delayedStopRequest, 10, TimeUnit.SECONDS);
		}
	}
	
	class DelayedStopRequest implements Runnable {
		WorkerService _theService;
		int _startId;
		
		public DelayedStopRequest(WorkerService theService, int startId) {
			_theService = theService;
			_startId = startId;
		}
		
		public void run() {
			_theService.stopSelfResult(_startId);
		}
	}
}
