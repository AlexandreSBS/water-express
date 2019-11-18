package br.com.waterexpress.controller;

import java.util.List;

import br.com.waterexpress.dao.ClientDAO;
import br.com.waterexpress.interfaces.Operacoes;
import br.com.waterexpress.model.Client;

public class ClientController implements Operacoes<Client>{
	
	private ClientController instance;
	private ClientDAO dao;
	
	private ClientController() {
		dao = ClientDAO.getClient();
	}
	
	public ClientController getClientController(){
		if(instance == null)
			instance = new ClientController();
		
		return instance; 
	}

	@Override
	public void insert(Client register) {
		
		dao.insert(register);
	}


	@Override
	public Client getById(int id) {
		
		return dao.getById(id);
	}

	@Override
	public List<Client> listAll() {
		
		return dao.listAll();
	}

	@Override
	public void update(Client register) {
		
		dao.update(register);
	}

	@Override
	public void delete(int id) {
		
		dao.delete(id);
	}

}
