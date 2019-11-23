package br.com.waterexpress.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.waterexpress.interfaces.Operacoes;
import br.com.waterexpress.model.OrderItem;

public class OderItemDAO implements Operacoes<OrderItem> {

	private SessionFactory sessionFactory;
	private static OderItemDAO instance;

	private OderItemDAO() {
		
		sessionFactory = FactorySession.getFactorySession().getSessionFactory();
	}
	
	public static OderItemDAO getItemDAO() {
		if(instance == null)
			instance = new OderItemDAO();
		
		return instance;
	}
	
	@Override
	public void insert(OrderItem register) {
		
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		session.save(register);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public OrderItem getById(int id) {
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();		
		
		OrderItem item = session.get(OrderItem.class, id);	
		
		session.getTransaction().commit();
		session.close();
		
		return item;
	}

	@Override
	public List<OrderItem> listAll() {
		
		List<OrderItem> result = new ArrayList<OrderItem>();
		Session session = sessionFactory.openSession();
		
		result = session.createQuery("from Item").list();
		
		session.close();
		
		return result;
	}

	@Override
	public void update(OrderItem register) {
		
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		session.saveOrUpdate(register);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public void delete(int id) {
		
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		
		OrderItem item = session.get(OrderItem.class, id);
		
		session.delete(item);
		session.getTransaction().commit();
		session.close();

	}

}
