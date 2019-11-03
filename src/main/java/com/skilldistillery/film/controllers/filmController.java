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

	@RequestMapping(path = "add.do", method = RequestMethod.POST)
	public ModelAndView addFilm(Film film) {
		Film filmAdd = dao.createFilm(film);
		ModelAndView mv = new ModelAndView();
		mv.addObject("result", filmAdd);
		mv.setViewName("WEB-INF/search.jsp"); 
		return mv;
	}

	@RequestMapping(path = "delete.do", params = "filmIdDelete", method = RequestMethod.POST)
	public ModelAndView deleteFilm(Film film, int filmIdDelete) {
		System.out.println(film);
		System.out.println(filmIdDelete);
		boolean filmDelete = dao.deleteFilm(filmIdDelete);
		System.out.println(filmDelete);
		ModelAndView mv = new ModelAndView();
		
		if (filmDelete) {
			mv.setViewName("WEB-INF/updateSuccessful.jsp");
		} else {
			mv.setViewName("WEB-INF/updateNotSuccessful.jsp");
		}
		
		
//		mv.setViewName("WEB-INF/addVideo.jsp");
//		mv.addObject("result", filmDelete);
		 // CHANGEDR RIGHT ERE
		return mv;
	}

//	@RequestMapping(path = "getFilmToUpdate.do", method = RequestMethod.GET)
//	public ModelAndView getFilmToUpdate(int filmId) {
//		Film foundFilm = dao.findFilmById(filmId);
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("result", foundFilm);
//		mv.setViewName("WEB-INF/updateFilm.jsp"); // uhm,what???
//		return mv;
//	}

	@RequestMapping(path = "update.do", method = RequestMethod.POST)
	public ModelAndView updateFilm(Film film) {
		System.out.println("update.do to zero?"+ film);
		Film filmUpdate = dao.updateFilm(film);
		ModelAndView mv = new ModelAndView();
		mv.addObject("result", filmUpdate);
		mv.setViewName("WEB-INF/updateSuccessful.jsp"); 
		return mv;
	}

	@RequestMapping(path = "search.do", params = "keywords", method = RequestMethod.GET)
	public ModelAndView getFilmBySearch(@RequestParam("keywords") String keyword) {
		ModelAndView mv = new ModelAndView();
		List<Film> k = dao.findFilmByKeyword(keyword);
		if (k.size() != 0) {
			mv.addObject("results", k);
			mv.setViewName("WEB-INF/search.jsp");
		} else {
			mv.setViewName("WEB-INF/filmNotFound.jsp");
		}
		return mv;
	}
}