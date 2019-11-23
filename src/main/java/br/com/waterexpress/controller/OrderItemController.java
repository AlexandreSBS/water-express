package br.com.waterexpress.controller;

import java.util.List;

import br.com.waterexpress.dao.OderItemDAO;
import br.com.waterexpress.interfaces.Operacoes;
import br.com.waterexpress.model.OrderItem;

public class OrderItemController implements Operacoes<OrderItem> {

	private static OrderItemController instance;
	private OderItemDAO dao;

	private OrderItemController() {
		
		dao = OderItemDAO.getItemDAO();
	}
	
	public static OrderItemController getItemController() {
		
		if(instance == null)
			instance = new OrderItemController();
		
		return instance;
	}

	@Override
	public void insert(OrderItem register) {
		dao.insert(register);
	}

	@Override
	public OrderItem getById(int id) {
		return dao.getById(id);
	}

	@Override
	public List<OrderItem> listAll() {
		return dao.listAll();
	}

	@Override
	public void update(OrderItem register) {
		dao.update(register);
	}

	@Override
	public void delete(int id) {
		dao.delete(id);
	}

}
