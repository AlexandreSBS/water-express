package br.com.waterexpress.controller;

import java.util.List;

import br.com.waterexpress.interfaces.Operacoes;
import br.com.waterexpress.model.Client;

public class ClientController implements Operacoes<Client>{
	
	private ClientController instance;
	
	private ClientController() {}
	
	public ClientController getClientController(){
		if(instance == null)
			instance = new ClientController();
		
		return instance; 
	}

	@Override
	public void insert(Client register) {
		
		
	}


	@Override
	public void update(Client register) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Client getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Client> listAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
