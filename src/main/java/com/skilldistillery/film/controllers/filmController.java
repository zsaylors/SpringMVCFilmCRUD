package com.skilldistillery.film.controllers;

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
		mv.setViewName("WEB-INF/addVideo.jsp"); // redirect to new mapping
		return mv;
	}

	@RequestMapping(path = "filmAdded.do", // mapping to handle Redirect
			method = RequestMethod.GET)
	public ModelAndView created() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("WEB-INF/search.jsp");
		return mv;
	}
}