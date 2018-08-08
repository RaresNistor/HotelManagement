package com.hotelmanagement.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hotelmanagement.model.user.Role;
import com.hotelmanagement.model.user.User;
import com.hotelmanagement.service.UserService;


@Controller
public class UserController {
	
		 @Autowired
		 private UserService userService;
		 
		 @Autowired
		 public UserController() {
			super();
		}

		@RequestMapping(value= {"/", "/login"}, method=RequestMethod.GET)
		 public ModelAndView loginGet() {
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
			  model.addObject("userName", user);
			  List<User> users = userService.findAllUsers();
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
	 
		 @RequestMapping(value= {"/create"}, method=RequestMethod.GET)
		 public ModelAndView create() {
			 ModelAndView model = new ModelAndView();
			  User user = new User();
			  user.setPassword(null);
			  user.setEmail(null);
			  user.setRrole("CLIENT");
			  model.addObject("user", user);
			  model.addObject("allRoles",userService.getAllRoles());
			  model.setViewName("user/edit");
			  return model;
		 }
		 
		 @RequestMapping(value= {"/edit/{email}"}, method=RequestMethod.GET)
		 public ModelAndView create(@PathVariable String email) {
			  ModelAndView model = new ModelAndView();
			  User user = userService.findUserByEmail(email);
			  user.setRrole(user.getRoles().get(0).getRole());
			  model.addObject("user", user);
			  model.addObject("allRoles",userService.getAllRoles());
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
			  User oldUser = userService.findUserByEmail(user.getEmail());
			  userService.deepCopy(oldUser, user);
		      userService.updateUser(oldUser);
		      ModelAndView model = new ModelAndView("redirect:/home/home");
			  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			  User user1 = userService.findUserByEmail(auth.getName());
			  List<User> users= userService.findAllUsers();
			  model.addObject("userName", user1);
			  model.addObject("users", users);
			  model.setViewName("home/home");
			  return model;
		}
		
		@RequestMapping(value="/edit" , params = "save")
		public ModelAndView save(@Valid User user){
		      userService.deepCopy(user,user);
		      userService.saveUser(user);
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