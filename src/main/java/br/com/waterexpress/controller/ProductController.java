package br.com.waterexpress.controller;

import java.util.ArrayList;
import java.util.List;

import br.com.waterexpress.enums.Brands;
import br.com.waterexpress.exception.SaleException;
import br.com.waterexpress.model.Product;

public class ProductController {
	
	private static ProductController instance;
	
	private int id = 1;
	private List<Product> products = new ArrayList<Product>();

	private ProductController() {
		popularize();
	}

	public static ProductController getProductController() {
		
		if(instance == null)
			instance = new ProductController();
		
		return instance;
	}
	
	public void addProduct(Product product) {
		
		product.setId(id);
		
		products.add(product);
		
		id++;
	}

	public void getProducts() {
		
		for (Product product : products) {
			
			System.out.println(product); //	TODO CONTINUOU O ERRO, MUITO BEM!!!
		}
	}

	public Product getProduct(int id) throws SaleException {
		
		Product prod = null;
		
		for (Product product : products) {
			
			if (product.getId() == id) {
				
				prod = product;
			}
		}
		if (prod != null) {
			
			return prod;
		} else {
			
			throw new SaleException("ID inválido!");
		}
	}

	private void popularize() {
		addProduct(new Product("Garrafão de Água Mineral 20L", Brands.Indaiá, 5.95));
		addProduct(new Product("Garrafão de Água Mineral 20L", Brands.santajoana, 4.65));
		addProduct(new Product("Água Mineral 5L", Brands.Indaiá, 2.00));
		addProduct(new Product("Água Mineral 5L", Brands.santajoana, 1.50));
	}

}
