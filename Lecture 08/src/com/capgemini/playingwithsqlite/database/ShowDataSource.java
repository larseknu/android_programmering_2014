package com.capgemini.playingwithsqlite.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.capgemini.playingwithsqlite.database.SQLiteHelper;
import com.capgemini.playingwithsqlite.Show;
import com.capgemini.playingwithsqlite.database.ShowTable;

public class ShowDataSource {
	private SQLiteDatabase database;
	private SQLiteHelper dbHelper;
	private String[] allShowColumns = { ShowTable.COLUMN_ID,
			ShowTable.COLUMN_TITLE,
			ShowTable.COLUMN_YEAR,
			ShowTable.COLUMN_IMDB_ID};
	
	public ShowDataSource(Context context) {
		dbHelper = new SQLiteHelper(context);
	}
	
	public Show createShow(String title, int year, String imdbId) {
		ContentValues values = new ContentValues();
		values.put(ShowTable.COLUMN_TITLE, title);
		values.put(ShowTable.COLUMN_YEAR, year);
		values.put(ShowTable.COLUMN_IMDB_ID, imdbId);

		long insertId = database.insert(ShowTable.TABLE_SHOW, null, values);
		
		return getShow(insertId);
	}
	
	public Show getShow(long id) {
		Cursor cursor = database.query(ShowTable.TABLE_SHOW,
										allShowColumns, 
										ShowTable.COLUMN_ID + " = " + id, 
										null, null, null, null);
		cursor.moveToFirst();
		
		return cursorToShow(cursor);
	}
	
	public List<Show> getAllShows() {
		List<Show> shows = new ArrayList<Show>();
		
		Cursor cursor = database.query(ShowTable.TABLE_SHOW,
										allShowColumns, 
										null, null, null, null, null);
	
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Show show = cursorToShow(cursor);
			shows.add(show);
			cursor.moveToNext();
		}
		// Make sure to close the cursor
		cursor.close();
		
		return shows;
	}
	
	private Show cursorToShow(Cursor cursor) {
		Show show = new Show();
		show.setId(cursor.getInt(0));
		show.setTitle(cursor.getString(1));
		show.setYear(cursor.getInt(2));
		show.setImdbId(cursor.getString(3));
		cursor.close();
		
		return show;
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}
}
