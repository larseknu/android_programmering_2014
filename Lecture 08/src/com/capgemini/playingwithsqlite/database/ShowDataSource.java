package com.capgemini.playingwithsqlite.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.capgemini.playingwithsqlite.database.SQLiteHelper;

public class ShowDataSource {
	// Database fields
	private SQLiteDatabase database;
	private SQLiteHelper dbHelper;
	
	public ShowDataSource(Context context) {
		dbHelper = new SQLiteHelper(context);
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		dbHelper.close();
	}
}
