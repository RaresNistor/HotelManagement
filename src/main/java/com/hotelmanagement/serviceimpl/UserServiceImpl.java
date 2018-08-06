package com.hotelmanagement.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hotelmanagement.model.client.Client;
import com.hotelmanagement.model.user.Role;
import com.hotelmanagement.model.user.User;
import com.hotelmanagement.repository.ClientRepository;
import com.hotelmanagement.repository.RoleRespository;
import com.hotelmanagement.repository.UserRepository;
import com.hotelmanagement.service.UserService;

import java.util.ArrayList;
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
	
	 @Autowired
	 private ClientRepository clientRepository;
	 
	 @Override
	 public User findUserByEmail(String email) {
	  return userRepository.findByEmail(email);
	 }
	
	 @Override
	 public void saveUser(User user) {
		 Client client = createClient(user);
		 clientRepository.save(client);
		 user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		 user.setActive(1);
		 Role userRole = roleRespository.findByRole("CLIENT");
		 user.setRoles(new ArrayList<Role>(Arrays.asList(userRole)));
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

	private Client createClient(User user) {
		Client client = new Client();
		client.setEmail(user.getEmail());
		client.setFirstname(user.getFirstname());
		client.setLastname(user.getLastname());
		client.setPassword(user.getPassword());
		return client;
	}
	
	public List<Role> getAllRoles(){
		return roleRespository.findAll();
	}
}