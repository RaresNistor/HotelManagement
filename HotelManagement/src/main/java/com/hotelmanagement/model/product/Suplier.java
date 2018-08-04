package com.hotelmanagement.model.product;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Table(name = "suplier")
public class Suplier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	 
	@UniqueElements
	@Column(unique=true, name = "name")
	private String name;
	 
	@Column(unique=true, name = "address")
	private String addres;
	 
	@Column(unique=true, name = "phone_number")
	private String phoneNumber;

	@OneToMany(mappedBy = "suplier", cascade = CascadeType.DETACH)
	List<Product> products;
	 
	public Suplier() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddres() {
		return addres;
	}

	public void setAddres(String addres) {
		this.addres = addres;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
