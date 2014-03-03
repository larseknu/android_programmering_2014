package com.capgemini.playingwiththreads;

import android.location.Location;
import android.os.AsyncTask;
import android.widget.TextView;

public class AsyncTaskWorker extends AsyncTask<TextView, String, Boolean> {
	TextView _textView;
	
	@Override
	protected Boolean doInBackground(TextView... textViews) {
		Boolean returnValue = false;
		
		if(textViews.length > 0) 
		{
			_textView = textViews[0];
		
			Worker worker = new Worker(_textView.getContext());
			publishProgress("Starting");
			
			Location location = worker.getLocation();
			publishProgress("Retrieved Location");
			
			String address = worker.reverseGeocode(location);
			publishProgress("Retrieved Address");
			
			worker.save(location, address, "FancyFileName.out");
			publishProgress("Done");
			
			returnValue = true;
		}
		
		return returnValue;
	}

	@Override
	protected void onProgressUpdate(String... values) {
		_textView.setText(values[0]);
	}
	
	@Override
	protected void onPostExecute(Boolean result) {
		if(result.booleanValue())
			_textView.setText("Finished");
		else
			_textView.setText("Error");
	}
}
