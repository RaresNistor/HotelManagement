package com.hotelmanagement.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hotelmanagement.model.client.Room;
import com.hotelmanagement.repository.RoomRepository;
import com.hotelmanagement.service.RoomService;

@Service("roomService")
public class RoomServiceImpl implements RoomService{

	@Autowired
	private RoomRepository roomRepository;
	
	@Override
	public void saveRoom(Room room) {
		roomRepository.save(room);
	}

	@Override
	public void deleteRoom(String id) {
		roomRepository.deleteById(id);
	}

	@Override
	public Room findRoomById(String id) {
		return roomRepository.findById(id).get();
	}

	@Override
	public Page<Room> findAllRooms(int page) {
		return  roomRepository.findAll(PageRequest.of(page, 2));
	}

	@Override
	public void DeepCopy(Room oldRoom, Room newRoom) {
		oldRoom.setNumberOfPersons(newRoom.getNumberOfPersons());
		oldRoom.setPrice(newRoom.getPrice());
		oldRoom.setType(newRoom.getType());
	}

}
