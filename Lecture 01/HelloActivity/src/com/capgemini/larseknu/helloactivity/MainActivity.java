package com.capgemini.larseknu.helloactivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	private String tag = "Livssyklus";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d(tag, "Inne i onCreate()");
	}
	
	public void doSomething(View view)
	{
		startActivity(new Intent("com.capgemini.second_activity"));
	}
	
	@Override
	protected void onStart()
	{
		super.onStart();
		Log.d(tag, "Inne i onStart()");
	}
	
	@Override
	protected void onResume()
	{
		super.onResume();
		Log.d(tag, "Inne i onResume()");
	}
	
	@Override
	protected void onPause()
	{
		super.onPause();
		Log.d(tag, "Inne i onPause()");
	}
	
	@Override
	protected void onStop()
	{
		super.onStop();
		Log.d(tag, "Inne i onStop()");
	}
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		Log.d(tag, "Inne i onDestroy()");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
