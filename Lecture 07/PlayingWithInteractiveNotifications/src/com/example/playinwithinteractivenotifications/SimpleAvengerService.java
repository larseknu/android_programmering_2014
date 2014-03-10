package com.example.playinwithinteractivenotifications;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class SimpleAvengerService extends Service {
  public static final String START_ACTION = "start";
  public static final String STOP_ACTION = "stop";

  private static final int SERVICE_NOTIFY_ID = 100;

  public static Intent getStopIntent(Context context) {
    Intent intent = new Intent(context, SimpleAvengerService.class);
    intent.setAction(SimpleAvengerService.STOP_ACTION);

    return intent;
  }

  public static Intent getStartIntent(Context context) {
    Intent intent = new Intent(context, SimpleAvengerService.class);
    intent.setAction(SimpleAvengerService.START_ACTION);

    return intent;
  }

  public IBinder onBind(Intent intent) {
    return null;
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    String action = intent.getAction();

    if(START_ACTION.equalsIgnoreCase(action))
      handleStart();
    else if (STOP_ACTION.equalsIgnoreCase(action))
      handleStop();
    else
      Log.e("SimpleAvengerService.onStartCommand", "Unrecognized Action:" + (action != null ? action : "<NULL>"));

    return START_NOT_STICKY;
  }

  private void handleStart() {
    // Intent to display Service Control Activity
    Intent activityIntent = new Intent(this, ServiceControlActivity.class);
    PendingIntent activityPendingIntent = PendingIntent.getActivity(this, 0, activityIntent, 0);

    Intent stopServiceIntent = getStopIntent(this);
    PendingIntent stopServicePendingIntent = PendingIntent.getService(this, 0, stopServiceIntent, 0);

    NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
    builder.setSmallIcon(R.drawable.ic_avengers)
        .setContentTitle("Avengers Service")
        .setContentText("Service in the foreground")
        .setContentIntent(activityPendingIntent)
        .addAction(android.R.drawable.ic_lock_power_off, "Stop", stopServicePendingIntent);

    Notification notification = builder.build();
    startForeground(SERVICE_NOTIFY_ID, notification);

    displayStatusMessage("Service starting");
  }

  private void handleStop() {

    stopForeground(true);

    stopSelf();
    displayStatusMessage("Service stopping");
  }

  private void displayStatusMessage(String message) {
    Log.d("SimpleAvengersService", message);
    Toast.makeText(this, message, Toast.LENGTH_LONG).show();
  }
}
