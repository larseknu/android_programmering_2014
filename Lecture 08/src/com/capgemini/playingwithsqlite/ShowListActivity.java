package com.capgemini.playingwithsqlite;

import java.util.List;
import java.util.Random;

import com.capgemini.playingwithsqlite.database.ShowDataSource;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemLongClickListener;

public class ShowListActivity extends ListActivity {
	private ShowDataSource datasource;
	private int showNumber = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		datasource = new ShowDataSource(this);
		datasource.open();
		
		ListView listView = getListView();
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
				deleteShowDialog(position);
		        return true;
            }
		});
		
		List<Show> shows = datasource.getAllShows();

	    ShowListAdapter adapter = new ShowListAdapter(this, android.R.layout.simple_list_item_1);
	    setListAdapter(adapter);
	    adapter.addAll(shows);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Show show = (Show) getListAdapter().getItem(position);
		//Toast.makeText(this, show.getId() + " " + show.getTitle(), Toast.LENGTH_LONG).show();
		
		Intent intent = new Intent(this, EpisodesActivity.class);
		intent.putExtra(EpisodesActivity.SHOW_ID, show.getId());
		intent.putExtra(EpisodesActivity.SHOW_TITLE, show.getTitle());
		startActivity(intent);
	}
	
	public void itemAddOnClick(MenuItem item) {
		CreateShow createShow = new CreateShow();
		createShow.execute();
	}
	
	private class CreateShow extends AsyncTask<Void, Void, Show> {

		@Override
		protected Show doInBackground(Void... params) {
			String[] shows = getResources().getStringArray(R.array.shows);
			
			Show show = datasource.createShow(shows[showNumber++%9], new Random().nextInt(15)+2000, null);
			datasource.createEpisode(show.getId(), "Pilot", 1, 1, "Brilliant physicist roommates Leonard and Sheldon meet their new neighbor Penny, who begins showing them that as much as they know about science, they know little about actual living.");
			datasource.createEpisode(show.getId(), "The Big Bran Hypothesis", 2, 1, "Brilliant physicist roommates Leonard and Sheldon meet their new neighbor Penny, who begins showing them that as much as they know about science, they know little about actual living.");
			
			return show;
		}

		@Override
		protected void onPostExecute(Show show) {
			@SuppressWarnings("unchecked")
			ArrayAdapter<Show> adapter = (ArrayAdapter<Show>) getListAdapter();
			
			adapter.add(show);
		}
	}
	
	public void itemDeleteOnClick(MenuItem item) {
		@SuppressWarnings("unchecked")
		ArrayAdapter<Show> adapter = (ArrayAdapter<Show>) getListAdapter();
		
		while (adapter.getCount() > 0) {
			Show show = adapter.getItem(0);
			datasource.deleteShow(show);
			adapter.remove(show);
		}
	}
	
	private void deleteShowDialog(final int position) {
		new AlertDialog.Builder(this)
	        .setTitle("Delete entry?")
	        .setMessage("Are you sure you want to delete this entry?")
	        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) { 
	            	ShowListAdapter adapter = (ShowListAdapter) getListAdapter();
					Show show = (Show) adapter.getItem(position);
					datasource.deleteShow(show);
			        adapter.remove(show);
	            }
	         })
	        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
	            public void onClick(DialogInterface dialog, int which) { 
	                // Do nothing
	            }
	         })
	        .setIcon(android.R.drawable.ic_dialog_alert)
	         .show();
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
