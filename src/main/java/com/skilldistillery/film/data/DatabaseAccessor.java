package com.skilldistillery.film.data;
 
import java.util.List;

import com.skilldistillery.film.entities.Film;
import com.skilldistillery.film.entities.Actor;
import com.skilldistillery.film.entities.Inventory;
import com.skilldistillery.film.entities.Store;

public interface DatabaseAccessor {
	  public Film findFilmById(int filmId);
	  public Actor findActorById(int actorId);
	  public List<Actor> findActorsByFilmId(int filmId);
	  public List<Film> findFilmByKeyword(String keyword);
	  public List<Inventory> findInventory(int filmId);
	  public Store findStores(int storeId);
	  public boolean deleteFilm(int filmid);
	  public Film createFilm(Film film);
	  public Film updateFilm(Film film);
}
