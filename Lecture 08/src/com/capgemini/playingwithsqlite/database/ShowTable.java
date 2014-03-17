package com.capgemini.playingwithsqlite.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ShowTable {
	public static final String TABLE_SHOW = "show";
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_TITLE = "name";
	public static final String COLUMN_YEAR = "year";
	public static final String COLUMN_IMDB_ID = "imdb_id";
	
	// Database creation SQL
	private static final String DATABASE_CREATE_SHOW = 
					  "create table " + TABLE_SHOW + "(" + 
					  COLUMN_ID + " integer primary key autoincrement, " + 
					  COLUMN_TITLE + " text not null, " +
					  COLUMN_YEAR + " integer, " +
					  COLUMN_IMDB_ID + " text" + ");";
	
	public static void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE_SHOW);
	}
	
	public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		Log.v(ShowTable.class.getName(), "Upgrading database from " + oldVersion + " to " + newVersion + ". All data are lost.");
		database.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOW);
		onCreate(database);
	}
}
