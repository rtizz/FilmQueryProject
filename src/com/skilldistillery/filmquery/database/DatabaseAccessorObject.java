package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private static final String USER = "student";
	private static final String PASS = "student";
	private boolean hasReturn = false;

	public DatabaseAccessorObject() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Film film = null;
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			String sql = "SELECT film.*, lang.name FROM film JOIN language lang ON lang.id = film.language_id WHERE film.id = ?";
	
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet filmResult = stmt.executeQuery();
			while (filmResult.next()) {
				hasReturn = true;
				String title = filmResult.getString("title");
				String desc = filmResult.getString("description");
				Integer releaseYear = filmResult.getInt("release_year");
				int langId = filmResult.getInt("language_id");
				int rentDur = filmResult.getInt("rental_duration");
				double rate = filmResult.getDouble("rental_rate");
				Integer length = filmResult.getInt("length");
				double repCost = filmResult.getDouble("replacement_cost");
				String rating = filmResult.getString("rating");
				String features = filmResult.getString("special_features");
				String language = filmResult.getString("name");
				List<Actor> actor = findActorsByFilmId(filmId);
				film = new Film(filmId, title, desc, releaseYear, langId, rentDur, rate, length, repCost, rating,
						features, language);
				film.setActor(actor);
			}
			if (!hasReturn) {
				System.out.println("Sorry, there are no results for that search. Please try another keyword.");
			}

			filmResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return film;
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		Actor actor = null;
		Connection conn = DriverManager.getConnection(URL, USER, PASS);
		String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, actorId);
		ResultSet actorResult = stmt.executeQuery();
		if (actorResult.next()) {
			actor = new Actor(actorResult.getInt("id"), actorResult.getString("first_name"),
					actorResult.getString("last_name"));

		}
		actorResult.close();
		stmt.close();
		conn.close();

		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actor = new ArrayList<>();
		Actor actObject;
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			String sql = "SELECT actor.id, actor.first_name, actor.last_name ";
			sql += " FROM actor JOIN film_actor ON actor.id = film_actor.actor_id JOIN film ON film.id = film_actor.film_id ";
			sql += " WHERE film.id = ? ";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet findActor = stmt.executeQuery();
			while (findActor.next()) {
				int id = findActor.getInt("id");
				String firstName = findActor.getString("first_name");
				String lastName = findActor.getString("last_name");
				actObject = new Actor(id, firstName, lastName);
				actor.add(actObject);
			}

			findActor.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return actor;
	}
	
	@Override
	public List<Film> keywordSearch(String keyword) {
		List<Film> kwSearch = new ArrayList<>();
		List<Actor> kwActor;
		Film filmList;

		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			String sql = "SELECT film.id, title, release_year, rating, description, lang.name FROM film ";
					sql += "JOIN language lang ON lang.id = film.language_id ";
					sql += "WHERE title LIKE ? OR description LIKE ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%" + keyword + "%");
			stmt.setString(2, "%" + keyword + "%"); 
			ResultSet searchResult = stmt.executeQuery();
			while (searchResult.next()) {
				hasReturn = true;
				int filmID = searchResult.getInt("id");
				String title = searchResult.getString("title");
				String description = searchResult.getString("description");
				Integer releaseYear = searchResult.getInt("release_year");
				String rating = searchResult.getString("rating");
				String language = searchResult.getString("name");
				filmList = new Film(filmID, title, releaseYear, rating, description, language);
				kwSearch.add(filmList);
				kwActor = findActorsByFilmId(filmList.getFilmId());
				filmList.setActor(kwActor);
			}
			if (!hasReturn) {
				System.out.println("Sorry, there are no results for that search. Please try another keyword.");
			}

			searchResult.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return kwSearch;
	}

}

//public List<Film> findFilmsByActorId(int actorId) {
//	  List<Film> films = new ArrayList<>();
//	  try {
//	    Connection conn = DriverManager.getConnection(URL, USER, PASS);
//	    String sql = "SELECT id, title, description, release_year, language_id, rental_duration, ";
//	                sql += " rental_rate, length, replacement_cost, rating, special_features "
//	               +  " FROM film JOIN film_actor ON film.id = film_actor.film_id "
//	               + " WHERE actor_id = ?";
//	    PreparedStatement stmt = conn.prepareStatement(sql);
//	    stmt.setInt(1, actorId);
//	    ResultSet rs = stmt.executeQuery();
//	    while (rs.next()) {
//	      int filmId = rs.getInt("id");
//	      String title = rs.getString("title");
//	      String desc = rs.getString("description");
//	      Integer releaseYear = rs.getInt("release_year");
//	      int langId = rs.getInt("language_id");
//	      int rentDur = rs.getInt("rental_duration");
//	      double rate = rs.getDouble("rental_rate");
//	      Integer length = rs.getInt("length");
//	      double repCost = rs.getDouble("replacement_cost");
//	      String rating = rs.getString("rating");
//	      String features = rs.getString("special_features");
//	      Film film = new Film(filmId, title, desc, releaseYear, langId,
//	                           rentDur, rate, length, repCost, rating, features);
//	      films.add(film);
//	    }
//	    rs.close();
//	    stmt.close();
//	    conn.close();
//	  } catch (SQLException e) {
//	    e.printStackTrace();
//	  }
//	  return films;
//	}