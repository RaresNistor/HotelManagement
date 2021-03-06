package com.hotelmanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hotelmanagement.model.user.User;
import com.hotelmanagement.service.UserService;


@Controller
public class UserController {
	
		 @Autowired
		 private UserService userService;
		 
		 @Autowired
		 private BCryptPasswordEncoder bCryptPasswordEncoder;
		 
		 public UserController() {
			super();
		}

		@RequestMapping(value= {"/", "/login"}, method=RequestMethod.GET)
		 public ModelAndView login() {
		  ModelAndView model = new ModelAndView();
		  model.setViewName("user/login");
		  return model;
		 }
		 
		 @RequestMapping(value= {"/signup"}, method=RequestMethod.GET)
		 public ModelAndView signup() {
			  ModelAndView model = new ModelAndView();
			  User user = new User();
			  model.addObject("user", user);
			  model.setViewName("user/signup");
			  
			  return model;
		 }
		 
		 @RequestMapping(value= {"/signup"}, method=RequestMethod.POST)
		 public ModelAndView createUser(@Valid User user, BindingResult bindingResult) {
			  ModelAndView model = new ModelAndView();
			  User userExists = userService.findUserByEmail(user.getEmail());
			  
			  if(userExists != null) {
				  bindingResult.rejectValue("email", "error.user", "This email already exists!");
			  }
			  if(bindingResult.hasErrors()) {
				  model.setViewName("user/signup");
			  } else {
				   userService.saveUser(user);
				   model.addObject("msg", "User has been registered successfully!");
				   model.addObject("user", new User());
				   model.setViewName("user/signup");
			  }
			 return model;
		 }
		 
		 @RequestMapping(value= {"/home/home"}, method=RequestMethod.GET)
		 public ModelAndView home() {
			  ModelAndView model = new ModelAndView();
			  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			  User user = userService.findUserByEmail(auth.getName());
			  List<User> users = userService.findAllUsers();
			  model.addObject("userName", user);
			  model.addObject("users", users);
			  model.setViewName("home/home");
			  return model;
		 }
		 
		 @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
		 public ModelAndView accessDenied() {
			  ModelAndView model = new ModelAndView();
			  model.setViewName("errors/access_denied");
			  return model;
		 }
	 
		 @RequestMapping(value= {"/edit/{email}"}, method=RequestMethod.GET)
		 public ModelAndView signup(@PathVariable String email) {
			  ModelAndView model = new ModelAndView();
			  User user = userService.findUserByEmail(email);
			  model.addObject("user", user);
			  model.setViewName("user/edit");
			  
			  return model;
		 }
		 
		@RequestMapping(value="/edit" , params = "delete")
		public ModelAndView delete( User user){
			 userService.deleteUser(userService.findUserByEmail(user.getEmail()).getId());
		      ModelAndView model = new ModelAndView("redirect:/home/home");
			  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			  User user1 = userService.findUserByEmail(auth.getName());
			  List<User> users = userService.findAllUsers();
			  model.addObject("userName", user1);
			  model.addObject("users", users);
			  model.setViewName("home/home");
			  return model;
		}
	 
		@RequestMapping(value="/edit" , params = "edit")
		public ModelAndView update(@Valid User user){
		      userService.updateUser(user);
		      ModelAndView model = new ModelAndView("redirect:/home/home");
			  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			  User user1 = userService.findUserByEmail(auth.getName());
			  List<User> users= userService.findAllUsers();
			  model.addObject("userName", user1);
			  model.addObject("users", users);
			  model.setViewName("home/home");
			  return model;
		}
}