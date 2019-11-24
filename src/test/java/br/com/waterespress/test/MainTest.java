package br.com.waterespress.test;

import java.util.ArrayList;
import java.util.List;

import br.com.waterexpress.dao.BrandDAO;
import br.com.waterexpress.dao.ProductDAO;
import br.com.waterexpress.model.Product;
import br.com.waterexpress.view.Sales;

public class MainTest {

	public static void main(String[] args) {

		
		 /*BrandDAO brandDAO = BrandDAO.getBranDAO();
		 
		 List<Brand> brands = new ArrayList<Brand>();
		 
		 brands.add(new Brand("Indaiá")); brands.add(new Brand("Santa Joana"));
		 brands.add(new Brand("Cristal"));
		 
		 for (Brand brand : brands) { brandDAO.insert(brand); }
		 */

		
		  /*BrandDAO brandDAO = BrandDAO.getBranDAO(); ProductDAO productDAO =
		  ProductDAO.getProductDAO(); List<Product> products = new
		  ArrayList<Product>();
		  
		  products.add(new Product("Garrafão 20L", brandDAO.getById(1), 7.49));
		  products.add(new Product("Garrafão 20L", brandDAO.getById(2), 5));
		  products.add(new Product("Garrafão 20L", brandDAO.getById(3), 5.99));
		  products.add(new Product("Galão 5L", brandDAO.getById(1), 5.49));
		  products.add(new Product("Galão 5L", brandDAO.getById(3), 5.2));
		  
		  for (Product product : products) { productDAO.insert(product); }
		 */

		 Sales sales = new Sales();

	}

}
