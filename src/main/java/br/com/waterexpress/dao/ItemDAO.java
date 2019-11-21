package br.com.waterexpress.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.waterexpress.interfaces.Operacoes;
import br.com.waterexpress.model.Item;

public class ItemDAO implements Operacoes<Item> {

	private SessionFactory sessionFactory;
	private static ItemDAO instance;

	private ItemDAO() {
		
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}
	
	public static ItemDAO getItemDAO() {
		if(instance == null)
			instance = new ItemDAO();
		
		return instance;
	}
	
	@Override
	public void insert(Item register) {
		
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		session.save(register);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Item getById(int id) {
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();		
		
		Item item = session.get(Item.class, id);	
		
		session.getTransaction().commit();
		session.close();
		
		return item;
	}

	@Override
	public List<Item> listAll() {
		
		List<Item> result = new ArrayList<Item>();
		Session session = sessionFactory.openSession();
		
		result = session.createQuery("from Item").list();
		
		session.close();
		
		return result;
	}

	@Override
	public void update(Item register) {
		
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
		
		Item item = session.get(Item.class, id);
		
		session.delete(item);
		session.getTransaction().commit();
		session.close();

	}

}
