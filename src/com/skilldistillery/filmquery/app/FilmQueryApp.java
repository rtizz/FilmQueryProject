package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();
	private boolean isTrue = true;

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		app.launch();
	}


	private void launch() {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to Film Query!");

		while (isTrue) {
			isTrue = startUserInterface(input);
		}
		input.close();
	}

	
	private boolean startUserInterface(Scanner input) {
		System.out.println("Please select the following options.");
		System.out.println("1. Look up a film by its ID.\n2. Look up a film by keyword search.\n3. Exit Application");
		int userInput = input.nextInt();
		Film film = null;
		switch (userInput) {
		case 1:
			System.out.println("Enter a valid film ID(1-1000) to get its corresponding film.");
			int filmId = input.nextInt();
			if (filmId >= 1 && filmId <= 1000) {
				try {
					film = db.findFilmById(filmId);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				if (film.getDesc() != null) {
					System.out.println("Film: " + film.getTitle() + " | Year: " + film.getReleaseYear() + " | Rating: "
							+ film.getRating() + " | Description: " + film.getDesc() + " | Language: " + film.getLanguage());
					System.out.println("Actors: " + film.getActor());
				} else {
					System.out.println("Sorry, that film ID is not associated to any film in this database. Please try another ID");
				}

			} else {
				System.out.println("Sorry, that film ID is not associated to any film in this database. Please try another ID");
			}
			break;
		case 2:
			System.out.println("Enter a keyword to search through titles and descriptions of films.");
			String keywordInput = input.next();
			input.nextLine();
			List<Film> keywordSearch = null;
			if (keywordInput.equals("")) {			
			System.out.println("Please enter valid search criteria");
			keywordSearch = db.findFilmByKeywordSearch(keywordInput);
			} else if (keywordInput.matches(".*\\d.*")) {
				System.out.println("'" + keywordInput + "' contains numbers, try again");
			} else if (keywordInput != null) {
				keywordSearch = db.findFilmByKeywordSearch(keywordInput);
				int count = 1;
				for (Film search : keywordSearch) {
					System.out.println(count + ". Film: " + search.getTitle() + " | Year: " + search.getReleaseYear() + " | Rating: "
							+ search.getRating() + " | Description: " + search.getDesc() + " | Language: " + search.getLanguage());
					System.out.println("Actors: " + search.getActor());
					count++;
				}
			} 
			
			break;
		case 3:
			System.out.println("Goodbye");
			return isTrue = false;
		default:
		}

		return isTrue;
	}

}
