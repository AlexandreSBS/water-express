package br.com.waterexpress.controller;

import java.util.List;

import br.com.waterexpress.dao.ItemDAO;
import br.com.waterexpress.interfaces.Operacoes;
import br.com.waterexpress.model.Item;

public class ItemController implements Operacoes<Item> {

	private static ItemController instance;
	private ItemDAO dao;

	private ItemController() {
		
		dao = ItemDAO.getItemDAO();
	}
	
	public static ItemController getItemController() {
		
		if(instance == null)
			instance = new ItemController();
		
		return instance;
	}
	
	

	@Override
	public void insert(Item register) {
		dao.insert(register);
	}

	@Override
	public Item getById(int id) {
		return dao.getById(id);
	}

	@Override
	public List<Item> listAll() {
		return dao.listAll();
	}

	@Override
	public void update(Item register) {
		dao.update(register);
	}

	@Override
	public void delete(int id) {
		dao.delete(id);
	}

}
