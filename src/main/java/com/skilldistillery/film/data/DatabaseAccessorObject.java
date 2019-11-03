package com.skilldistillery.film.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Film;
import com.skilldistillery.film.entities.Inventory;
import com.skilldistillery.film.entities.Store;

public class DatabaseAccessorObject implements DatabaseAccessor {
	// private static final String URL =
	// "jdbc:mysql://localhost:3306/sdvid?useSSL=false";
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&serverTimezone=UTC&useLegacyDatetimeCode=false";
	private String user = "student";
	private String pass = "student";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// STANDARD SQL STRING FOR ALL FILMS
	// Note: Specific where statements are concatenated in their respective methods.
	// added to not repeat and ease to edit in future.
	private String setSql() {
		return "select * FROM film ";
//				+ 
//				"JOIN language ON film.language_id = language.id\n" + // adds language category
//				"JOIN film_category ON film.id = film_category.film_id\n" + // adds film category
//				"JOIN category ON film_category.category_id = category.id\n"; // adds film category
	}

//
	// CREATES FILM OBJECT
	// added to not repeat and ease to edit in future.
	private Film createFilm(ResultSet rs, Film film, int filmId) throws SQLException {
		film = new Film();
		film.setId(rs.getInt("id"));
		film.setTitle(rs.getString("title"));
		film.setDescription(rs.getString("description"));
		film.setReleaseYear(rs.getInt("release_year"));
		film.setLanguageId(rs.getInt("language_id"));
		film.setDescription(rs.getString("description"));
		film.setRentalDuration(rs.getInt("rental_duration"));
		film.setRentalRate(rs.getDouble("rental_rate"));
		film.setLength(rs.getInt("length"));
		film.setReplacementCost(rs.getDouble("replacement_cost"));
		film.setRating(rs.getString("rating"));
		film.setSpecialFeatures(rs.getString("special_features"));
		film.setFilmActors(findActorsByFilmId(filmId));
		film.setCategory(findCategoryById(filmId));
//		film.setActorList(findActorsByFilmId(filmId));

//		film.setLanguage(rs.getString("language.name"));
//		film.setCategory(rs.getString("category.name"));
		return film;
	}

	// FINDS FILM BY ID
	// first menu item in FilmQueryApp.
	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		String sql = setSql() + "WHERE film.id = ?;";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, filmId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				film = createFilm(rs, film, filmId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return film;
	}

	// FINDS FILM BY KEYWORD
	// second menu item in FilmQueryApp.
	public List<Film> findFilmByKeyword(String keyword) {
		Film film = null;
		String sql = setSql() + "WHERE film.title like ? OR film.description like ?;";
		List<Film> films = new ArrayList<>();
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + keyword + "%");
			pst.setString(2, "%" + keyword + "%");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				film = createFilm(rs, film, rs.getInt("id"));
				films.add(film);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return films;
	}

	// FINDS ACTOR
	// currently unused.
	public Actor findActorById(int actorId) {
		Actor actor = null;
		String sql = "SELECT * FROM actor WHERE id = ?;";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, actorId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirst_name(rs.getString("first_name"));
				actor.setLast_name(rs.getString("last_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return actor;
	}

	// CREATES AN ACTOR LIST SPECIFIC TO A FILM.
	// Added in the film object methods above.
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actors = new ArrayList<>();
		String sql = "SELECT * FROM film_actor " + "JOIN film ON film.id = film_actor.film_id "
				+ "JOIN actor on actor.id = film_actor.actor_id" + " WHERE film.id = ?";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Actor actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirst_name(rs.getString("first_name"));
				actor.setLast_name(rs.getString("last_name"));
				actors.add(actor);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actors;
	}

	public String findCategoryById(int filmId) {
		String category = "a";
		String sql = "SELECT category.name FROM film_category\n" + "JOIN film ON film.id = film_category.film_id\n"
				+ "JOIN category ON film_category.category_id = category.id \n" + "WHERE film.id = ?";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				category = rs.getString("category.name");

			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}

	// CREATES AN INVENTORY LIST SPECIFIC TO A FILM.
	// This is used in the sub menu (option 2) in FilmQueryApp. Prints all film
	// locations and conditions.
	public List<Inventory> findInventory(int filmId) {
		List<Inventory> inventory = new ArrayList<>();
		Inventory inv = null;
		String sql = "SELECT * FROM inventory_item " + "JOIN film ON film.id = inventory_item.film_id \n" // adds
																											// inventory.
																											// May need
																											// it's own
																											// method.
				+ "JOIN store_list ON store_list.store_id = inventory_item.store_id\n" + "WHERE film.id = ?";
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				inv = new Inventory(rs.getInt("id"), rs.getInt("film_id"), rs.getInt("store_id"),
						rs.getString("media_condition"), rs.getString("last_update"),
						findStores(rs.getInt("store_id")));
				inventory.add(inv);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return inventory;
	}

	// CREATES A STORE OBJECT TO BE USED WITH INVENTORY TO FIND LOCATION.
	// Added in findInventory method above.
	public Store findStores(int storeId) {
		String sql = "SELECT * FROM store_list WHERE store_list.store_id = ?";
		Store store = null;
		try {
			Connection conn = DriverManager.getConnection(URL, user, pass);
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, storeId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				store = new Store(rs.getInt("store_id"), rs.getInt("manager_id"), rs.getString("address"),
						rs.getString("city"), rs.getString("state"), rs.getString("postal_code"));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return store;
	}

	// FILM QUERY PROJECT CONTINUED - WEEK 2
	// ORM for DML Lab

	public Film createFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);
			String sql = "INSERT INTO film (title, description, release_year, language_id, rental_duration, rental_rate, length, replacement_cost, rating, special_features) "
					+ " VALUES (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getReleaseYear());
			stmt.setInt(4, film.getLanguageId());
			stmt.setInt(5, film.getRentalDuration());
			stmt.setDouble(6, film.getRentalRate());
			stmt.setInt(7, film.getLength());
			stmt.setDouble(8, film.getReplacementCost());
			stmt.setString(9, film.getRating());
			stmt.setString(10, film.getSpecialFeatures());
			int updateCount = stmt.executeUpdate();
			if (updateCount == 1) {
				ResultSet keys = stmt.getGeneratedKeys();
				if (keys.next()) {
					int newFilmId = keys.getInt(1);
					film.setId(newFilmId);
					System.out.println("New film ID: " + newFilmId);
				}
			} else {
				film = null;
			}
			conn.commit(); 
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			throw new RuntimeException("Error inserting actor " + film);
		}
		System.out.println("new film id=" + film.getId() + "*********");
		return film;
	}

	public boolean deleteFilm(int filmid) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false); // START TRANSACTION
			String sql = "DELETE FROM film_actor WHERE film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, filmid);
			int updateCount = stmt.executeUpdate();
			String deletesql = "DELETE FROM film WHERE id = ?";
			stmt = conn.prepareStatement(deletesql);
			stmt.setInt(1, filmid);
			updateCount = stmt.executeUpdate();
			conn.commit();
			System.out.println("****after execute***");
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			return false;
		}
		return true;
	}

	public Film updateFilm(Film film) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			conn.setAutoCommit(false);
			String sql = "UPDATE film SET title = ?, description = ?, release_year = ?, language_id = ?, rental_duration = ?, rental_rate = ?, length = ?, replacement_cost = ?, rating = ?, special_features = ? WHERE id = ?";

			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, film.getTitle());
			stmt.setString(2, film.getDescription());
			stmt.setInt(3, film.getReleaseYear());
			stmt.setInt(4, film.getLanguageId());
			stmt.setInt(5, film.getRentalDuration());
			stmt.setDouble(6, film.getRentalRate());
			stmt.setInt(7, film.getLength());
			stmt.setDouble(8, film.getReplacementCost());
			stmt.setString(9, film.getRating());
			stmt.setString(10, film.getSpecialFeatures());
			stmt.setInt(11, film.getId());
			int updateCount = stmt.executeUpdate();
			
			if (updateCount == 1) {
				conn.commit(); 
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} 
				catch (SQLException sqle2) {
					System.err.println("Error trying to rollback");
				}
			}
			System.out.println(film.getId() + "&&&");
			return film;
		}
		return film;
	}
}