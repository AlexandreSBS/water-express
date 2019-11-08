package br.com.waterexpress.controller;

import br.com.waterexpress.model.Client;

public class ClientController {
	
	private ClientController instance;
	private Client client = new Client();
	
	private ClientController() {}
	
	public ClientController getClientController(){
		if(instance == null)
			instance = new ClientController();
		
		return instance; 
	}

	public ClientController(Client client) {
		this.setClient(client);
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
