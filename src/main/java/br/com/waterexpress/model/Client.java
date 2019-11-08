package br.com.waterexpress.model;

public class Client {
	private String name;
	private String address;
	private String phoneNumber;

	public Client() {
		super();
	}

	public Client(String name, String address, String phoneNumber) {
		super();
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	// Name's get/set
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name + " - " + address + " - " + phoneNumber;
	}

	// Address's get/set
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	// PhoneNumber's get/set
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
