package com.capgemini.playinwithfragmenttransactions;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HappyFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceBundle)
	{
		return inflater.inflate(R.layout.fragment_happy, container, false);
	}
}
