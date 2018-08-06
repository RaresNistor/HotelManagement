package com.hotelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hotelmanagement.model.client.Room;

public interface RoomRepository extends JpaRepository<Room, String>{

}
