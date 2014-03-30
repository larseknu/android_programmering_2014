package com.capgemini.playinwithnotifications;
import java.util.ArrayList;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	private int NOTIFICATION_ID = 1;
	
	private int _mNotificationCount = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void btnNotificationOnClick(View view) {
		String title = "Avengers!";
		String text = "Assemble";
		
		Intent intent = new Intent(this, DetailsActivity.class);
		intent.setAction("Notification");
		intent.putExtra(DetailsActivity.TITLE_EXTRA, title);
		intent.putExtra(DetailsActivity.BODY_TEXT_EXTRA, text);
		
		NotificationCompat.Builder builder = initBasicBuilder(title, text, intent);
		
		Notification notification = builder.build();
		
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.notify(NOTIFICATION_ID, notification);
	}
	
	public void btnPersonalNotificationOnClick(View view) {
		String title = "Avengers, assemble!";
		String text = "Help needed!";
		
		// Create the Intent to display the text in an Activity
		Intent intent = new Intent(this, DetailsActivity.class);
		intent.setAction("PersonalNotification"); 
		intent.putExtra(DetailsActivity.TITLE_EXTRA, title);
		intent.putExtra(DetailsActivity.BODY_TEXT_EXTRA, text);
		
		// Create Builder with basic notification info
		NotificationCompat.Builder builder = initBasicBuilder(title, text, intent);
		
		// Make it personal
		builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_captain_america));
		
		// Construct the Notification
		Notification notification = builder.build();
		
		// Display the Notification
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.notify(NOTIFICATION_ID+1, notification);
	}
	
	public void btnMultiNotificationsOnClick(View view) {
		String title = "Messages from Hulk";
		String text = "I need help";
		
		String detailText1 = "I need help";
		String detailText2 = "I'm getting angry!";
	    String detailText3 = "AAAAARGH!";
	    ++_mNotificationCount;

	    ArrayList<String> textValues = new ArrayList<String>();
	    textValues.add(detailText1);
	    textValues.add(detailText2);
	    textValues.add(detailText3);
		
		// Create the Intent to display the info in an Activity
		Intent intent = new Intent(this, ListActivity.class);
		intent.setAction("MultiNotifications");
		intent.putExtra(ListActivity.TITLE_EXTRA, title);
		intent.putExtra(ListActivity.TEXT_VALUES_EXTRA, textValues);
		
		// Create Builder with basic notification info
		NotificationCompat.Builder builder = initBasicBuilder(title, text, intent);
		
		// Make multi
		builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_hulk))
				.setNumber(_mNotificationCount)
				.setTicker("You received another message from Hulk");
		
		Notification notification = builder.build();
		
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.notify(NOTIFICATION_ID+1, notification);
	}
	
	public void btnBigTextNotificationOnClick(View view) {
		String title = "Tony Stark";
		String text = "I'm not saying I'm responsible for this country's longest run of uninterrupted peace in 35 years! I'm not saying that from the ashes of captivity, never has a Phoenix metaphor been more personified! I'm not saying Uncle Sam can kick back on a lawn chair, sipping on an iced tea, because I haven't come across anyone man enough to go toe to toe with me on my best day! It's not about me. It's not about you, either. It's about legacy, the legacy left behind for future generations. It's not about us! ";
		String bigSummary = "Phoenix metaphor personified";
		
		Intent intent = new Intent(this, DetailsActivity.class);
		intent.setAction("BigTextNotification");
		intent.putExtra(DetailsActivity.TITLE_EXTRA, title);
		intent.putExtra(DetailsActivity.BODY_TEXT_EXTRA, text);
		
		NotificationCompat.Builder builder = initBasicBuilder(title, text, intent);
		
		builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_iron_man));
		
		// Add the Big Text Style
	    NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
	    bigTextStyle.setBigContentTitle(title)
	        .setSummaryText(bigSummary)
	        .bigText(text);
	    builder.setStyle(bigTextStyle);
		
		Notification notification = builder.build();
		
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.notify(NOTIFICATION_ID+2, notification);
	}
	
	public void btnBigPictureNotificationOnClick (View view) {
		String title = "Avengers - to New York";
		String text = "New York is under attack!";
		
		// Create the Intent to display the picture in an Activity
		Intent intent = new Intent(this, PictureActivity.class);
		intent.setAction("BigPictureNotification");
		intent.putExtra(PictureActivity.TITLE_EXTRA, title);
		intent.putExtra(PictureActivity.IMAGE_TEXT_EXTRA, text);
	    intent.putExtra(PictureActivity.IMAGE_RESOURCE_ID_EXTRA, R.drawable.avengers_new_york);
		
		// Create Builder with basic notification info
		NotificationCompat.Builder builder = initBasicBuilder(title, text, intent);
		
		builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_hulk));
		
		// Add the Big Picture Style
	    NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
	    bigPictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.avengers_new_york))
	    				.setSummaryText(text)
	    				.setBigContentTitle(title);
	    
	    builder.setStyle(bigPictureStyle);
		
	    // Construct the Notification
		Notification notification = builder.build();
		
		// Display the Notification
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.notify(NOTIFICATION_ID+3, notification);
	}
	
	private NotificationCompat.Builder initBasicBuilder(String title, String text, Intent intent) {
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
		builder.setSmallIcon(R.drawable.ic_avengers)
				.setContentTitle(title)
				.setContentText(text);
		
		if (intent != null){
			PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			builder.setContentIntent(pendingIntent);
		}
		
		return builder;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}