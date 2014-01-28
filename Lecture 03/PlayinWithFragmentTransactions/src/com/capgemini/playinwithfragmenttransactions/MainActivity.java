package com.capgemini.playinwithfragmenttransactions;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	private String _dynamicFragments = "DynamicFragment";
 	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void onClickBtnAdd(View view) {
		FragmentManager fragmentManager = getFragmentManager();
		HappyFragment happyFragment = new HappyFragment();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		
		fragmentTransaction.add(R.id.group1, happyFragment, _dynamicFragments);
		fragmentTransaction.addToBackStack("Add");
		fragmentTransaction.commit();
	}
	
	public void onClickBtnRemove(View view) {
		FragmentManager fragmentManager = getFragmentManager();
		SadFragment sadFragment = new SadFragment();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		Fragment oldFragment = fragmentManager.findFragmentByTag(_dynamicFragments);
		
		if (oldFragment != null)
			fragmentTransaction.remove(oldFragment);
		fragmentTransaction.add(R.id.group1, sadFragment, _dynamicFragments);
		
		fragmentTransaction.addToBackStack("Remove");
		fragmentTransaction.commit();
	}
	
	public void onClickBtnReplace(View view) {
		FragmentManager fragmentManager = getFragmentManager();
		HappyFragment happyFragment = new HappyFragment();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		
		fragmentTransaction.replace(R.id.group1, happyFragment, _dynamicFragments);
		fragmentTransaction.addToBackStack("Replace");
		fragmentTransaction.commit();
	}
	
	public void onClickBtnDetach(View view) {
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		
		fragmentTransaction.detach(fragmentManager.findFragmentByTag(_dynamicFragments));
		fragmentTransaction.addToBackStack("Detach");
		fragmentTransaction.commit();
	}
	
	public void onClickBtnAttach(View view) {
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		
		fragmentTransaction.attach(fragmentManager.findFragmentByTag(_dynamicFragments));
		fragmentTransaction.addToBackStack("Attach");
		fragmentTransaction.commit();
	}
	
	public void onClickBtnPrevious(View view) {
		FragmentManager fm = getFragmentManager();
        fm.popBackStack();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
