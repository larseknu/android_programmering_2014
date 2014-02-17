package com.capgemini.tabbedactionbar;

import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.util.Log;

public class SimpleTabListener implements TabListener {
    Context _context;
    String _tabFragmentClassName;
    Fragment _tabFragment = null;
    int _viewGroupId;

    public SimpleTabListener(Context context, String tabFragmentClassName, int viewGroupId) {
        _context = context;
        _tabFragmentClassName = tabFragmentClassName;
        _viewGroupId = viewGroupId;
     }

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction fragmentTransaction) {
		
		if(_tabFragment == null) {
			_tabFragment = Fragment.instantiate(_context, _tabFragmentClassName);
			fragmentTransaction.add(_viewGroupId, _tabFragment);
		}
		else
			fragmentTransaction.attach(_tabFragment);
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction fragmentTransaction) {
		fragmentTransaction.detach(_tabFragment);
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction fragmentTransaction) {
		
	}
	
	public static void SetupTabbedNavigation(Activity containingActivity,
            int displayNameResourceId,
            int fragmentClassNameResourceId) {
		SetupTabbedNavigation(containingActivity, displayNameResourceId, fragmentClassNameResourceId, android.R.id.content);
	}
	
    public static void SetupTabbedNavigation(Activity containingActivity,
                                                               int displayNameResourceId,
                                                               int fragmentClassNameResourceId,
                                                               int viewGroupId) {
        // Load the display values and class names from the resources
        String[] displayNameList = containingActivity.getResources().getStringArray(displayNameResourceId);
        String[] fragmentList = containingActivity.getResources().getStringArray(fragmentClassNameResourceId);

        if(fragmentList.length != displayNameList.length) {
            Log.e("SetupTabbedNavigation",
                    "ERROR - List of fragment class names and list of display names do not contain the same number of entries");
            return ;
        }

        ActionBar actionBar = containingActivity.getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        
        ActionBar.TabListener tablistener;
        for (int i=0; i < fragmentList.length; i++) {
        	tablistener = new SimpleTabListener(containingActivity, fragmentList[i], viewGroupId);
        	actionBar.addTab(
        			actionBar.newTab()
        					.setText(displayNameList[i])
        					.setTabListener(tablistener)
        	);
        }
    }

}
