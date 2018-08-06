package com.hotelmanagement.service;

import java.util.List;

import com.hotelmanagement.model.client.Room;

public interface RoomService {
	
	public void saveRoom(Room room);
	
	public void deleteRoom(String id);
	
	public Room findRoomById(String id);
	
	public List<Room> findAllRooms();
		
}
