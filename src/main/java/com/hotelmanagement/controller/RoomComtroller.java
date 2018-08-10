package com.hotelmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hotelmanagement.model.client.Room;
import com.hotelmanagement.service.RoomService;

@Controller
public class RoomComtroller {

	@Autowired
	RoomService roomService;
	
	public RoomComtroller() {
			super();
	}
	
	@RequestMapping(value= {"/room/home/{nr}"}, method=RequestMethod.GET)
	public ModelAndView home(@PathVariable int nr) {
		  ModelAndView model = new ModelAndView();
		  model.addObject("rooms", roomService.findAllRooms(nr));
		  model.addObject("currentPage", nr);
		  model.setViewName("room/home");
		  return model;
	 }
	
	@RequestMapping(value= {"/room/create"}, method=RequestMethod.GET)
	public ModelAndView edit(Room room) {
		ModelAndView model = new ModelAndView();
		model.addObject("room", room);
		model.addObject("types", RoomService.types);
		model.addObject("hideCreate", 1);
		model.setViewName("room/edit");
		return model;
	}
	
	 @RequestMapping(value= {"/room/edit/{nr}"}, method=RequestMethod.GET)
	 public ModelAndView create(@PathVariable String nr) {
		  ModelAndView model = new ModelAndView();
		  Room room = roomService.findRoomById(nr);
		  model.addObject("room", room);
		  model.addObject("types", RoomService.types);
		  model.setViewName("room/edit");
		  model.addObject("hideCreate", 0);
		  return model;
	 }
	
	@RequestMapping(value= {"/room/edit"}, params = "save")
	public ModelAndView create(Room room) {
		roomService.saveRoom(room);
		return  prepareHomePage();
	}
	
	@RequestMapping(value= {"/room/edit"}, params = "edit")
	public ModelAndView update(Room room) {
		Room oldRoom = roomService.findRoomById(room.getId());
		roomService.DeepCopy(oldRoom, room);
		roomService.saveRoom(oldRoom);
		return  prepareHomePage();
	}
	
	@RequestMapping(value= {"/room/edit"}, params = "delete")
	public ModelAndView delete(Room room) {
		roomService.deleteRoom(room.getId());
		return prepareHomePage();
	}
	
	private ModelAndView prepareHomePage() {
		ModelAndView model = new ModelAndView();
		model.addObject("rooms", roomService.findAllRooms(0));
		model.setViewName("room/home");
		model.addObject("currentPage", 0);
		return model;
	}
	
	@GetMapping("/findOne")
	@ResponseBody
	public String findOne( String id) {
		return id;
	}
}
