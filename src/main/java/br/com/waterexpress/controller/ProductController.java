package br.com.waterexpress.controller;

import java.util.List;

import br.com.waterexpress.dao.ProductDAO;
import br.com.waterexpress.interfaces.Operacoes;
import br.com.waterexpress.model.Product;

public class ProductController implements Operacoes<Product>{
	
	private static ProductController instance;
	private ProductDAO dao;

	private ProductController() {
		
		dao = ProductDAO.getProductDAO();
	}

	public static ProductController getProductController() {
		
		if(instance == null)
			instance = new ProductController();
		
		return instance;
	}

	public void insert(Product register) {
		
		dao.insert(register);
	}

	public Product getById(int id) {
		
		return dao.getById(id);
	}	
	
	public List<Product> listAll() {
		
		return dao.listAll();
	}
	
	public void update(Product register) {
		
		dao.update(register);
	}

	public void delete(int id) {
		
		dao.delete(id);
	}

	/*private void popularize() {
		addProduct(new Product("Garrafão de Água Mineral 20L", Brands.Indaiá, 5.95));
		addProduct(new Product("Garrafão de Água Mineral 20L", Brands.santajoana, 4.65));
		addProduct(new Product("Água Mineral 5L", Brands.Indaiá, 2.00));
		addProduct(new Product("Água Mineral 5L", Brands.santajoana, 1.50));
	}*/

}
