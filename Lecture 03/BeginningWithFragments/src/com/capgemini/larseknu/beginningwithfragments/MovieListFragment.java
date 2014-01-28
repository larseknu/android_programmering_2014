package com.capgemini.larseknu.beginningwithfragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

public class MovieListFragment extends Fragment implements View.OnClickListener {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View fragmentView = inflater.inflate(R.layout.movie_list_fragment, container, false);
		
		setupClickListeners(fragmentView);
		
		return fragmentView;
	}

	private void setupClickListeners(View view)
	{
		setupClickListener(view, R.id.theMatrix);
		setupClickListener(view, R.id.theHobbit);
		setupClickListener(view, R.id.theAvengers);
		setupClickListener(view, R.id.reignOverMe);
		setupClickListener(view, R.id.intoTheWild);
		setupClickListener(view, R.id.wallE);
	}

	private void setupClickListener(View view, int childViewId) {
		View childView = view.findViewById(childViewId);
		childView.setOnClickListener(this);
	}

	private int translateIdToIndex(int id)
	{
		int index = 0;
		switch (id)
		{
			case R.id.theMatrix:
				index = 0;
				break;
			case R.id.theHobbit:
				index = 1;
				break;
			case R.id.theAvengers:
				index = 2;
				break;
			case R.id.reignOverMe:
				index = 3;
				break;
			case R.id.intoTheWild:
				index = 4;
				break;
			case R.id.wallE:
				index = 5;
				break;
		}
		
		return index;
	}
	
	@Override
	public void onClick(View view)
	{
		int id = view.getId();
		RadioButton radioButton = (RadioButton) view;
		String movieTitle = (String) radioButton.getText();
		
		Activity fragmentedActivity = getActivity();
		Toast toast = Toast.makeText(fragmentedActivity, movieTitle, Toast.LENGTH_LONG);
		toast.show();
		
		int index = translateIdToIndex(id);
		
		MovieFragmentCoordinator coordinator = (MovieFragmentCoordinator)fragmentedActivity;
		coordinator.onSelectedMovieChanged(index);
	}
}
