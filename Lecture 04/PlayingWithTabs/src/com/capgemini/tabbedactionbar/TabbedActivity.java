package com.capgemini.tabbedactionbar;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class TabbedActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		ActionBar.TabListener tabListener1 = new SimpleTabListener(this, "com.capgemini.tabbedactionbar.iPhoneOrDroidFragment");
		ActionBar.Tab iPhoneDroidTab = actionBar.newTab();
		iPhoneDroidTab.setText("iPhone or Droid");
		iPhoneDroidTab.setTabListener(tabListener1);
		actionBar.addTab(iPhoneDroidTab);
		
		ActionBar.TabListener tablistener2 = new SimpleTabListener(this, "com.capgemini.tabbedactionbar.GoodCodeFragment");
		ActionBar.Tab goodCodeTab = actionBar.newTab()
											.setText("Good Code")
											.setTabListener(tablistener2);
		actionBar.addTab(goodCodeTab);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_tab_control, menu);
		return true;
	}
	
	public void onToggleTitleClick(MenuItem menuItem) {
		
	}
}
