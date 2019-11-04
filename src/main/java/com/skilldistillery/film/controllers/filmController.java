package com.skilldistillery.film.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.skilldistillery.film.data.DatabaseAccessorObject;
import com.skilldistillery.film.entities.Film;

@Controller
public class filmController {
	@Autowired
	private DatabaseAccessorObject dao;

	public void setDa(DatabaseAccessorObject dao) {
		this.dao = dao;
	}

	// REQUEST MAPPING FOR SEARCH BY ID
	// If film is null it will redirect to a film not found page. Otherwise it will go to search.jsp adding it to "result."
	@RequestMapping(path = "route.do", params = "data", method = RequestMethod.GET)
	public ModelAndView getFilmByName(@RequestParam("data") int id) {
		ModelAndView mv = new ModelAndView();
		Film f = dao.findFilmById(id);
		System.out.println("film that is being displayed" + f);
		if (f == null) {
			mv.setViewName("WEB-INF/filmNotFound.jsp");
		} else {
			mv.addObject("result", f);
			mv.setViewName("WEB-INF/search.jsp");
		}
		return mv;
	}

	// REQUEST MAPPING FOR SEARCH BY KEYWORD
	// String goes into findFilmByKeyword method.  If a list is returned it will go to search.jsp and print all results.
	// Otherwise, it will go to the filmNotFound.jsp page.
	@RequestMapping(path = "search.do", params = "keywords", method = RequestMethod.GET)
	public ModelAndView getFilmBySearch(@RequestParam("keywords") String keyword) {
		ModelAndView mv = new ModelAndView();
		List<Film> k = dao.findFilmByKeyword(keyword);
		if (k.size() != 0) {   // a testing sysout before this line will break the code for some reason (if null)?  Don't add one.
			mv.addObject("results", k);
			mv.setViewName("WEB-INF/search.jsp");
		} else {
			mv.setViewName("WEB-INF/filmNotFound.jsp");
		}
		return mv;
	}
	
	// WHEN A VIDEO IS ADDED THIS WILL ROUTE THE USER TO THE SEARCH PAGE WITH THE INPUT THEY PUT IN.
	// Note that currently actors and category will show nothing on the search.jsp page.
	@RequestMapping(path = "add.do", method = RequestMethod.POST)
	public ModelAndView addFilm(Film film) {
		boolean filmAdd = dao.createFilm(film);
		ModelAndView mv = new ModelAndView();
		if (filmAdd) {
			mv.addObject("result", film);
			mv.setViewName("WEB-INF/search.jsp"); 
		} else {
			mv.setViewName("WEB-INF/updateNotSuccessful.jsp");
		}
		return mv;
	}

	// WHEN VIDEO IS DELETED IT WILL REDIRECT TO A RESPECTIVE SUCCESS PAGE.
	@RequestMapping(path = "delete.do", params = "filmIdDelete", method = RequestMethod.POST)
	public ModelAndView deleteFilm(Film film, int filmIdDelete) {
		boolean filmDelete = dao.deleteFilm(filmIdDelete);
		System.out.println(filmDelete); // test to see what filmDelete is returning. If false should redirect to not successful.
		ModelAndView mv = new ModelAndView();
		if (filmDelete) {
			mv.setViewName("WEB-INF/updateSuccessful.jsp");
//			mv.addObject("result", filmDelete); // not sure if needed now that we are not redirecting back to search.jsp?
		} else {
			mv.setViewName("WEB-INF/updateNotSuccessful.jsp");
		}
		return mv;
	}

	// WHEN UPDATED USER IS REDIRECTED TO HOME PAGE
	// Note:  originally it was directing to search.jsp, but actor and category fields would be blank. when
	// searched for a second time they would reappear.  May be an issue with the DAO or jsp form, but this fixes 
	// the illusion that they were deleted for now.
	@RequestMapping(path = "update.do", method = RequestMethod.POST)
	public ModelAndView updateFilm(Film film) {
		boolean filmUpdate = dao.updateFilm(film);
		ModelAndView mv = new ModelAndView();
		if (filmUpdate) { 
//			mv.addObject("result", filmUpdate);
			mv.setViewName("WEB-INF/updateSuccessful.jsp");											
		} else {
			mv.setViewName("WEB-INF/updateNotSuccessful.jsp"); 
		} 
		return mv;
	}
}