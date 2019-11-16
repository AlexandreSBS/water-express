package br.com.waterexpress.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.waterexpress.exception.SaleException;
import br.com.waterexpress.interfaces.Operacoes;
import br.com.waterexpress.model.Product;

public class ProductController implements Operacoes<Product>{
	
	private static ProductController instance;
	
	
	private List<Product> products = new ArrayList<Product>();

	private ProductController() {
		//popularize();
	}

	public static ProductController getProductController() {
		
		if(instance == null)
			instance = new ProductController();
		
		return instance;
	}


	@Override
	public void insert(Product register) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void update(Product register) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Product getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/*private void popularize() {
		addProduct(new Product("Garrafão de Água Mineral 20L", Brands.Indaiá, 5.95));
		addProduct(new Product("Garrafão de Água Mineral 20L", Brands.santajoana, 4.65));
		addProduct(new Product("Água Mineral 5L", Brands.Indaiá, 2.00));
		addProduct(new Product("Água Mineral 5L", Brands.santajoana, 1.50));
	}*/

}
