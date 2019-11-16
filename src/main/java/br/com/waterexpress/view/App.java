package br.com.waterexpress.view;

import java.awt.FontFormatException;
import java.util.ArrayList;
import java.util.List;

import br.com.waterexpress.dao.ClientDAO;
import br.com.waterexpress.model.Client;

public class App {
	
	public static void main(String[] args) throws FontFormatException, Exception {

		Client client = new Client();
		client.setName("teste");
		client.setPhoneNumber("(11) 1111-1111");
		client.setAddress("rua das batatas");
		ClientDAO DAO = new ClientDAO();
		DAO.insert(client);
		
		List<Client> clients = new ArrayList<Client>();
		
		clients = DAO.listAll();
		

		
		clients.forEach((c) -> System.out.println(c));
		

		
		Sales sales = new Sales();
	}

}
