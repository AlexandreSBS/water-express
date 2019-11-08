package br.com.waterexpress.model;

import br.com.waterexpress.enums.Brands;

public class Product {
	private int id;
	private String name;
	private Brands brand;
	private double price;

	public Product() {
	}

	public Product(String name, Brands brand, double price) {
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
	public Brands getBrand() {
		return brand;
	}

	public void setBrand(Brands brand) {
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
