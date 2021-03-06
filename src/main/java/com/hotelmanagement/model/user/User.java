package com.hotelmanagement.model.user;
import java.util.ArrayList;
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
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "user")
public class User {
 
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
	 
	 @Transient
	 private String rrole;
	 
	 @ManyToMany(cascade=CascadeType.DETACH)
	 @JoinTable(name="user_role", joinColumns=@JoinColumn(name="user_id"), inverseJoinColumns=@JoinColumn(name="role_id"))
	 private List<Role> roles;
	
	 public String getRrole() {
		return rrole;
	}

	public void setRrole(String rrole) {
		this.rrole = rrole;
	}

	public User() {
		super();
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
	
	 public List<Role> getRoles() {
	  return roles;
	 }
	
	 public void setRoles(List<Role> roles) {
	  this.roles = roles;
	 }
}