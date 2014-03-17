package com.capgemini.playingwithsqlite;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.capgemini.playingwithsqlite.database.ShowDataSource;

public class EpisodesActivity extends ListActivity {
	public static String SHOW_ID = "show id";
	public static String SHOW_TITLE = "show title";

	private ShowDataSource datasource;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_episodes);
		
		datasource = new ShowDataSource(this);
		datasource.open();
		
		Intent intent = getIntent();
		int showId = intent.getIntExtra(SHOW_ID, -1);
		
		TextView header = (TextView) findViewById(R.id.header);
		header.setText("Season 1");
		
		setTitle(intent.getStringExtra(SHOW_TITLE));
		
		List<Episode> episodes = datasource.getAllEpisodes(showId);
		
		if (showId != -1) {
			EpisodeListAdapter adapter = new EpisodeListAdapter (this, android.R.layout.simple_list_item_1);
			setListAdapter(adapter);
			
			if(!episodes.isEmpty())
				adapter.addAll(episodes);
		}
		else
			header.setText("No episodes found");
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
}
