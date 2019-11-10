package br.com.waterexpress.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ProductO")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	
	@ManyToOne
	Brand brand;
	private double price;

	public Product() {
	}

	public Product(String name, Brand brand, double price) {
		super();
		this.name = name;
		this.brand = brand;
		this.price = price;
	}

	
	
	@Override
	public String toString() {
		return id + " - " + name + " - " + brand + " - R$"+price;
	}

	// brand get/set
	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	// Name's get/set
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Price's get/set
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

}
