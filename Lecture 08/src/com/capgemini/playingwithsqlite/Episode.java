package com.capgemini.playingwithsqlite;

public class Episode {
	private int id;
	private int show_id;
	private int episode;
	private String title;
	private int season;
	private String overview;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShowId() {
		return show_id;
	}

	public int getEpisode() {
		return episode;
	}

	public void setEpisode(int episode) {
		this.episode = episode;
	}
	
	public void setShowId(int show_id) {
		this.show_id = show_id;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}
}
