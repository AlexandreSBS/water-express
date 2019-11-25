package br.com.waterexpress.controller;

import java.util.List;

import br.com.waterexpress.dao.ClientDAO;
import br.com.waterexpress.interfaces.OperacoesBase;
import br.com.waterexpress.model.Client;

public class ClientController implements OperacoesBase<Client>{
	
	private static ClientController instance;
	private ClientDAO dao;
	
	private ClientController() {
		
		dao = ClientDAO.getClient();
	}
	
	public static ClientController getClientController(){
		if(instance == null)
			instance = new ClientController();
		
		return instance; 
	}

	public void insert(Client register) {
		
		dao.insert(register);
	}

	public Client getById(int id) {
		
		return dao.getById(id);
	}

	public List<Client> listAll() {
		
		return dao.listAll();
	}

	public void update(Client register) {
		
		dao.update(register);
	}

	public void delete(int id) {
		
		dao.delete(id);
	}
}
