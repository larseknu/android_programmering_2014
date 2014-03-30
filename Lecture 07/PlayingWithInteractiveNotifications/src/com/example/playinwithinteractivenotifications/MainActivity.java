package com.example.playinwithinteractivenotifications;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.View;
import android.widget.RemoteViews;

public class MainActivity extends Activity {
	private int NOTIFICATION_ID = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void btnNotificationNavigationButtonOnClick (View view) {
		String title = "What happens with the back button?";
		String text = "Let's make it function correctly";
		
		Intent intent = new Intent("com.capgemini.playinwithnotifications.action.DETAILS");
		
		TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
		taskStackBuilder.addNextIntentWithParentStack(intent);
		
		PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
		//PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
		
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
		builder.setSmallIcon(R.drawable.ic_avengers)
				.setAutoCancel(true)
				.setContentTitle(title)
				.setContentText(text)
				.setContentIntent(pendingIntent);
		Notification notification = builder.build();
		
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.notify(NOTIFICATION_ID, notification);		
	}
	
	public void btnStartServiceOnClick(View view) {
	      Intent intent = new Intent(this, SimpleAvengerService.class);
	      intent.setAction(SimpleAvengerService.START_ACTION);
	      
	      startService(intent);
    }

    public void btnCustomNotifyOnClick(View view) {
	      RemoteViews notificationViews = new RemoteViews(getPackageName(), R.layout.notification_simple);

	      Intent stopIntent = SimpleAvengerService.getStopIntent(this);
	      PendingIntent stopPendingIntent = PendingIntent.getService(this, 0, stopIntent, 0);
	      notificationViews.setOnClickPendingIntent(R.id.btnStop, stopPendingIntent);

	      Intent startIntent = SimpleAvengerService.getStartIntent(this);
	      PendingIntent startPendingIntent = PendingIntent.getService(this, 0, startIntent, 0);
	      notificationViews.setOnClickPendingIntent(R.id.btnStart, startPendingIntent);

	      NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

	      builder.setSmallIcon(R.drawable.ic_avengers)
	      			.setContent(notificationViews);

	      Notification notification = builder.build();

	      NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
	      notificationManager.notify(NOTIFICATION_ID, notification);
	    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
