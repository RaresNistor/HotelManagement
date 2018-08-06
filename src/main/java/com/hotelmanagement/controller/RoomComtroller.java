package com.hotelmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hotelmanagement.model.client.Room;
import com.hotelmanagement.model.user.User;
import com.hotelmanagement.service.RoomService;

@Controller
public class RoomComtroller {

	@Autowired
	RoomService roomService;
	
	public RoomComtroller() {
			super();
	}
	
	@RequestMapping(value= {"/room/home"}, method=RequestMethod.GET)
	public ModelAndView home() {
		  ModelAndView model = new ModelAndView();
		  List<Room> allRooms = roomService.findAllRooms();
		  model.addObject("roooms", allRooms);
		  model.setViewName("room/home");
		  return model;
	 }
}
