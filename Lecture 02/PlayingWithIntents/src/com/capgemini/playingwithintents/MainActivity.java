package com.capgemini.playingwithintents;

import com.example.playingwithintents.R;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Intent intent = getIntent();
		Log.i("MainActivity", "onCreate - " + intent.toString());
	}

	public void startMyService(View view)
	{
		// Explicit
		//Intent startServiceIntent = new Intent(this, MyService.class);
		
		// Implicit
		Intent startServiceIntent = new Intent("com.capgemini.action.LOG_TIME");
		startService(startServiceIntent);
	}
	
	public void startOtherActivity(View view)
	{
		// Explicit
		Intent startOtherActivity = new Intent(this, OtherActivity.class);
		
		// Implicit
		//Intent startOtherActivity = new Intent("com.capgemini.action.other_activity");
		startActivity(startOtherActivity);
	}
	
	public void startHelloWorldActivity(View view)
	{
		Intent startOtherActivity = new Intent("com.capgemini.action.HELLO_WORLD");
		
		startActivity(startOtherActivity);
	}
	
	public void openWebsite(View view)
	{
		Intent intent = new Intent("android.intent.action.VIEW");
		Uri uri = Uri.parse("http://www.capgemini.no");
		intent.setData(uri);	
		startActivity(intent);
	}
	
	// If I want the successive intents
	@Override
	protected void onNewIntent(Intent intent)
	{
		Log.i("MyActivity", "onNewIntent - " + intent.toString());
	}
}
