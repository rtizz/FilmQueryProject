package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

public class Film {
	private int filmId;
	private String title;
	private String desc;
	private Integer releaseYear;
	private int langId;
	private int rentDur;
	private double rate;
	private Integer length;
	private double repCost;
	private String rating;
	private String features;
	private String language;
	private List<Actor> actor;
	
	public Film() {}
	
	
	public Film(int filmId, String title, Integer releaseYear, String rating, String desc, String language) {
		this.filmId = filmId;
		this.title = title;
		this.desc = desc;
		this.releaseYear = releaseYear;
		this.rating = rating;
		this.language = language;
	}

	public Film(int filmId, String title, String desc, Integer releaseYear, int langId, int rentDur, double rate,
			Integer length, double repCost, String rating, String features, String language) {
		this.filmId = filmId;
		this.title = title;
		this.desc = desc;
		this.releaseYear = releaseYear;
		this.langId = langId;
		this.rentDur = rentDur;
		this.rate = rate;
		this.length = length;
		this.repCost = repCost;
		this.rating = rating;
		this.features = features;
		this.language = language;
	}


	public int getFilmId() {
		return filmId;
	}


	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public Integer getReleaseYear() {
		return releaseYear;
	}


	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}


	public int getLangId() {
		return langId;
	}


	public void setLangId(int langId) {
		this.langId = langId;
	}


	public int getRentDur() {
		return rentDur;
	}


	public void setRentDur(int rentDur) {
		this.rentDur = rentDur;
	}


	public double getRate() {
		return rate;
	}


	public void setRate(double rate) {
		this.rate = rate;
	}


	public Integer getLength() {
		return length;
	}


	public void setLength(Integer length) {
		this.length = length;
	}


	public double getRepCost() {
		return repCost;
	}


	public void setRepCost(double repCost) {
		this.repCost = repCost;
	}


	public String getRating() {
		return rating;
	}


	public void setRating(String rating) {
		this.rating = rating;
	}


	public String getFeatures() {
		return features;
	}


	public void setFeatures(String features) {
		this.features = features;
	}

	
	


	public List<Actor> getActor() {
		return actor;
	}


	public void setActor(List<Actor> actor) {
		this.actor = actor;
	}





	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return "Film [filmId=" + filmId + ", title=" + title + ", desc=" + desc + ", releaseYear=" + releaseYear
				+ ", langId=" + langId + ", rentDur=" + rentDur + ", rate=" + rate + ", length=" + length + ", repCost="
				+ repCost + ", rating=" + rating + ", features=" + features + ", actor=" + actor + "]";
	}


	@Override
	public int hashCode() {
		return Objects.hash(filmId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return filmId == other.filmId;
	}
	
	
}
