package com.capgemini.playinwithnotifications;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends Activity {
	public static String TITLE_EXTRA = "title extra";
	public static String BODY_TEXT_EXTRA = "body text extra";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		// Show the Up button in the action bar.
		setupActionBar();
		
		Intent createIntent = getIntent();
		String title = createIntent.getStringExtra(TITLE_EXTRA);
		String bodyText = createIntent.getStringExtra(BODY_TEXT_EXTRA);
		
		if (title != null)
			setTitle(title);
		if (bodyText != null)
			((TextView)findViewById(R.id.body_text)).setText(bodyText);
		
	}
	
	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}
}
