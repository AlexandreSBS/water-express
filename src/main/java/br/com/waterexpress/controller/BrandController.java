package br.com.waterexpress.controller;

import java.util.List;

import br.com.waterexpress.dao.BrandDAO;
import br.com.waterexpress.interfaces.OperacoesBase;
import br.com.waterexpress.model.Brand;

public class BrandController implements OperacoesBase<Brand>{

	private static BrandController instance;
	private BrandDAO dao;
	
	private BrandController() {
		dao = BrandDAO.getBranDAO();
	}
	
	public static BrandController getBrandDAO() {
		
		if(instance == null)
			instance = new BrandController();
		
		return instance;
	}
	
	public void insert(Brand register) {
		
		dao.insert(register);
	}

	public Brand getById(int id) {
		
		return dao.getById(id);
	}

	public List<Brand> listAll() {
		
		return dao.listAll();
	}

	public void update(Brand register) {
		
		dao.update(register);
	}

	public void delete(int id) {
		
		dao.delete(id);
	}

}
