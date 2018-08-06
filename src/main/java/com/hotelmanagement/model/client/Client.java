package com.hotelmanagement.model.client;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hotelmanagement.model.user.Role;

@Entity
@Table(name = "client")
public class Client {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Long id;
	 
	 @Column(name = "email")
	 private String email;
	 
	 @Column(name = "firstname")
	 private String firstname; 
	 
	 @Column(name = "lastname")
	 private String lastname;
	 
	 @Column(name = "password")
	 private String password;
	 
	 @Column(name = "active")
	 private int active;
	 
	 
	 @OneToMany(mappedBy = "client", cascade = CascadeType.DETACH)
	 private List<Reservation> reservations;
	
	 
	 
	 public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public Long getId() {
	  return id;
	 }
	
	 public void setId(Long id) {
	  this.id = id;
	 }
	
	 public String getEmail() {
	  return email;
	 }
	
	 public void setEmail(String email) {
	  this.email = email;
	 }
	
	 public String getFirstname() {
	  return firstname;
	 }
	
	 public void setFirstname(String firstname) {
	  this.firstname = firstname;
	 }
	
	 public String getLastname() {
	  return lastname;
	 }
	
	 public void setLastname(String lastname) {
	  this.lastname = lastname;
	 }
	
	 public String getPassword() {
	  return password;
	 }
	
	 public void setPassword(String password) {
	  this.password = password;
	 }
	
	 public int getActive() {
	  return active;
	 }
	
	 public void setActive(int active) {
	  this.active = active;
	 }
	
	 public List<Reservation> getRoles() {
	  return reservations;
	 }
	
	 public void setRoles(List<Reservation> reservations) {
	  this.reservations = reservations;
	 }
}
