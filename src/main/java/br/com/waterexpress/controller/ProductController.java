package br.com.waterexpress.controller;

import java.util.List;

import br.com.waterexpress.dao.ProductDAO;
import br.com.waterexpress.interfaces.OperacoesBase;
import br.com.waterexpress.model.Product;

public class ProductController implements OperacoesBase<Product>{
	
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
	
	public List<Product> listAll() throws Exception {
		
		List<Product> allProducts = dao.listAll();
		
		if(!allProducts.isEmpty()) {
		
			return allProducts;
		}
		else {
			throw new Exception("Não existem produtos registrados");
		}
	}
	
	public void update(Product register) {
		
		dao.update(register);
	}

	public void delete(int id) {
		
		dao.delete(id);
	}


}
