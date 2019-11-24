package br.com.waterespress.test;

import br.com.waterexpress.dao.BrandDAO;
import br.com.waterexpress.dao.ProductDAO;
import br.com.waterexpress.model.Brand;
import br.com.waterexpress.model.Product;
import br.com.waterexpress.view.Sales;

public class MainTest {

	public static void main(String[] args) {

		// Scanner reader = new Scanner(System.in)

		// int id = reader.nextInt();

		BrandDAO bDAO = BrandDAO.getBranDAO();

		ProductDAO dao = ProductDAO.getProductDAO();

		Brand brand = new Brand("Minalba");

		bDAO.insert(brand);

		dao.insert(new Product("Garrafão 20L", brand, 15.31));

		Sales sales = new Sales();

		/*
		 * Brand brand = new Brand("Indaiá");
		 * 
		 * insert(brand);
		 */

	}

}
