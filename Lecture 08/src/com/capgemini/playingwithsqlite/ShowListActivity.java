package com.capgemini.playingwithsqlite;

import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import com.capgemini.playingwithsqlite.database.ShowDataSource;

public class ShowListActivity extends ListActivity {
	private ShowDataSource datasource;
	private int showNumber = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		datasource = new ShowDataSource(this);
		datasource.open();
		
		List<Show> shows = datasource.getAllShows();

	    ShowListAdapter adapter = new ShowListAdapter(this, android.R.layout.simple_list_item_1);
	    setListAdapter(adapter);
	    adapter.addAll(shows);
	}
	
	public void itemAddOnClick(MenuItem item) {
		String[] shows =  getResources().getStringArray(R.array.shows);
		Show show = datasource.createShow(shows[showNumber++ % 9], new Random().nextInt(15)+2000, null);
		
		@SuppressWarnings("unchecked")
		ArrayAdapter<Show> adapter = (ArrayAdapter<Show>) getListAdapter();
		adapter.add(show);
	}
	
	@Override
  	protected void onResume() {
		datasource.open();
    	super.onResume();
	}

	@Override
	protected void onPause() {
		datasource.close();
		super.onPause();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
