package com.hotelmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hotelmanagement.model.user.Role;
import com.hotelmanagement.model.user.User;
import com.hotelmanagement.repository.RoleRespository;
import com.hotelmanagement.repository.UserRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
 
	 @Autowired
	 private UserRepository userRepository;
	 
	 @Autowired
	 private RoleRespository roleRespository;
	 
	 @Autowired
	 private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	 @Override
	 public User findUserByEmail(String email) {
	  return userRepository.findByEmail(email);
	 }
	
	 @Override
	 public void saveUser(User user) {
	  user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	  user.setActive(1);
	  Role userRole = roleRespository.findByRole("ADMIN");
	  user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
	  userRepository.save(user);
	 }

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
	}

}