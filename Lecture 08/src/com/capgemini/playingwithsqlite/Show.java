package com.capgemini.playingwithsqlite;

public class Show {
	private int id;
	private String title;
	private int year;
	private String imdb_id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public long getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public String getImdbId() {
		return imdb_id;
	}

	public void setImdbId(String imdb_id) {
		this.imdb_id = imdb_id;
	}
	
	@Override
	public String toString() {
		return title + " - " + year;
	}
}
