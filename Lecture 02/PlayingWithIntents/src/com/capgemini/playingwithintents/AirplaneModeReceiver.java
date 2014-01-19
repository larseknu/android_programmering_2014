package com.capgemini.playingwithintents;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AirplaneModeReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		boolean modeIsOn = intent.getBooleanExtra("state", false);
		Log.i("AirplaneModeReceiver", "Mode is " + (modeIsOn ? "on" : "off"));
	}

}
