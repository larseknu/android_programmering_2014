package com.capgemini.tabbedactionbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    public void onTabbedClick(MenuItem menuItem) {
        Intent intent = new Intent(this, TabbedActivity.class);
        startActivity(intent);
     }
    
    public void onPartialTabbedClick (MenuItem menuItem) {
        Intent intent = new Intent(this, PartialTabbedActivity.class);
        startActivity(intent);
     }

    public void onExitClick(MenuItem menuItem) {
         finish();
     }
	
}
