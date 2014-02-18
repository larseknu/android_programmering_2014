package com.capgemini.tabbedactionbar;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.util.Log;
import android.widget.ArrayAdapter;

public class SimpleNavigationListener  implements ActionBar.OnNavigationListener{
    Activity _containingActivity;
    ArrayAdapter<CharSequence> _displayNameList;
    String[] _fragmentList;
    int _viewGroupId;

    public SimpleNavigationListener(Activity containingActivity, String[] fragmentList,
                                    ArrayAdapter<CharSequence> displayNameList,
                                    int viewGroupId)
    {
        _containingActivity = containingActivity;
        _displayNameList = displayNameList;
        _fragmentList = fragmentList;
        _viewGroupId = viewGroupId;
    }

    public boolean onNavigationItemSelected(int i, long l) {
        String fragmentClassName = _fragmentList[i];
        Fragment theFragment = Fragment.instantiate(_containingActivity, fragmentClassName);

        FragmentTransaction fragmentTransaction =
                _containingActivity.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(_viewGroupId, theFragment);
        fragmentTransaction.commit();

        return true;

    }

    public static SimpleNavigationListener SetupListNavigation(Activity containingActivity,
                                                               int displayNameResourceId,
                                                               int fragmentClassNameResourceId)
    {
        return SetupListNavigation(containingActivity, displayNameResourceId,
                fragmentClassNameResourceId, android.R.id.content);
    }

    public static SimpleNavigationListener SetupListNavigation(Activity containingActivity,
                                                               int displayNameResourceId,
                                                               int fragmentClassNameResourceId,
                                                               int viewGroupId) {
        // Load the display values and class names from the resources
        ArrayAdapter<CharSequence> displayNameList =
                ArrayAdapter.createFromResource(
                        containingActivity,
                        displayNameResourceId,
                        android.R.layout.simple_spinner_dropdown_item);
        String[] fragmentList = containingActivity.getResources().getStringArray(fragmentClassNameResourceId);

        if(fragmentList.length != displayNameList.getCount()) {
            Log.e("SetupListNavigation",
                    "ERROR - List of fragment class names and list of display names do not contain the same number of entries");
            return null;
        }

        // Create the listener
        SimpleNavigationListener listener =
                new SimpleNavigationListener(containingActivity, fragmentList,
                        displayNameList, viewGroupId
                        );

        ActionBar actionBar = containingActivity.getActionBar();

        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        actionBar.setListNavigationCallbacks(displayNameList, listener);

        actionBar.setDisplayShowTitleEnabled(false);

        return listener;
    }

 }
