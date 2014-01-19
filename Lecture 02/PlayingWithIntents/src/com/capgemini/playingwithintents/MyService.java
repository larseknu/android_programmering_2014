package com.capgemini.playingwithintents;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.example.playingwithintents.R;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class MyService extends Service {
	int _notificationId = 1;
	int _callCount = 0;
	
	@Override
	public void onCreate() {
		Log.i("MyService", "onCreate");
		_callCount = 0;
		startInForeground();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		_callCount++;
		Log.i("MyService", "onStartCommand - call #" + _callCount);
		
		SimpleDateFormat dateFormat = null;
		String action = intent.getAction();
		
		if (action.equals("com.capgemini.action.LOG_TIME")) {
			dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
		} else if (action.equals("com.capgemini.action.LOG_DATE")) 
		{
			dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
		} else {
			Log.i("MyService", "Missing or unrecognized action");
		}
		
		if (dateFormat != null) {
			long now = (new Date()).getTime();
			Log.i("MyService", dateFormat.format(now));
		}
		
		return START_NOT_STICKY;
	}
	
	@SuppressWarnings("deprecation")
	private void startInForeground()
	{
		// Set basic notification information
		int notificationIcon = R.drawable.ic_launcher;
		String notificationTickerText = "Launching my service";
		long notificationTimeStamp = System.currentTimeMillis();
		
		// Describe what to do if the user clicks on the notification in the status bar
		String notificationTitleText = "My Service";
        String notificationBodyText = "Does non-UI processing";
        Intent notificationActivityIntent = new Intent(this, MainActivity.class);
        notificationActivityIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent startMyActivityPendingIntent = PendingIntent.getActivity(this, 0, notificationActivityIntent, 0);
		
        Notification foregroundNotification = null;
        
		final int sdkVersion = Build.VERSION.SDK_INT;
		if (sdkVersion < Build.VERSION_CODES.HONEYCOMB)
		{
			foregroundNotification = new Notification(notificationIcon, notificationTickerText, notificationTimeStamp);
			foregroundNotification.setLatestEventInfo(this, notificationTitleText, notificationBodyText, startMyActivityPendingIntent);
		}
		else
		{
			NotificationCompat.Builder notificationbuilder = new NotificationCompat.Builder(this)
														.setSmallIcon(notificationIcon)
														.setTicker(notificationTickerText)
													    .setWhen(notificationTimeStamp);
			
			foregroundNotification = notificationbuilder.setContentTitle(notificationTitleText)
											.setContentText(notificationBodyText)
											.setContentIntent(startMyActivityPendingIntent).build();
		}
        // ID to use w/ Notification Manager for _foregroundNotification
        // Set the service to foreground status and provide notification info
        startForeground(_notificationId, foregroundNotification);
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
