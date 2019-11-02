package com.skilldistillery.film.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.skilldistillery.film.data.DatabaseAccessor;
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
		mv.addObject("result", f);
		mv.setViewName("WEB-INF/search.jsp");
		return mv;
	}

//	@RequestMapping(path = "add.do", params = "data", method = RequestMethod.GET)
//	public ModelAndView createFilm(@RequestParam("data") Film film) {
//		ModelAndView mv = new ModelAndView();
//		Film f = da.createFilm(film);
//		mv.addObject("result", f);
//		mv.setViewName("WEB-INF/search.jsp");
//		return mv;
//	}

	@RequestMapping(path = "add.do", method = RequestMethod.POST)
	public ModelAndView addFilm(Film film) {
		Film filmAdd = dao.createFilm(film);
		ModelAndView mv = new ModelAndView();
//		mv.setViewName("WEB-INF/addVideo.jsp");
		mv.addObject("result", filmAdd);
		mv.setViewName("WEB-INF/search.jsp"); // redirect to new mapping
		return mv;
	}

	@RequestMapping(path = "delete.do", params = "filmIdDelete", method = RequestMethod.POST)
	public ModelAndView deleteFilm(Film film, int filmIdDelete) {
		System.out.println(film);
		System.out.println(filmIdDelete);
		boolean filmDelete = dao.deleteFilm(filmIdDelete);
		System.out.println(filmDelete);
		ModelAndView mv = new ModelAndView();
//		mv.setViewName("WEB-INF/addVideo.jsp");
//		mv.addObject("result", filmDelete);
		mv.setViewName("index.html"); // redirect to new mapping
		return mv;
	}

	@RequestMapping(path = "getfilmtoupdate.do", method = RequestMethod.GET)
	public ModelAndView getFilmToUpdate(int filmId) {
		Film foundFilm = dao.findFilmById(filmId);
		ModelAndView mv = new ModelAndView();
//		mv.setViewName("WEB-INF/addVideo.jsp");
		mv.addObject("result", foundFilm);
		mv.setViewName("WEB-INF/updateFilm.jsp"); // redirect to new mapping
		return mv;
	}
//
	@RequestMapping(path = "update.do", method = RequestMethod.POST)
	public ModelAndView updateFilm(Film film) {
		System.out.println(film);
		boolean filmUpdate = dao.updateFilm(film);
		ModelAndView mv = new ModelAndView();
//		mv.setViewName("WEB-INF/addVideo.jsp");
//		mv.addObject("result", filmDelete);
		mv.addObject("result", film);
		mv.setViewName("WEB-INF/search.jsp"); // redirect to new mapping
		return mv;
	}

	@RequestMapping(path = "search.do", params = "keywords", method = RequestMethod.GET)
	public ModelAndView getFilmBySearch(@RequestParam("keywords") String keyword) {
		ModelAndView mv = new ModelAndView();
		List<Film> k = dao.findFilmByKeyword(keyword);
		System.out.println("film that is being displayed" + k);
		mv.addObject("results", k);
		mv.setViewName("WEB-INF/search.jsp");
		return mv;
	}
//	@RequestMapping(path = "filmAdded.do", // mapping to handle Redirect
//			method = RequestMethod.GET)
//	public ModelAndView created() {
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("WEB-INF/search.jsp");
//		return mv;
//	}
}