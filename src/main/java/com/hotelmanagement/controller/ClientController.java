package com.hotelmanagement.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hotelmanagement.model.user.User;
import com.hotelmanagement.service.UserService;

@Controller
public class ClientController {

	 @Autowired
	 private UserService userService;
	 
	 @RequestMapping(value= {"/client/home"}, method=RequestMethod.GET)
	 public ModelAndView home() {
		  ModelAndView model = new ModelAndView();
		  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		  User user = userService.findUserByEmail(auth.getName());
		  model.addObject("userName", user);
		  model.setViewName("client/home");
		  return model;
	 }
}
