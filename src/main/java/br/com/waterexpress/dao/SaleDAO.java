package br.com.waterexpress.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import br.com.waterexpress.interfaces.Operacoes;
import br.com.waterexpress.model.Sale;


public class SaleDAO implements Operacoes<Sale> {


	private List<Sale> product = new ArrayList<Sale>();
	private SessionFactory sessionFactory;
	private static SaleDAO instance;

	private SaleDAO() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	public static SaleDAO getSaleDAO() {
		if(instance == null)
			instance = new SaleDAO();
		
		return instance;
	}
	
	@Override
	public void insert(Sale register) {
		
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();
		session.save(register);
		session.getTransaction().commit();
		session.close();

	}

	@Override
	public List<Sale> listAll() {

		List<Sale> result = new ArrayList<Sale>();
		Session session = sessionFactory.openSession();
		
		result = session.createQuery("from Sale").list();
		
		session.close();
		
		return result;
	}

	@Override
	public void update(Sale register) {
		
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
		
		Sale sale = session.get(Sale.class, id);
		
		session.delete(sale);
		session.getTransaction().commit();
		session.close();
	}

	@Override
	public Sale getById(int id) {
		
		Session session = sessionFactory.openSession();
		
		session.beginTransaction();		
		
		Sale sale = session.get(Sale.class, id);	
		
		session.getTransaction().commit();
		session.close();
	
		return sale;

	}

}
