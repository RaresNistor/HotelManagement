package com.hotelmanagement.service;

import java.util.List;

import com.hotelmanagement.model.user.User;

public interface UserService {
	  
	 public User findUserByEmail(String email);
	 
	 public void saveUser(User user);
	 
	 public List<User> findAllUsers();
	 
	 public void deleteUser(Long id);
	 
	 public void updateUser(User user);
	 
}
