package com.capgemini.playingwithsqlite.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "show.db";
	private static final int DATABASE_VERSION = 2;
	
	public SQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		ShowTable.onCreate(database);
		EpisodeTable.onCreate(database);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		ShowTable.onUpgrade(database, oldVersion, newVersion);
		EpisodeTable.onUpgrade(database, oldVersion, newVersion);
	}
}
