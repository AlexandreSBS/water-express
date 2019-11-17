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
		ClientDAO clientDAO = ClientDAO.getClient();
		SaleDAO DAO = SaleDAO.getSaleDAO();
		Sale sale = new Sale();
		
		Client client = new Client();
		client.setName("dsfsdf");
		client.setPhoneNumber("2dasdadad");
		client.setAddress("sadasdadad");
		clientDAO.insert(client);
		Client client1 = clientDAO.getById(2);
		
		sale.setClient(client1);
		sale.setPaymentMethods(PaymentMethods.CARTAO);

		
		
		
		DAO.insert(sale);
	
		
		List<Sale> sales = new ArrayList<Sale>();
		
		sales = DAO.listAll();
		
		
		sales.forEach((c) -> System.out.println(c));
		

	
		
		sale = DAO.getById(2);
		
		
		System.out.println(sale);
		
		
		
		sale.setProduct(new Product("Garraf�o de �gua Mineral 20L", new Brand("Indai�"), 5.95));	

	}

}
