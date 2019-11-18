package br.com.waterexpress.controller;

import java.util.List;

import br.com.waterexpress.dao.BrandDAO;
import br.com.waterexpress.interfaces.Operacoes;
import br.com.waterexpress.model.Brand;

public class BrandController implements Operacoes<Brand>{

	private BrandDAO dao;
	
	public BrandController() {
		dao = BrandDAO.getBranDAO();
	}	
	@Override
	public void insert(Brand register) {
		
		dao.insert(register);
	}

	@Override
	public Brand getById(int id) {
		
		return dao.getById(id);
	}

	@Override
	public List<Brand> listAll() {
		
		return dao.listAll();
	}

	@Override
	public void update(Brand register) {
		
		dao.update(register);
	}

	@Override
	public void delete(int id) {
		
		dao.delete(id);
	}

}
