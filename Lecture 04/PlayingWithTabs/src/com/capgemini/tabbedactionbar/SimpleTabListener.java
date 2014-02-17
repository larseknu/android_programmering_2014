package com.capgemini.tabbedactionbar;

import android.app.Fragment;
import android.content.Context;

public class SimpleTabListener {
    Context _context;
    String _tabFragmentClassName;
    Fragment _tabFragment = null;

    public SimpleTabListener(Context context, String tabFragmentClassName) {
        _context = context;
        _tabFragmentClassName = tabFragmentClassName;
     }

//    public static void SetupTabbedNavigation(Activity containingActivity,
//                                                               int displayNameResourceId,
//                                                               int fragmentClassNameResourceId) {
//        // Load the display values and class names from the resources
//        String[] displayNameList = containingActivity.getResources().getStringArray(displayNameResourceId);
//        String[] fragmentList = containingActivity.getResources().getStringArray(fragmentClassNameResourceId);
//
//        if(fragmentList.length != displayNameList.length) {
//            Log.e("SetupTabbedNavigation",
//                    "ERROR - List of fragment class names and list of display names do not contain the same number of entries");
//            return ;
//        }
//
//    }

}
