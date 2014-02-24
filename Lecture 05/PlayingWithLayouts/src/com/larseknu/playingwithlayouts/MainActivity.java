package com.larseknu.playingwithlayouts;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
	private View smileys; 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ViewServer.get(this).addWindow(this);
		
		smileys = findViewById(R.id.happySmileys);
	}
	
	public void toggleSmileys(View view) {
		boolean visible = (smileys.getVisibility() == View.VISIBLE);
		smileys.setVisibility(visible ? View.GONE : View.VISIBLE);
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
