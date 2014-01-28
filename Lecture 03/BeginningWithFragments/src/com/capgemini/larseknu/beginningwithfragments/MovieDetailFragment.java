package com.capgemini.larseknu.beginningwithfragments;

import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MovieDetailFragment extends Fragment {
	final String _movieDescriptionIndexStateKey = "movieDescriptionIndex";
	final int _movieDescriptionIndexDefaultValue = 0;
	
	TextView _movieDescriptionView;
	String[] _movieDescriptions;
	int _movieDescriptionIndex;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View fragmentView = inflater.inflate(R.layout.movie_detail_fragment, container, false);
		_movieDescriptionView = (TextView) fragmentView.findViewById(R.id.movieDetail);
		_movieDescriptions = getResources().getStringArray(R.array.movie_descriptions);
		
		_movieDescriptionIndex = savedInstanceState == null? _movieDescriptionIndexDefaultValue : 
									savedInstanceState.getInt(_movieDescriptionIndexStateKey, _movieDescriptionIndexDefaultValue);
	
		setDesplayedDetail(_movieDescriptionIndex);
		return fragmentView;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putInt(_movieDescriptionIndexStateKey, _movieDescriptionIndex);
	}
	
	public void setDesplayedDetail(int movieDescriptionIndex)
	{
		_movieDescriptionIndex = movieDescriptionIndex;
		_movieDescriptionView.setText(_movieDescriptions[_movieDescriptionIndex]);
	}
}
