package com.hotelmanagement.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import com.hotelmanagement.model.client.Room;

public interface RoomService {
	public static final ArrayList<String> types = new ArrayList<String>() {
		private static final long serialVersionUID = 1L;
	{
	    add("SINGLE");
	    add("DOUBLE");
	    add("TRIPLE");
	}};
	
	public void DeepCopy(Room oldRoom, Room newRoom);
	
	public void saveRoom(Room room);
	
	public void deleteRoom(String id);
	
	public Room findRoomById(String id);
	
	public Page<Room> findAllRooms(int page);
		
}
