package br.com.waterespress.test;

import java.util.ArrayList;
import java.util.List;

import br.com.waterexpress.dao.ClientDAO;
import br.com.waterexpress.dao.SaleDAO;
import br.com.waterexpress.enums.PaymentMethods;
import br.com.waterexpress.model.Client;
import br.com.waterexpress.model.Sale;

public class MainTest {

	public static void main(String[] args) {
		ClientDAO clientDAO = new ClientDAO();
		
		Sale sale = new Sale();
		
		Client client = new Client();
		client.setName("asdad");
		client.setPhoneNumber("2dasdadad");
		client.setAddress("sadasdadad");
		clientDAO.insert(client);
		Client client1 = clientDAO.getById(2);
		
		sale.setClient(client1);
		sale.setPaymentMethods(PaymentMethods.CARTAO);

		
		SaleDAO DAO = SaleDAO.getSaleDAO();
		
		DAO.insert(sale);
	
		
		List<Sale> sales = new ArrayList<Sale>();
		
		sales = DAO.listAll();
		
		
		sales.forEach((c) -> System.out.println(c));
		
		
		
		//sale.setProduct(new Product("Garrafão de Água Mineral 20L", new Brand("Indaiá"), 5.95));	

	}

}
