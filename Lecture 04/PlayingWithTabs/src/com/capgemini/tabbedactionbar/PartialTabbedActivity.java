package com.capgemini.tabbedactionbar;

import android.app.Activity;
import android.os.Bundle;

public class PartialTabbedActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_partial_tabbed);
        
        SimpleTabListener.SetupTabbedNavigation(this, R.array.tabName, R.array.tabFragmentNames, R.id.selectableGroup);
    }
}