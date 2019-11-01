package com.skilldistillery.film.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.skilldistillery.film.data.DatabaseAccessor;
import com.skilldistillery.film.entities.Film;

@Controller
public class filmController {
	@Autowired
	private DatabaseAccessor da;
	// private DatabaseAccessorObject dao;

	public void setDa(DatabaseAccessor da) {
		this.da = da;
	}

	@RequestMapping(path = "route.do", params = "data", method = RequestMethod.GET)
	public ModelAndView getFilmByName(int id) {
		ModelAndView mv = new ModelAndView();
		Film f = da.findFilmById(id);
		mv.addObject("result", f);
		mv.setViewName("WEB-INF/search.jsp");
		return mv;
	}
}