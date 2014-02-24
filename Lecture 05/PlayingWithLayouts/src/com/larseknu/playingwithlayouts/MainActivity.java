package com.larseknu.playingwithlayouts;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

public class MainActivity extends Activity {
	private View smileys; 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ViewServer.get(this).addWindow(this);
	}
	
	public void toggleSmileys(View view) {
		if (smileys == null)
		{
			smileys = ((ViewStub) findViewById(R.id.happySmileys)).inflate();
		}
		else
		{
			boolean visible = (smileys.getVisibility() == View.VISIBLE);
			smileys.setVisibility(visible ? View.GONE : View.VISIBLE);
		}
	}
	
	@Override
    public void onDestroy() {
    	super.onDestroy();
    	ViewServer.get(this).removeWindow(this);
    }

    @Override
    public void onResume() {
    	super.onResume();
    	ViewServer.get(this).setFocusedWindow(this);
    }
}
