package com.larseknu.playingwithlayouts;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		View parent = findViewById(android.R.id.content);
		parent.setBackgroundColor(Color.RED);
		
		ViewServer.get(this).addWindow(this);
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
