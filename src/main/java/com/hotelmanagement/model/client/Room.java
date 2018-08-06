package com.hotelmanagement.model.client;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.persistence.Table;

import com.hotelmanagement.model.product.Product;

@Entity
@Table(name = "room")
public class Room {

	@Id
	@Column(name="room_id")
	private String Id;
	
	@NotNull
	@Column(name="price")
	private Double price;
	
	@NotNull
	@Column(name="type")
	private String type;

	@NotNull
	@Column(name="nr_persons")
	private Integer numberOfPersons;
	
	@ManyToMany(cascade=CascadeType.DETACH)
	@JoinTable(name="room_product", joinColumns=@JoinColumn(name="room_id"), inverseJoinColumns=@JoinColumn(name="product_id"))
	private List<Product> products;

	 @OneToMany(mappedBy = "room", cascade = CascadeType.DETACH)
	private List<Reservation> reservations;
	
	public Room() {
		super();
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getNumberOfPersons() {
		return numberOfPersons;
	}

	public void setNumberOfPersons(Integer numberOfPersons) {
		this.numberOfPersons = numberOfPersons;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
